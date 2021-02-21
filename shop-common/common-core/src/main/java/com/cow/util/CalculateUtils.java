package com.cow.util;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

/**
 * 计算工具
 */
public class CalculateUtils {

    /**
     * 乘
     * @param num1 数值1
     * @param num2 数值2
     * @param count 保留的小数点, 四舍五入保留
     * @return
     */
    public static BigDecimal multiply(String num1, String num2, int count) {
        if (StringUtils.isEmpty(num1) || StringUtils.isEmpty(num2)) {
            return null;
        }
        BigDecimal a = new BigDecimal(num1.trim());
        BigDecimal b = new BigDecimal(num2.trim());
        return a.multiply(b).setScale(count, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * 乘
     * @param num1 数值1
     * @param num2 数值2
     * @param count 保留的小数点, 四舍五入保留
     * @return
     */
    public static BigDecimal multiply(BigDecimal num1, BigDecimal num2, int count) {
        return num1.multiply(num2).setScale(count, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * 乘
     * @param num1 数值1
     * @param num2 数值2
     * @param count 保留的小数点, 四舍五入保留
     * @return
     */
    public static BigDecimal multiply(Object num1, Object num2, int count) {
        if (StringUtils.isEmpty(num1) || StringUtils.isEmpty(num2)) {
            return null;
        }
        BigDecimal a = new BigDecimal(num1.toString().trim());
        BigDecimal b = new BigDecimal(num2.toString().trim());
        return a.multiply(b).setScale(count, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * 除，默认四舍五入
     * @param num1 数值1
     * @param num2 数值2
     * @param count 保留的小数点
     * @return
     */
    public static BigDecimal divide(BigDecimal num1, BigDecimal num2, int count) {
        if (num1 == null || num2 == null) {
            return null;
        }
        return num1.divide(num2, count, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * 除
     * @param num1 数值1
     * @param num2 数值2
     * @param count 保留的小数点
     * @param roundingMode 指定处理方式
     * <br>    #ROUND_UP       向上取整
     * <br>    #ROUND_DOWN     向下取整
     * <br>    #ROUND_CEILING
     * <br>    #ROUND_FLOOR
     * <br>    #ROUND_HALF_UP  四舍五入
     * <br>    #ROUND_HALF_DOWN
     * <br>    #ROUND_HALF_EVEN
     * <br>    #ROUND_UNNECESSARY
     * @return
     */
    public static BigDecimal divide(BigDecimal num1, BigDecimal num2, int count, int roundingMode) {
        if (num1 == null || num2 == null) {
            return null;
        }
        return num1.divide(num2, count, roundingMode);
    }
    
    /**
     * 加 
     * @param num1 数值1  不存在时以0处理
     * @param num2 数值2  不存在时以0处理
     * @return
     */
    public static BigDecimal add(BigDecimal num1, BigDecimal num2) {
        BigDecimal a = (num1 == null) ? BigDecimal.ZERO : num1;
        BigDecimal b = (num2 == null) ? BigDecimal.ZERO : num2;
        return a.add(b);
    }

    /**
     * 加
     * @param num1 数值1  不存在时以0处理
     * @param num2 数值2  不存在时以0处理
     * @param count 保留的小数点, 四舍五入保留
     * @return
     */
    public static BigDecimal add(BigDecimal num1, BigDecimal num2, int count) {
        BigDecimal a = (num1 == null) ? BigDecimal.ZERO : num1;
        BigDecimal b = (num2 == null) ? BigDecimal.ZERO : num2;
        return a.add(b).setScale(count, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * 加 
     * @param num1 数值1  不存在时以0处理
     * @param num2 数值2  不存在时以0处理
     * @param count 保留的小数点, 四舍五入保留
     * @return
     */
    public static BigDecimal add(String num1, String num2, int count) {
        BigDecimal a = StringUtils.isEmpty(num1) ? BigDecimal.ZERO : new BigDecimal(num1.trim());
        BigDecimal b = StringUtils.isEmpty(num2) ? BigDecimal.ZERO : new BigDecimal(num2.trim());
        return a.add(b).setScale(count, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * 减
     * @param num1 数值1  不存在时以0处理
     * @param num2 数值2  不存在时以0处理
     * @param count 保留的小数点, 四舍五入保留
     * @return
     */
    public static BigDecimal subtract(String num1, String num2, int count) {
        BigDecimal a = StringUtils.isEmpty(num1) ? BigDecimal.ZERO : new BigDecimal(num1.trim());
        BigDecimal b = StringUtils.isEmpty(num2) ? BigDecimal.ZERO : new BigDecimal(num2.trim());
        return a.subtract(b).setScale(count, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * 减
     * @param num1 数值1  不存在时以0处理
     * @param num2 数值2  不存在时以0处理
     * @param count 保留的小数点, 四舍五入保留
     * @return
     */
    public static BigDecimal subtract(BigDecimal num1, BigDecimal num2, int count) {
        BigDecimal a = (num1 == null) ? BigDecimal.ZERO : num1;
        BigDecimal b = (num2 == null) ? BigDecimal.ZERO : num2;
        return a.subtract(b).setScale(count, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * 字符串转BigDecimal
     * @param s
     * @return
     */
    public static BigDecimal parseBigDecimal(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return new BigDecimal(s);
    }
    
    /**
     * BigDecimal比较<br>
     * b1 = b2  return 0 <br>
     * b1 > b2  return 1 <br>
     * b1 < b2  return -1 <br>
     * @param b1
     * @param b2
     * @return
     */
    public static int compareTo(BigDecimal b1, BigDecimal b2) {
        b1 = (b1 == null) ? BigDecimal.ZERO : b1;
        b2 = (b2 == null) ? BigDecimal.ZERO : b2;
        return b1.compareTo(b2);
    }
    
}
