package com.cow.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * QueryWrapper工具类
 */
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

    /**
     * in
     * @param qw QueryWrapper<?>
     * @param column 对应数据库字段名，不是实体类字段名
     * @param coll 集合值
     * @return
     */
    public static QueryWrapper<?> in(QueryWrapper<?> qw, String column, Collection<?> coll) {
        if (CollectionUtils.isEmpty(coll)) {
            return qw;
        }
        return qw.in(column, coll);
    }
    /**
     * notIn
     * @param qw QueryWrapper<?>
     * @param column 对应数据库字段名，不是实体类字段名
     * @param coll 集合值
     * @return
     */
    public static QueryWrapper<?> notIn(QueryWrapper<?> qw, String column, Collection<?> coll) {
        if (CollectionUtils.isEmpty(coll)) {
            return qw;
        }
        return qw.notIn(column, coll);
    }

}
