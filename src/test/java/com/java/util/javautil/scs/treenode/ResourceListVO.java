package com.java.util.javautil.scs.treenode;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class ResourceListVO implements TreeNode<Long> {
    private Long id;

    private Long pid;

    private Integer type;

    private String name;

    private String icon;

    private String code;

    private Integer status;

    private List<ResourceListVO> children;

    @Override
    public Long id() {
        return this.id;
    }

    @Override
    public Long parentId() {
        return this.pid;
    }

    @Override
    public boolean root() {
        return Objects.equals(this.pid, 0L);
    }

    @Override
    public void setChildren(List children) {
        this.children = children;
    }
}

