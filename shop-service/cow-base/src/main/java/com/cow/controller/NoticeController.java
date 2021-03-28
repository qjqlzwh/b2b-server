package com.cow.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.NoticeDTO;
import com.cow.po.pojo.Notice;
import com.cow.resp.R;
import com.cow.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 通知 前端控制器
 * </p>
 *
 * @author cow
 * @since 2021-03-09
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 加载列表数据
     * @param noticeDTO
     * @return
     */
    @GetMapping("/list")
    public R list(NoticeDTO noticeDTO) {
        Page<Map<String, Object>> page = noticeService.pageData(noticeDTO);
        return R.ok().data(page);
    }

    /**
     * 新增
     * @param notice
     * @return
     */
    @PostMapping("/add")
    public R add(@RequestBody Notice notice) {
        noticeService.add(notice);
        return R.ok();
    }

    /**
     * 更新
     * @param notice
     * @return
     */
    @PostMapping("/update")
    public R update(@RequestBody Notice notice) {
        noticeService.update(notice);
        return R.ok();
    }

    /**
     * 根据id获取
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        Notice notice = noticeService.getById(id);
        return R.ok().data(notice);
    }
    
}

