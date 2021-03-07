package com.cow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cow.po.dto.DictDTO;
import com.cow.po.pojo.Dict;

import java.util.Map;

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

    /**
     * 根据字典编码获取，不包含父级；Map<dvalue, dname> 格式
     * @param dcode
     * @return
     */
    Map<Integer, Object> getMapByDcode(String dcode);
}
