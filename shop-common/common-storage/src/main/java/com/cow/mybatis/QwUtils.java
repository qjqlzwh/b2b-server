package com.cow.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.util.StringUtils;

public class QwUtils {

    /**
     * 相等
     * @param qw QueryWrapper<?>
     * @param column 对应数据库字段名，不是实体类字段名
     * @param val 值
     * @return
     */
    public static QueryWrapper<?> eq(QueryWrapper<?> qw, String column, Object val) {
        if (val instanceof String) {
            if (!StringUtils.hasText(ObjectUtils.toString(val))) {
                return qw;
            }
        } else if (val == null) {
            return qw;
        }
        return qw.eq(column, val);
    }

    /**
     * 不相等
     * @param qw QueryWrapper<?>
     * @param column 对应数据库字段名，不是实体类字段名
     * @param val 值
     * @return
     */
    public static QueryWrapper<?> ne(QueryWrapper<?> qw, String column, Object val) {
        if (val instanceof String) {
            if (!StringUtils.hasText(ObjectUtils.toString(val))) {
                return qw;
            }
        } else if (val == null) {
            return qw;
        }
        return qw.ne(column, val);
    }

    /**
     * LIKE '%值%'
     * @param qw QueryWrapper<?>
     * @param column 对应数据库字段名，不是实体类字段名
     * @param val 值
     * @return
     */
    public static QueryWrapper<?> like(QueryWrapper<?> qw, String column, Object val) {
        if (!StringUtils.hasText(ObjectUtils.toString(val))) {
            return qw;
        }
        return qw.like(column, val);
    }

}
