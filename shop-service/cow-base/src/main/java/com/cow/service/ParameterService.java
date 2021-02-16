package com.cow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cow.po.dto.ParameterDTO;
import com.cow.po.pojo.Parameter;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cow
 * @since 2021-01-23
 */
public interface ParameterService extends IService<Parameter> {

    /**
     * 列表数据
     * @param parameterDTO
     * @return
     */
    Page<Parameter> pageData(ParameterDTO parameterDTO);

    /**
     * 新增
     * @param parameter
     */
    void add(Parameter parameter);

    /**
     * 更新
     * @param parameter
     */
    void update(Parameter parameter);

    /**
     * 根据编码查
     * @param dcode
     * @return
     */
    Parameter findByDcode(String dcode);

}
