package cn.zhengjun.rxandroidinaction.chapter06conditions_and_boolean;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;
import io.reactivex.functions.Predicate;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/12  18:38
 * Summary : 在这里描述Class的主要功能
 */

public class TakeUntil {
    private static final String TAG = "TakeUntil";

    public static void main(String[] args) {
        Observable.intervalRange(1,9,0,1, TimeUnit.SECONDS)
                .takeUntil(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        boolean b = aLong > 5;
                        LogUtils.print("TakeUntil.test  " + "aLong = [" + aLong + "]   b = " + b);
                        return b;
                    }
                })
                .subscribe(new DefaultSubscriberImpl<Long>(TAG));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Observable.intervalRange(1,9,0,1, TimeUnit.SECONDS)
                .takeWhile(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        boolean b = aLong > 5;
                        LogUtils.print("TakeWhile.test  " + "aLong = [" + aLong + "]   b = " + b);
                        return b;
                    }
                })
                .subscribe(new DefaultSubscriberImpl<Long>("TakeWhile"));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
