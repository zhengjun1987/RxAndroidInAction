package cn.zhengjun.rxandroidinaction.chapter05map_and_filter;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import io.reactivex.Observable;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  14:48
 * Summary : 在这里描述Class的主要功能
 */

public class SkipLast {
    private static final String TAG = "SkipLast";
    public static void main(String[] args) {
        Observable.range(1,5)
                .skipLast(2)
                .subscribe(new DefaultSubscriberImpl<Integer>(TAG));

        Observable.intervalRange(1,10,1,1, TimeUnit.SECONDS)
                .skipLast(3,TimeUnit.SECONDS)
                .subscribe(new DefaultSubscriberImpl<Long>(TAG));

        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
