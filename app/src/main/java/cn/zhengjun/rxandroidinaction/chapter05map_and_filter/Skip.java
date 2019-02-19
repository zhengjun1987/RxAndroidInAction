package cn.zhengjun.rxandroidinaction.chapter05map_and_filter;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import io.reactivex.Observable;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  14:43
 * Summary : 在这里描述Class的主要功能
 */

public class Skip {
    public static void main(String[] args) {
        Observable.range(1,5)
                .skip(3)
                .subscribe(new DefaultSubscriberImpl<Integer>("Skip"));

        Observable.interval(1, TimeUnit.SECONDS)
                .skip(3)
                .take(6)
                .subscribe(new DefaultSubscriberImpl<Long>("Skip Interval"));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
