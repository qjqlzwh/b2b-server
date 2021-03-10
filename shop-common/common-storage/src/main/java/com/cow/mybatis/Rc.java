package com.cow.mybatis;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 结果集合，返回分页或List
 */
public class Rc {

    private Page<?> pageData;

    private List<?> listData;

    public Rc() {
    }

    public Rc(Page<?> pageData) {
        this.pageData = pageData;
    }

    public Rc(List<?> listData) {
        this.listData = listData;
    }

    public Rc(Page<?> pageData, List<?> listData) {
        this.pageData = pageData;
        this.listData = listData;
    }

    public static Rc pd(Page<?> pageData) {
        return new Rc(pageData);
    }

    public static Rc ld(List<?> listData) {
        return new Rc(listData);
    }

    public Page<?> getPageData() {
        return this.pageData;
    }

    public void setPageData(Page<?> pageData) {
        this.pageData = pageData;
    }

    public List<?> getListData() {
        return this.listData;
    }

    public void setListData(List<?> listData) {
        this.listData = listData;
    }
}
