package com.cow.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.ParameterDTO;
import com.cow.po.pojo.Parameter;
import com.cow.resp.R;
import com.cow.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  系统参数 - Controller
 * </p>
 *
 * @author cow
 * @since 2021-01-23
 */
@RestController
@RequestMapping("/parameter")
public class ParameterController {

    @Autowired
    private ParameterService parameterService;

    /**
     * 加载列表数据
     * @param parameterDTO
     * @return
     */
    @GetMapping("/list")
    public R list(ParameterDTO parameterDTO) {
        Page<Parameter> page = parameterService.pageData(parameterDTO);
        return R.ok().data(page);
    }

    /**
     * 新增
     * @param parameter
     * @return
     */
    @PostMapping("/add")
    public R add(@RequestBody Parameter parameter) {
        parameterService.add(parameter);
        return R.ok();
    }

    /**
     * 更新
     * @param parameter
     * @return
     */
    @PostMapping("/update")
    public R update(@RequestBody Parameter parameter) {
        parameterService.update(parameter);
        return R.ok();
    }
}

