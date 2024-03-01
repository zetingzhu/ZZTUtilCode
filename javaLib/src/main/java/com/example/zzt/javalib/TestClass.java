package com.example.zzt.javalib;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author: zeting
 * @date: 2024/3/1
 */
public class TestClass {
    public static void main(String[] args) {
//        ZFormat("3.14159265358979323846");

        System.out.println("小数 》》 " + ZFormat("3.14159265358979323846", RoundingMode.HALF_UP, 3, 3));
        System.out.println("小数 》》 " + ZFormat("333.3336", RoundingMode.HALF_UP, 3, 3));
        System.out.println("小数 》》 " + ZFormat("444", RoundingMode.HALF_UP, 3, 5));
        System.out.println("小数 》》 " + ZFormat("$555.6666666", RoundingMode.HALF_UP, 1, 3));
    }

    public static String ZFormat(double value) {
        return ZFormat(String.valueOf(value));
    }

    public static String ZFormat(String value) {
        //创建一个默认的通用格式
        NumberFormat numberFormat = NumberFormat.getInstance();
        DecimalFormat numberDecimalFormat = null;
        //捕捉异常，以防强制类型转换出错
        try {
            //强制转换成DecimalFormat
            numberDecimalFormat = (DecimalFormat) numberFormat;
            //保留小数点后面三位，不足的补零,前面整数部分 每隔四位 ，用 “,” 符合隔开
            numberDecimalFormat.applyPattern("#,####.000");
            //设置舍入模式 为DOWN,否则默认的是HALF_EVEN
            numberDecimalFormat.setRoundingMode(RoundingMode.DOWN);
            //设置 要格式化的数 是正数的时候。前面加前缀
            numberDecimalFormat.setPositivePrefix("Prefix  ");
            System.out.println("正数前缀  " + numberDecimalFormat.format(123456.7891));
            //设置 要格式化的数 是正数的时候。后面加后缀
            numberDecimalFormat.setPositiveSuffix("  Suffix");
            System.out.println("正数后缀  " + numberDecimalFormat.format(123456.7891));
            //设置整数部分的最大位数
            numberDecimalFormat.setMaximumIntegerDigits(3);
            System.out.println("整数最大位数 " + numberDecimalFormat.format(123456.7891));
            //设置整数部分最小位数
            numberDecimalFormat.setMinimumIntegerDigits(10);
            System.out.println("整数最小位数 " + numberDecimalFormat.format(123456.7891));
            //设置小数部分的最大位数
            numberDecimalFormat.setMaximumFractionDigits(2);
            System.out.println("小数部分最大位数 " + numberDecimalFormat.format(123.4));
            //设置小数部分的最小位数
            numberDecimalFormat.setMinimumFractionDigits(6);
            System.out.println("小数部分最小位数 " + numberDecimalFormat.format(123.4));
            return numberDecimalFormat.format(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }


    /**
     * 四舍五入保留小数
     */
    public static String formatRoundHalfUp(String value, int fractionDigits) {
        return ZFormat(value, RoundingMode.HALF_UP, fractionDigits, 0);
    }

    /**
     * 四舍五入保留小数
     */
    public static String formatRoundHalfUp(double value, int fractionDigits) {
        return ZFormat(value, RoundingMode.HALF_UP, fractionDigits, 0);
    }

    /**
     * 四舍五入保留小数，最少保留多少位小数
     */
    public static String formatRoundHalfUp(String value, int fractionDigits, int fractionDigitsMin) {
        return ZFormat(value, RoundingMode.HALF_UP, fractionDigits, fractionDigitsMin);
    }

    /**
     * 四舍五入保留小数，最少保留多少位小数
     */
    public static String formatRoundHalfUp(double value, int fractionDigits, int fractionDigitsMin) {
        return ZFormat(value, RoundingMode.HALF_UP, fractionDigits, fractionDigitsMin);
    }

    /**
     * 数字转换
     *
     * @param value             数字
     * @param roundingMode      舍入模式
     * @param fractionDigitsMax 保留最大小数
     * @param fractionDigitsMin 保留最小小数位数
     * @return
     */
    public static String ZFormat(double value, RoundingMode roundingMode, int fractionDigitsMax, int fractionDigitsMin) {
        return ZFormat(String.valueOf(value), roundingMode, fractionDigitsMax, fractionDigitsMin);
    }

    /**
     * 数字转换
     *
     * @param value             数字
     * @param roundingMode      舍入模式
     * @param fractionDigitsMax 保留最大小数
     * @param fractionDigitsMin 保留最小小数位数
     * @return
     */
    public static String ZFormat(String value, RoundingMode roundingMode, int fractionDigitsMax, int fractionDigitsMin) {
        DecimalFormat numberDecimalFormat = null;
        try {
            BigDecimal bigValue = new BigDecimal(value);
            numberDecimalFormat = getSafeDecimalFormat();
            //设置舍入模式
            numberDecimalFormat.setRoundingMode(roundingMode);
            //设置小数部分的最大位数
            numberDecimalFormat.setMaximumFractionDigits(fractionDigitsMax);
            //设置小数部分的最小位数
            numberDecimalFormat.setMinimumFractionDigits(fractionDigitsMin);
            return numberDecimalFormat.format(bigValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    private static final ThreadLocal<DecimalFormat> DF_THREAD_LOCAL = new ThreadLocal<DecimalFormat>() {
        @Override
        protected DecimalFormat initialValue() {
            return (DecimalFormat) NumberFormat.getInstance();
        }
    };

    public static DecimalFormat getSafeDecimalFormat() {
        return DF_THREAD_LOCAL.get();
    }


}
