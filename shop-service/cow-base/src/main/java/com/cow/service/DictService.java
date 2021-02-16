package com.cow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cow.po.dto.DictDTO;
import com.cow.po.pojo.Dict;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cow
 * @since 2020-12-24
 */
public interface DictService extends IService<Dict> {

    /**
     * 新增
     */
    void add(Dict dict);

    /**
     * 更新
     */
    void update(Dict dict);

    /**
     * 分页数据
     * @param dictDTO
     */
    Page<Dict> pageData(DictDTO dictDTO);
}
