package cn.zhengjun.rxandroidinaction.chapter08backpressure;

import cn.zhengjun.rxandroidinaction.LogUtils;
import cn.zhengjun.rxandroidinaction.chapter04threads.Scheduler;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/22  00:16
 * Summary : 在这里描述Class的主要功能
 */

public class Error {
    public static void main(String[] args) {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> flowableEmitter) throws Exception {
                for (int i = 0; ; i++) {
                    LogUtils.print("Error.subscribe  " + "i = [" + i + "]");
                    flowableEmitter.onNext(i);
                }
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.single())
                .subscribe(new DefaultSubscriber<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.print("Error.onNext  " + "integer = [" + integer + "]");
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
