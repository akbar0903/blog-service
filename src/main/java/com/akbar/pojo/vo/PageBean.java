package com.akbar.pojo.vo;

import java.util.List;

public class PageBean {
    private Long total;
    private List rows;

    public PageBean(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageBean() {

    }

    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }
    public void setRows(List rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
