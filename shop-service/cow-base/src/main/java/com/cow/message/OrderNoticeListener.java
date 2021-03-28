package com.cow.message;

import com.cow.constant.MqTopic;
import com.cow.feign.user.CustomerFeignClient;
import com.cow.po.vo.user.CustomerVo;
import com.cow.po.enums.NoticeType;
import com.cow.po.pojo.Notice;
import com.cow.po.pojo.NoticeUser;
import com.cow.po.vo.order.OrderVo;
import com.cow.service.NoticeService;
import com.cow.service.NoticeUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单审核发通知
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "order-notice-consumer", topic = MqTopic.ORDER_AUDIT)
public class OrderNoticeListener implements RocketMQListener<OrderVo> {

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private NoticeUserService noticeUserService;
    @Autowired
    private CustomerFeignClient customerFeignClient;

    @Override
    @Transactional
    public void onMessage(OrderVo orderVo) {
        log.info("订单审核通过发送通知！");
        // 处理通知
        Notice notice = new Notice();
        notice.setCreator(orderVo.getCreator());
        notice.setContent(orderVo.getContent());
        notice.setSubject(orderVo.getSubject());
        notice.setSourceId(orderVo.getId());
        notice.setType(NoticeType.ORDER.getVal());
        noticeService.save(notice);

        // 处理通知用户
        NoticeUser noticeUser1 = new NoticeUser();
        noticeUser1.setNotice(notice.getId());
        noticeUser1.setRecipient(orderVo.getSalesman()); // 订单业务员
        noticeUser1.setIsRead(false);

        CustomerVo customerVo = customerFeignClient.info(orderVo.getCustomer()).getData();
        NoticeUser noticeUser2 = new NoticeUser();
        noticeUser2.setNotice(notice.getId());
        noticeUser2.setRecipient(customerVo.getUserId()); // 订单客户
        noticeUser2.setIsRead(false);

        noticeUserService.save(noticeUser1);
        noticeUserService.save(noticeUser2);
    }
}
