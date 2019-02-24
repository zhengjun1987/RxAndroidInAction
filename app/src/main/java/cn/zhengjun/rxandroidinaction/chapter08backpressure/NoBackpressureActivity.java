package cn.zhengjun.rxandroidinaction.chapter08backpressure;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/22  00:07
 * Summary : 在这里描述Class的主要功能
 */

public class NoBackpressureActivity {
    private static final String TAG = "NoBackpressureActivity";

    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                for (int i = 0; i < 19; i++) {
                    observableEmitter.onNext(i);
                }
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.single())
                .subscribe(new DefaultSubscriberImpl<Integer>(TAG){
                    @Override
                    public void onNext(Integer integer) {
                        super.onNext(integer);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
