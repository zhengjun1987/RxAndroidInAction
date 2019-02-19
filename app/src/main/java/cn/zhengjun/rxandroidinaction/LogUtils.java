package cn.zhengjun.rxandroidinaction;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/7  16:20
 * Summary : 在这里描述Class的主要功能
 */

public class LogUtils {
    public static void print(String args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println(simpleDateFormat.format(new Date())+"  "+Thread.currentThread().getName()+"  "+args);
    }
}
