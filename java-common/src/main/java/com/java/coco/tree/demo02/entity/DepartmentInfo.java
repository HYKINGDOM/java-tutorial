package com.java.coco.tree.demo02.entity;

/**
 * @author hy852
 */
public class DepartmentInfo {


    private Integer depId;

    private Integer parentId;

    private String depName;

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }
}
