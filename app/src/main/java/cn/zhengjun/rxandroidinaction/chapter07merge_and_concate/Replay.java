package cn.zhengjun.rxandroidinaction.chapter07merge_and_concate;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/21  23:45
 * Summary : 在这里描述Class的主要功能
 */

public class Replay {
    private static final String TAG = "Replay";

    public static void main(String[] args) {
        ConnectableObservable<Long> connectableObservable = Observable.interval(1, TimeUnit.SECONDS).take(6).replay();
        connectableObservable.connect();
        connectableObservable.subscribe(new DefaultSubscriberImpl<Long>("订阅者1"));
        connectableObservable.delaySubscription(3,TimeUnit.SECONDS)
                .subscribe(new DefaultSubscriberImpl<Long>("订阅者2"));

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//        2019-02-21 23:50:02:690  main  订阅者1.onStart
//        2019-02-21 23:50:02:693  main  订阅者2.onStart
//        2019-02-21 23:50:03:657  RxComputationThreadPool-1  订阅者1.onNext  t = [0]
//        2019-02-21 23:50:04:645  RxComputationThreadPool-1  订阅者1.onNext  t = [1]
//        2019-02-21 23:50:05:643  RxComputationThreadPool-1  订阅者1.onNext  t = [2]
//        2019-02-21 23:50:05:702  RxComputationThreadPool-2  订阅者2.onNext  t = [0]
//        2019-02-21 23:50:05:703  RxComputationThreadPool-2  订阅者2.onNext  t = [1]
//        2019-02-21 23:50:05:703  RxComputationThreadPool-2  订阅者2.onNext  t = [2]
//        2019-02-21 23:50:06:644  RxComputationThreadPool-1  订阅者1.onNext  t = [3]
//        2019-02-21 23:50:06:645  RxComputationThreadPool-1  订阅者2.onNext  t = [3]
//        2019-02-21 23:50:07:645  RxComputationThreadPool-1  订阅者1.onNext  t = [4]
//        2019-02-21 23:50:07:646  RxComputationThreadPool-1  订阅者2.onNext  t = [4]
//        2019-02-21 23:50:08:645  RxComputationThreadPool-1  订阅者1.onNext  t = [5]
//        2019-02-21 23:50:08:645  RxComputationThreadPool-1  订阅者2.onNext  t = [5]
//        2019-02-21 23:50:08:646  RxComputationThreadPool-1  订阅者1.onComplete
//        2019-02-21 23:50:08:646  RxComputationThreadPool-1  订阅者2.onComplete
//
//        Process finished with exit code 0