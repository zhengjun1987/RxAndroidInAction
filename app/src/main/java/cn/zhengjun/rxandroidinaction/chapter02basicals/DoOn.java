package cn.zhengjun.rxandroidinaction.chapter02basicals;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/3/3  23:40
 * Summary : 在这里描述Class的主要功能
 */

public class DoOn {
    private static final String TAG = "DoOn";

    public static void main(String[] args) {
        Observable.intervalRange(1,5,1,1, TimeUnit.SECONDS)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        LogUtils.print("doOnSubscribe.accept  " + "disposable = [" + disposable + "]");
                    }
                })
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.print("doOnDispose.run  " + "");
                    }
                })
                .doOnEach(new Consumer<Notification<Long>>() {
                    @Override
                    public void accept(Notification<Long> longNotification) throws Exception {
                        LogUtils.print("doOnEach.accept  " + "longNotification = [" + longNotification.toString() + "]");
                    }
                })
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogUtils.print("doOnNext.accept  " + "aLong = [" + aLong + "]");
                    }
                })
                .doOnLifecycle(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        LogUtils.print("doOnLifecycle.onSubscribe  " + "disposable = [" + disposable + "]");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.print("doOnLifecycle.onDispose  " + "");
                    }
                })
                .doAfterNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogUtils.print("doAfterNext.accept  " + "aLong = [" + aLong + "]");
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.print("doOnTerminate.run  " + "");
                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.print("doAfterTerminate.run  " + "");
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.print("doFinally.run  " + "");
                    }
                })
                .subscribe(new DefaultSubscriberImpl<Long>(TAG));

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//        2019-03-03 23:50:51:284  main  doOnSubscribe.accept  disposable = [null]
//        2019-03-03 23:50:51:294  main  doOnLifecycle.onSubscribe  disposable = [io.reactivex.internal.operators.observable.ObservableDoOnEach$DoOnEachObserver@7a46a697]
//        2019-03-03 23:50:51:294  main  DoOn.onStart
//        2019-03-03 23:50:52:306  RxComputationThreadPool-1  doOnEach.accept  longNotification = [OnNextNotification[1]]
//        2019-03-03 23:50:52:307  RxComputationThreadPool-1  doOnNext.accept  aLong = [1]
//        2019-03-03 23:50:52:307  RxComputationThreadPool-1  DoOn.onNext  t = [1]
//        2019-03-03 23:50:52:307  RxComputationThreadPool-1  doAfterNext.accept  aLong = [1]
//        2019-03-03 23:50:53:301  RxComputationThreadPool-1  doOnEach.accept  longNotification = [OnNextNotification[2]]
//        2019-03-03 23:50:53:301  RxComputationThreadPool-1  doOnNext.accept  aLong = [2]
//        2019-03-03 23:50:53:301  RxComputationThreadPool-1  DoOn.onNext  t = [2]
//        2019-03-03 23:50:53:302  RxComputationThreadPool-1  doAfterNext.accept  aLong = [2]
//        2019-03-03 23:50:54:299  RxComputationThreadPool-1  doOnEach.accept  longNotification = [OnNextNotification[3]]
//        2019-03-03 23:50:54:300  RxComputationThreadPool-1  doOnNext.accept  aLong = [3]
//        2019-03-03 23:50:54:300  RxComputationThreadPool-1  DoOn.onNext  t = [3]
//        2019-03-03 23:50:54:300  RxComputationThreadPool-1  doAfterNext.accept  aLong = [3]
//        2019-03-03 23:50:55:300  RxComputationThreadPool-1  doOnEach.accept  longNotification = [OnNextNotification[4]]
//        2019-03-03 23:50:55:300  RxComputationThreadPool-1  doOnNext.accept  aLong = [4]
//        2019-03-03 23:50:55:301  RxComputationThreadPool-1  DoOn.onNext  t = [4]
//        2019-03-03 23:50:55:301  RxComputationThreadPool-1  doAfterNext.accept  aLong = [4]
//        2019-03-03 23:50:56:301  RxComputationThreadPool-1  doOnEach.accept  longNotification = [OnNextNotification[5]]
//        2019-03-03 23:50:56:301  RxComputationThreadPool-1  doOnNext.accept  aLong = [5]
//        2019-03-03 23:50:56:301  RxComputationThreadPool-1  DoOn.onNext  t = [5]
//        2019-03-03 23:50:56:302  RxComputationThreadPool-1  doAfterNext.accept  aLong = [5]
//        2019-03-03 23:50:56:302  RxComputationThreadPool-1  doOnEach.accept  longNotification = [OnCompleteNotification]
//        2019-03-03 23:50:56:302  RxComputationThreadPool-1  doOnTerminate.run
//        2019-03-03 23:50:56:302  RxComputationThreadPool-1  DoOn.onComplete
//        2019-03-03 23:50:56:303  RxComputationThreadPool-1  doFinally.run
//        2019-03-03 23:50:56:303  RxComputationThreadPool-1  doAfterTerminate.run
//
//        Process finished with exit code 0
