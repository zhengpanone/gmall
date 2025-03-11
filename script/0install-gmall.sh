#!/bin/bash

# 设置服务名称
SERVICE_NAME="petro-admin"
K8S_NAMESPACE="petro-ecs"
K8S_YAML_FILE="petro-admin-k8s.yaml"
# docker镜像相关配置
IMAGE_TAG="eclipse-temurin:8-jre"
IMAGE_TAR_PATH="../docker-images/eclipse-temurin-8-jre.tar"

# 获取版本号函数
get_version() {
    local default_version=$(date +"%Y.%m.%d.%H%M%S")
    if [ -n "$1" ]; then
        echo "$1"
    else
        echo "$default_version"
    fi
}

# 构建镜像名称函数
build_image_name() {
    local service=$1
    local version=$2
    echo "${service}:${version}"
}

# 检查并获取Docker私有仓库地址函数
get_docker_registry() {
    local daemon_path="/etc/docker/daemon.json"

    if [ ! -f "$daemon_path" ]; then
        echo "错误：Docker配置文件 $daemon_path 不存在。" >&2
        exit 1
    fi

    local insecure_registries_line=$(grep '"insecure-registries"' "$daemon_path")

    if [ -z "$insecure_registries_line" ]; then
        echo "未能从 $daemon_path 中提取 'insecure-registries' 所在行的内容。" >&2
        exit 1
    fi

    local docker_registry=$(echo "$insecure_registries_line" | grep -oP '\b(?:[A-Za-z0-9-]+\.)+[A-Za-z0-9]{2,}(?::\d{1,5})?\b' | head -n 1)

    if [ -z "$docker_registry" ]; then
        echo "未能找到私服仓库地址。" >&2
        exit 1
    fi

    local domain_or_ip=${docker_registry%:*}
    if ! ping -c 4 "$domain_or_ip" &>/dev/null; then
        echo "错误：无法连接到docker镜像仓库 $domain_or_ip。" >&2
        exit 1
    fi

    echo "$docker_registry"
}

# 检查Docker命令是否可用
check_docker() {
    if ! command -v docker &>/dev/null; then
        echo "错误：Docker命令不可用，请确保Docker已安装并正确配置。" >&2
        exit 1
    fi
}

# 检查Docker命令是否可用
check_kubectl() {
    if ! command -v kubectl &>/dev/null; then
        echo "错误：kubectl命令不可用，请确保kubectl已安装并正确配置。" >&2
        exit 1
    fi
}

# 定义函数：加载镜像
load_image() {
    if ! docker inspect "$IMAGE_TAG" >/dev/null 2>&1; then
        echo "检查并加载 Docker 镜像..."
        docker load -i $IMAGE_TAR_PATH || {
            echo "加载镜像失败" >&2
            exit 1
        }
    else
        echo "Docker 镜像 $IMAGE_TAG 已存在，跳过加载。"
    fi
}

# 构建Docker镜像
build_docker_image() {
    local local_image=$1
    echo "------构建 $local_image 开始-----"
    # 如果||左边的命令（docker build）失败（即返回非零退出状态），则执行右边的代码块
    docker build -f Dockerfile -t "$local_image" ./ || {
        echo "构建镜像失败" >&2
        exit 1
    }
    echo "------构建 $local_image 完成-----"
}

# 推送Docker镜像
push_docker_image() {
    local remote_image=$1
    echo "------开始推送 $remote_image-----"
    # 如果||左边的命令失败（即返回非零退出状态），则执行右边的代码块
    docker push "$remote_image" || {
        echo "推送像失败" >&2
        exit 1
    }
    echo "------推送 $remote_image 完成-----"
}

# 判定k8s命名空间是否存在,不在就创建
check_k8s_namespace() {
    # 检查命名空间是否创建成功
    if ! kubectl get namespace $K8S_NAMESPACE &>/dev/null; then
        # 创建命名空间
        echo "创建 namespace: $K8S_NAMESPACE ..."
        kubectl create namespace $K8S_NAMESPACE || {
            echo "创建 namespace: $K8S_NAMESPACE 创建失败"
            exit 1
        }
    fi
}

# 执行 k8s 配置文件
run_k8s_yaml() {
    local local_k8s_repo=$1
    # 替换yaml文件中 Docker 镜像地址，即 image: "K8S_TARGET_REPO_IMAGE"  或 image: "easzlab.io.local:5000/ecs/petro-html"
    sed -i 's|image: .*"\(.*\)"|image: '\"${local_k8s_repo}\"'|g' "$K8S_YAML_FILE"

    echo "执行 k8s 配置文件..."
    kubectl apply -f "$K8S_YAML_FILE" || exit 1
}

# 主函数
main() {
    # 检查docker 和 kubectl命令是否可用
    check_docker
    check_kubectl

    # 获取版本号
    local version=$(get_version "$1")
    echo "最终版本:$version"

    # 拼接不带仓库地址的镜像名称
    local local_image=$(build_image_name "$SERVICE_NAME" "$version")
    echo "镜像名称:$local_image"

    # 获取Docker私有仓库地址
    local docker_registry=$(get_docker_registry)
    # 判断docker_registry是否为空，为修复地址网络不通后报错，导致docker_registry为空的bug
    if [ -z "$docker_registry" ]; then
        exit 1
    fi
    # 拼接带远程仓库地址的镜像名称
    local remote_image="${docker_registry}/ecs/${local_image}"
    echo "远程镜像名称:$remote_image"

    # 加载镜像
    load_image

    # 构建镜像
    build_docker_image "$remote_image"

    # 推送镜像
    push_docker_image "$remote_image"

    # 检查namespace 并创建
    check_k8s_namespace
    # 执行 k8s 配置文件
    run_k8s_yaml "$remote_image"
}

# 调用主函数
main "$1"
