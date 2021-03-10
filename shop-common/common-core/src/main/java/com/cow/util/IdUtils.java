package com.cow.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.apache.commons.lang3.RandomUtils;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IdUtils {

    /**
     * 订单号生成
     **/
    private static ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");
    private static final AtomicInteger SEQ = new AtomicInteger(10000);
    private static final DateTimeFormatter DF_FMT_PREFIX = DateTimeFormatter.ofPattern("yyMMddHHmmssSS");

    public static String generateOrderNo() {
        LocalDateTime dataTime = LocalDateTime.now(ZONE_ID);
        if (SEQ.intValue() > 99900) {
            SEQ.getAndSet(10000);
        }
        return dataTime.format(DF_FMT_PREFIX) + getLocalIpSuffix() + SEQ.getAndIncrement();
    }

    private volatile static String IP_SUFFIX = null;

    private static String getLocalIpSuffix() {
        if (null != IP_SUFFIX) {
            return IP_SUFFIX;
        }
        try {
            synchronized (IdUtils.class) {
                if (null != IP_SUFFIX) {
                    return IP_SUFFIX;
                }
                InetAddress addr = InetAddress.getLocalHost();
                //  172.17.0.4  172.17.0.199 ,
                String hostAddress = addr.getHostAddress();
                if (null != hostAddress && hostAddress.length() > 4) {
                    String ipSuffix = hostAddress.trim().split("\\.")[3];
                    if (ipSuffix.length() == 2) {
                        IP_SUFFIX = ipSuffix;
                        return IP_SUFFIX;
                    }
                    ipSuffix = "0" + ipSuffix;
                    IP_SUFFIX = ipSuffix.substring(ipSuffix.length() - 2);
                    return IP_SUFFIX;
                }
                IP_SUFFIX = RandomUtils.nextInt(10, 20) + "";
                return IP_SUFFIX;
            }
        } catch (Exception e) {
            System.out.println("获取IP失败:" + e.getMessage());
            IP_SUFFIX = RandomUtils.nextInt(10, 20) + "";
            return IP_SUFFIX;
        }
    }


    public static void main(String[] args) {
        List<String> orderNos = Collections.synchronizedList(new ArrayList<String>());
        IntStream.range(0, 100000).parallel().forEach(i -> {
            orderNos.add(generateOrderNo());
        });

        List<String> filterOrderNos = orderNos.stream().distinct().collect(Collectors.toList());

//        for (String sn : orderNos) {
//            System.out.println(sn);
//        }

        System.out.println("订单样例：" + orderNos.get(22));
        System.out.println("生成订单数：" + orderNos.size());
        System.out.println("过滤重复后订单数：" + filterOrderNos.size());
        System.out.println("重复订单数：" + (orderNos.size() - filterOrderNos.size()));
    }

    /**
     * 雪花算法生成
     *
     * @return
     */
    public static String snfId() {
        // 参数1为终端ID
        // 参数2为数据中心ID
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        return String.valueOf(snowflake.nextId());
    }

}
