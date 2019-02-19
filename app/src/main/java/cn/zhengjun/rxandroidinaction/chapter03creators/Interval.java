package cn.zhengjun.rxandroidinaction.chapter03creators;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/7  14:25
 * Summary : 在这里描述Class的主要功能
 */

public class Interval {
    public static void main(String[] args) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println("start = " + simpleDateFormat.format(new Date()));
        Observable.interval(1, TimeUnit.SECONDS)
                .take(4)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(simpleDateFormat.format(new Date())+" Interval.accept  " + "aLong = [" + aLong + "]");
                    }
                });

        System.out.println("*****start = "+simpleDateFormat.format(new Date()));
        Observable.timer(1,TimeUnit.SECONDS)
                .repeat(3)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(simpleDateFormat.format(new Date())+" *****Interval.accept  " + "aLong = [" + aLong + "]");
                    }
                });

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
