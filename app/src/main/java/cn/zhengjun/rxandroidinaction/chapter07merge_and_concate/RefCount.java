package cn.zhengjun.rxandroidinaction.chapter07merge_and_concate;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import io.reactivex.Observable;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/21  23:27
 * Summary : 在这里描述Class的主要功能
 */

public class RefCount {
    private static final String TAG = "RefCount";

    public static void main(String[] args) {
        Observable<Long> longObservable = Observable.interval(1, TimeUnit.SECONDS).take(6);
        Observable<Long> refCount = longObservable.publish().refCount();

        longObservable.subscribe(new DefaultSubscriberImpl<Long>("订阅者1"));
        longObservable.delaySubscription(3,TimeUnit.SECONDS)
                .subscribe(new DefaultSubscriberImpl<Long>("订阅者2"));

        refCount.subscribe(new DefaultSubscriberImpl<Long>("订阅者3"));
        refCount.delaySubscription(3,TimeUnit.SECONDS)
                .subscribe(new DefaultSubscriberImpl<Long>("订阅者4"));

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//        2019-02-21 23:34:37:249  main  订阅者1.onStart
//        2019-02-21 23:34:37:267  main  订阅者2.onStart
//        2019-02-21 23:34:37:273  main  订阅者3.onStart
//        2019-02-21 23:34:37:275  main  订阅者4.onStart
//        2019-02-21 23:34:38:268  RxComputationThreadPool-1  订阅者1.onNext  t = [0]
//        2019-02-21 23:34:38:279  RxComputationThreadPool-3  订阅者3.onNext  t = [0]
//        2019-02-21 23:34:39:271  RxComputationThreadPool-1  订阅者1.onNext  t = [1]
//        2019-02-21 23:34:39:277  RxComputationThreadPool-3  订阅者3.onNext  t = [1]
//        2019-02-21 23:34:40:271  RxComputationThreadPool-1  订阅者1.onNext  t = [2]
//        2019-02-21 23:34:40:276  RxComputationThreadPool-3  订阅者3.onNext  t = [2]
//        2019-02-21 23:34:41:269  RxComputationThreadPool-1  订阅者1.onNext  t = [3]
//        2019-02-21 23:34:41:273  RxComputationThreadPool-1  订阅者2.onNext  t = [0]
//        2019-02-21 23:34:41:278  RxComputationThreadPool-3  订阅者3.onNext  t = [3]
//        2019-02-21 23:34:41:278  RxComputationThreadPool-3  订阅者4.onNext  t = [3]
//        2019-02-21 23:34:42:268  RxComputationThreadPool-1  订阅者1.onNext  t = [4]
//        2019-02-21 23:34:42:273  RxComputationThreadPool-1  订阅者2.onNext  t = [1]
//        2019-02-21 23:34:42:279  RxComputationThreadPool-3  订阅者3.onNext  t = [4]
//        2019-02-21 23:34:42:279  RxComputationThreadPool-3  订阅者4.onNext  t = [4]
//        2019-02-21 23:34:43:271  RxComputationThreadPool-1  订阅者1.onNext  t = [5]
//        2019-02-21 23:34:43:272  RxComputationThreadPool-1  订阅者1.onComplete
//        2019-02-21 23:34:43:272  RxComputationThreadPool-1  订阅者2.onNext  t = [2]
//        2019-02-21 23:34:43:278  RxComputationThreadPool-3  订阅者3.onNext  t = [5]
//        2019-02-21 23:34:43:278  RxComputationThreadPool-3  订阅者4.onNext  t = [5]
//        2019-02-21 23:34:43:278  RxComputationThreadPool-3  订阅者3.onComplete
//        2019-02-21 23:34:43:279  RxComputationThreadPool-3  订阅者4.onComplete
//        2019-02-21 23:34:44:276  RxComputationThreadPool-1  订阅者2.onNext  t = [3]
//        2019-02-21 23:34:45:274  RxComputationThreadPool-1  订阅者2.onNext  t = [4]
//        2019-02-21 23:34:46:273  RxComputationThreadPool-1  订阅者2.onNext  t = [5]
//        2019-02-21 23:34:46:273  RxComputationThreadPool-1  订阅者2.onComplete