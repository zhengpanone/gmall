package com.zp.gmall.module.infra.service.file;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.infra.controller.admin.file.dto.FileCreateDTO;
import com.zp.gmall.module.infra.controller.admin.file.dto.FilePageDTO;
import com.zp.gmall.module.infra.controller.admin.file.vo.FilePresignedUrlVO;
import com.zp.gmall.module.infra.entity.file.FileDO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface IFileService extends IService<FileDO> {
    /**
     * 获得文件分页
     *
     * @param pageDTO 分页查询
     * @return 文件分页
     */
    PageResult<FileDO> getFilePage(FilePageDTO pageDTO);

    /**
     * 生成文件预签名地址信息，用于上传
     *
     * @param name      文件名
     * @param directory 目录
     * @return 预签名地址信息
     */
    FilePresignedUrlVO presignPutUrl(@NotEmpty(message = "文件名不能为空") String name,
                                     String directory);

    /**
     * 生成文件预签名地址信息，用于读取
     *
     * @param url               完整的文件访问地址
     * @param expirationSeconds 访问有效期，单位秒
     * @return 文件预签名地址
     */
    String presignGetUrl(String url, Integer expirationSeconds);

    /**
     * 保存文件，并返回文件的访问路径
     *
     * @param content   文件内容
     * @param fileName  文件名称，允许空
     * @param directory 目录，允许空
     * @param type      文件的 MIME 类型，允许空
     * @return 文件路径
     */
    String createFile(byte[] content, String fileName, String directory, String type);

    /**
     * 创建文件
     *
     * @param createDTO 创建信息
     * @return 文件路径
     */
    String createFile(@Valid FileCreateDTO createDTO);

    /**
     * 删除文件
     *
     * @param id 编号
     */
    void deleteFile(String id) throws Exception;

    /**
     * 批量删除文件
     *
     * @param ids 编号列表
     */
    void deleteFileList(Collection<? extends Serializable> ids) throws Exception;

    /**
     * 获得文件内容
     *
     * @param configId 配置编号
     * @param path     文件路径
     * @return 文件内容
     */
    byte[] getFileContent(String configId, String path) throws Exception;

    /**
     * 获得文件
     *
     * @param configId 配置编号
     * @param path     文件路径
     * @return 文件
     */
    FileDO getFileByConfigIdAndPath(String configId, String path);
}
