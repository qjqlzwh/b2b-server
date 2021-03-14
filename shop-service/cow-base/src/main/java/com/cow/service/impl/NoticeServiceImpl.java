package com.cow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.jwt.JwtUtils;
import com.cow.mybatis.QwUtils;
import com.cow.po.dto.NoticeDTO;
import com.cow.po.pojo.Notice;
import com.cow.mapper.NoticeMapper;
import com.cow.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cow.util.WebUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * <p>
 * 通知 服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-03-09
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    /**
     * 列表数据
     *
     * @param noticeDTO
     * @return
     */
    @Override
    public Page<Map<String, Object>> pageData(NoticeDTO noticeDTO) {
        QueryWrapper<Notice> qw = new QueryWrapper<>();
        QwUtils.eq(qw, "subject", noticeDTO.getSubject());
        QwUtils.eq(qw, "creator", noticeDTO.getCreator());
        return baseMapper.selectMapsPage(noticeDTO.page(), qw);
    }

    /**
     * 新增
     *
     * @param notice
     */
    @Override
    public void add(Notice notice) {
        notice.setCreator(WebUtils.getLoginUsername());
        handleSaveOrUpdate(notice);
        baseMapper.insert(notice);
    }

    /**
     * 更新
     *
     * @param notice
     */
    @Override
    public void update(Notice notice) {
        handleSaveOrUpdate(notice);
        baseMapper.updateById(notice);
    }

    private void handleSaveOrUpdate(Notice notice) {
        Assert.notNull(notice.getType(), "通知类型不能为空");
        Assert.hasText(notice.getContent(), "通知内容不能为空");
        Assert.hasText(notice.getSubject(), "通知主题不能为空");
    }
}
