package cn.zhengjun.rxandroidinaction.chapter05map_and_filter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import io.reactivex.Observable;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  14:29
 * Summary : 在这里描述Class的主要功能
 */

public class TakeLast {
    private static final String TAG = "TakeLast";
    public static void main(String[] args) {
        Observable.range(1,10)
                .takeLast(3)
                .buffer(3)
                .subscribe(new DefaultSubscriberImpl<List<Integer>>(TAG));


        Observable.intervalRange(1,10,1,1, TimeUnit.SECONDS)
                .takeLast(3)
                .subscribe(new DefaultSubscriberImpl<Long>(TAG));

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
