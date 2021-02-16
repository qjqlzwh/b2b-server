package com.cow.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class IdUtils {

    /**
     * 雪花算法生成
     * @return
     */
    public static String snfId() {
        // 参数1为终端ID
        // 参数2为数据中心ID
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        return String.valueOf(snowflake.nextId());
    }

}
