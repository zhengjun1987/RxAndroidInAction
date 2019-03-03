package cn.zhengjun.rxandroidinaction.chapter02basicals;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/3/3  23:57
 * Summary : 在这里描述Class的主要功能
 */

public class SingleTest {
    private static final String TAG = "SingleTest";

    public static void main(String[] args) throws InterruptedException {
        Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> singleEmitter) throws Exception {
                LogUtils.print("SingleOnSubscribe.subscribe  " + "singleEmitter = [" + singleEmitter + "]");
                singleEmitter.onSuccess("SINGLE1");//仅接受1个数据，其后的忽略
                singleEmitter.onSuccess("SINGLE2");
            }
        })
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        LogUtils.print("SingleTest.onSubscribe  " + "disposable = [" + disposable + "]");
                    }

                    @Override
                    public void onSuccess(String s) {
                        LogUtils.print("SingleTest.onSuccess  " + "s = [" + s + "]");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        LogUtils.print("SingleTest.onError  " + "throwable = [" + throwable + "]");
                    }
                });

        Single.timer(2, TimeUnit.SECONDS)
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(Long aLong) throws Exception {
                        return "TIMER_MAP";
                    }
                })
                .subscribe(new DisposableSingleObserver<String>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        LogUtils.print("SingleTest.onStart");
                    }

                    @Override
                    public void onSuccess(String s) {
                        LogUtils.print("SingleTest.onSuccess  " + "s = [" + s + "]");
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }
                });

        Thread.sleep(3000);
    }
}
