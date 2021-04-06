package com.syxx.hr.common.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.syxx.hr.entity.Department;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class TreeSelect implements Serializable {

    private Integer id;

    private String label;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;

    public TreeSelect() {

    }

    public TreeSelect(Department department) {
        this.id = department.getId();
        this.label = department.getName();
        if (department.getChildren() != null) {
            this.children = department.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeSelect> getChildren() {
        return children;
    }

    public void setChildren(List<TreeSelect> children) {
        this.children = children;
    }
}
