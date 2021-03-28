package com.cow.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.AreaDTO;
import com.cow.po.pojo.Area;
import com.cow.resp.R;
import com.cow.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 地区 - Controller
 * </p>
 *
 * @author cow
 * @since 2021-01-23
 */
@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @PostMapping("/init")
    public R init(@RequestBody List<Area> areas) {
        areaService.init(areas);
        return R.ok();
    }

    /**
     * 加载列表数据
     * @param areaDTO
     * @return
     */
    @GetMapping("/list")
    public R list(AreaDTO areaDTO) {
        System.out.println("线程名：" + Thread.currentThread().getName());
        Page<Area> page = areaService.pageData(areaDTO);
        return R.ok().data(page);
    }

    /**
     * 加载子列表数据
     */
    @GetMapping("/getChild/{parentId}")
    public R getChild(@PathVariable("parentId") Long parentId) {
        List<Area> childList = areaService.list(Wrappers.<Area>lambdaQuery().eq(Area::getParentId, parentId));
        Area parentArea = areaService.getById(parentId);
        if (parentArea.getParentId() == null) {
            // 如果 parentId 是省级，则市级有子级
            childList.forEach(area -> area.setHasChild(true));
        }
        return R.ok().data(childList);
    }

    /**
     * 获取所有地区
     */
    @GetMapping("/allArea")
    public R allArea() {
        List<Area> areaList = areaService.allArea();
        return R.ok().data(areaList);
    }

}

