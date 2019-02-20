package cn.zhengjun.rxandroidinaction.chapter07merge_and_concate;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/20  00:31
 * Summary : 在这里描述Class的主要功能
 */

public class Connect {
    private static final String TAG = "Connect";

    public static void main(String[] args) {
        Observable<Long> longObservable = Observable.interval(1, TimeUnit.SECONDS).take(6);
        ConnectableObservable<Long> connectableObservable = longObservable.publish();
        connectableObservable.subscribe(new DefaultSubscriberImpl<Long>("订阅者1"));
        connectableObservable
                .delaySubscription(3, TimeUnit.SECONDS)
                .subscribe(new DefaultSubscriberImpl<Long>("订阅者2") );
        connectableObservable.connect();
        try {
            Thread.sleep(18000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
//
//        2019-02-20 23:56:23:428  main  订阅者1.onStart
//        2019-02-20 23:56:23:438  main  订阅者2.onStart
//        2019-02-20 23:56:24:476  RxComputationThreadPool-2  订阅者1.onNext  t = [0]
//        2019-02-20 23:56:25:478  RxComputationThreadPool-2  订阅者1.onNext  t = [1]
//        2019-02-20 23:56:26:478  RxComputationThreadPool-2  订阅者1.onNext  t = [2]
//        2019-02-20 23:56:27:481  RxComputationThreadPool-2  订阅者1.onNext  t = [3]
//        2019-02-20 23:56:27:481  RxComputationThreadPool-2  订阅者2.onNext  t = [3]
//        2019-02-20 23:56:28:476  RxComputationThreadPool-2  订阅者1.onNext  t = [4]
//        2019-02-20 23:56:28:476  RxComputationThreadPool-2  订阅者2.onNext  t = [4]
//        2019-02-20 23:56:29:480  RxComputationThreadPool-2  订阅者1.onNext  t = [5]
//        2019-02-20 23:56:29:480  RxComputationThreadPool-2  订阅者2.onNext  t = [5]
//        2019-02-20 23:56:29:480  RxComputationThreadPool-2  订阅者1.onComplete
//        2019-02-20 23:56:29:481  RxComputationThreadPool-2  订阅者2.onComplete