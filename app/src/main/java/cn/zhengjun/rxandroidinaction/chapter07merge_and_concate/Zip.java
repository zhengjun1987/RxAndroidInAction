package cn.zhengjun.rxandroidinaction.chapter07merge_and_concate;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/13  00:53
 * Summary : 在这里描述Class的主要功能
 */

public class Zip {
    private static final String TAG = "Zip";

    public static void main(String[] args) {
        Observable<Long> lows = Observable.intervalRange(1, 7, 0, 1, TimeUnit.SECONDS);
        Observable<Long> highs = Observable.intervalRange(6, 5, 0, 2, TimeUnit.SECONDS);
        Observable.zip(lows.skip(2), highs, new BiFunction<Long, Long, Long>() {
            @Override
            public Long apply(Long aLong, Long aLong2) throws Exception {
                return aLong + aLong2;
            }
        }).subscribe(new DefaultSubscriberImpl<Long>(TAG));

        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtils.print("========================");
        Observable.combineLatest(lows.skip(2), highs, new BiFunction<Long, Long, Long>() {
            @Override
            public Long apply(Long aLong, Long aLong2) throws Exception {
                return aLong + aLong2;
            }
        }).subscribe(new DefaultSubscriberImpl<Long>(TAG));

        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
