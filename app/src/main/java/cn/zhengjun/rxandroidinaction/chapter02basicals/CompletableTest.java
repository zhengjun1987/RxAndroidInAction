package cn.zhengjun.rxandroidinaction.chapter02basicals;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/3/4  00:10
 * Summary : 在这里描述Class的主要功能
 */

public class CompletableTest {
    private static final String TAG = "CompletableTest";

    public static void main(String[] args) {
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter completableEmitter) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    completableEmitter.onComplete();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    completableEmitter.onError(e);
                }
            }
        })
                .andThen(Observable.error(new Exception("Error")))
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        LogUtils.print("CompletableTest.onSubscribe  " + "disposable = [" + disposable + "]");
                    }

                    @Override
                    public void onNext(Object o) {
                        LogUtils.print("CompletableTest.onNext  " + "o = [" + o + "]");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.print("CompletableTest.onComplete");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        LogUtils.print("CompletableTest.onError  " + "throwable = [" + throwable + "]");
                    }
                });

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
