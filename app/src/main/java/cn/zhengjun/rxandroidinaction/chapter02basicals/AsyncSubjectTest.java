package cn.zhengjun.rxandroidinaction.chapter02basicals;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/3/4  01:58
 * Summary : 在这里描述Class的主要功能
 */

public class AsyncSubjectTest {
    private static final String TAG = "AsyncSubjectTest";

    public static void main(String[] args) {
        final AsyncSubject<String> asyncSubject = AsyncSubject.create();
        asyncSubject.onNext("1");
        asyncSubject.onComplete();
        asyncSubject.onNext("2");
        asyncSubject.subscribe(new DefaultSubscriberImpl<String>(TAG + "1"));
        asyncSubject
                .subscribeOn(Schedulers.single())
                .subscribe(new DefaultSubscriberImpl<String>(TAG + "2"));
        Runnable runnable = new Runnable() {
            public void run() {
                asyncSubject.onNext("3");
            }
        };
        Executors.newFixedThreadPool(1).submit(runnable);
        asyncSubject.onNext("4");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
//        2019-03-04 02:52:41:435  main  AsyncSubjectTest1.onStart
//        2019-03-04 02:52:41:438  main  AsyncSubjectTest1.onNext  t = [2]
//        2019-03-04 02:52:41:438  main  AsyncSubjectTest1.onComplete
//        2019-03-04 02:52:41:549  main  AsyncSubjectTest2.onStart
//        2019-03-04 02:52:41:574  RxSingleScheduler-1  AsyncSubjectTest2.onNext  t = [2]
//        2019-03-04 02:52:41:574  RxSingleScheduler-1  AsyncSubjectTest2.onComplete
//
//        Process finished with exit code 0
