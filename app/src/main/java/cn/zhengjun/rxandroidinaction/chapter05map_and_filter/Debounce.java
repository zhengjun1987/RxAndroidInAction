package cn.zhengjun.rxandroidinaction.chapter05map_and_filter;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import cn.zhengjun.rxandroidinaction.LogUtils;
import cn.zhengjun.rxandroidinaction.chapter04threads.Scheduler;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  15:17
 * Summary : 在这里描述Class的主要功能
 */

public class Debounce {
    private static final String TAG = "Debounce";

    public static void main(String[] args) {
        final Random random = new Random(System.currentTimeMillis() % 99);
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) {
                try {
                    for (int i = 0; i < 20; i++) {
                        observableEmitter.onNext(i);
                        int millis = random.nextInt(5) * 100;
                        LogUtils.print("i = " + i + "   millis = " + millis);
                        Thread.sleep(millis);
                    }
                    observableEmitter.onComplete();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    observableEmitter.onError(e);
                }
            }
        })
                .subscribeOn(Schedulers.single())
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribe(new DefaultSubscriberImpl<Integer>(TAG));

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
