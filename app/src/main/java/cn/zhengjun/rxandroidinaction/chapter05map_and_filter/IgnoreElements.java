package cn.zhengjun.rxandroidinaction.chapter05map_and_filter;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  14:59
 * Summary : 在这里描述Class的主要功能
 */

public class IgnoreElements {
    public static void main(String[] args) {
        Observable.intervalRange(1,10,1,1, TimeUnit.SECONDS)
                .ignoreElements()
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        LogUtils.print("IgnoreElements.onSubscribe  " + "disposable = [" + disposable + "]");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.print("IgnoreElements.onComplete  " + "");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        LogUtils.print("IgnoreElements.onError  " + "throwable = [" + throwable + "]");
                    }
                });

        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
