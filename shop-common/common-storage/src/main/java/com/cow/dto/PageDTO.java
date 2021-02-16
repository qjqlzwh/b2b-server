package com.cow.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class PageDTO {

    /**
     * 每页显示的条数
     */
    private long limit = 10;

    /**
     * 当前页
     */
    private long page = 1;

    public Page page()  {
        return new Page(this.page, this.limit);
    }

    public long getLimit() {
        return this.limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getPage() {
        return this.page;
    }

    public void setPage(long page) {
        this.page = page;
    }
}
