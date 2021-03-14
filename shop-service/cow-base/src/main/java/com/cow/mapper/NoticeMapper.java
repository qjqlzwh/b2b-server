package com.cow.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.NoticeDTO;
import com.cow.po.pojo.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 通知 Mapper 接口
 * </p>
 *
 * @author cow
 * @since 2021-03-09
 */
public interface NoticeMapper extends BaseMapper<Notice> {

    Page<Map<String, Object>> pageData(Page page, NoticeDTO noticeDTO);
}
