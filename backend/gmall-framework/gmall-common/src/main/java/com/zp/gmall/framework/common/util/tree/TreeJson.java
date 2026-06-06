package com.zp.gmall.framework.common.util.tree;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;


/**
 * Author : zhengpanone
 * Date : 2023/11/10 23:34
 * Version : v1.0.0
 */
@Schema(description = "树形节点")
@NoArgsConstructor
@Data
public class TreeJson extends TreeNode {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "节点ID", accessMode = Schema.AccessMode.READ_ONLY)
    private String id;
    @Schema(description = "节点名称", accessMode = Schema.AccessMode.READ_ONLY)
    private String name;
    @Schema(description = "父节点ID", accessMode = Schema.AccessMode.READ_ONLY)
    private String parentId;
    @Schema(description = "节点类型", accessMode = Schema.AccessMode.READ_ONLY)
    private String type;
    @Schema(description = "编码", accessMode = Schema.AccessMode.READ_ONLY)
    private String code;
    @Schema(description = "选中", accessMode = Schema.AccessMode.READ_ONLY)
    private String selFag;


    public TreeJson(String id, String code, String name, String type, String parentId) {
        super(id, name, parentId, type, null);
        this.id = id;
        this.code = code;
        this.name = name;
        this.type = type;
        this.parentId = parentId;
    }


    public void setSelFag(boolean selected) {
        this.setSelFag(selected ? "1" : "0");
    }

    public void setId(String id) {
        this.setTreeNodeId(id);
    }

    public void setName(String name) {
        this.setTreeNodeName(name);
    }

    public void setType(String type) {
        this.setTreeNodeType(type);
    }

    public void setSelFag(String selFag) {
        this.selFag = selFag;
    }

    public void setParentId(String parentId) {
        this.setTreeNodeParent(parentId);
    }

    @Override
    public void setTreeNodeId(String id) {
        super.setTreeNodeId(id);
        this.id = id;
    }

    @Override
    public void setTreeNodeName(String name) {
        super.setTreeNodeName(name);
        this.name = name;
    }

    @Override
    public void setTreeNodeParent(String parentId) {
        super.setTreeNodeParent(parentId);
        this.parentId = parentId;
    }

    @Override
    public void setTreeNodeType(String type) {
        super.setTreeNodeType(type);
        this.type = type;
    }

    @Override
    public String toString() {
        return "TreeJson{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", selFag='" + selFag + '\'' +
                '}';
    }
}
