package cn.zhengjun.rxandroidinaction.chapter07merge_and_concate;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import io.reactivex.Observable;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/12  22:52
 * Summary : 在这里描述Class的主要功能
 */

public class Merge {
    private static final String TAG = "Merge";

    public static void main(String[] args) {
//        Observable<Integer> odds = Observable.just(1, 3, 5,7,9);
//        Observable<Integer> evens = Observable.just(2, 4, 6);
//        Observable.merge(odds,evens)
//                .subscribe(new DefaultSubscriberImpl<Integer>(TAG));


        Observable.merge(
                Observable.intervalRange(1,5,0,1, TimeUnit.SECONDS),
                Observable.intervalRange(6,5,0,1, TimeUnit.SECONDS)
                )
                .subscribe(new DefaultSubscriberImpl<Long>(TAG));

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
