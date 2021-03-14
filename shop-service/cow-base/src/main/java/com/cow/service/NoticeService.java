package com.cow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.NoticeDTO;
import com.cow.po.pojo.Notice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 通知 服务类
 * </p>
 *
 * @author cow
 * @since 2021-03-09
 */
public interface NoticeService extends IService<Notice> {

    /**
     * 列表数据
     * @param noticeDTO
     * @return
     */
    Page<Map<String, Object>> pageData(NoticeDTO noticeDTO);

    /**
     * 新增
     * @param notice
     */
    void add(Notice notice);

    /**
     * 更新
     * @param notice
     */
    void update(Notice notice);
}
