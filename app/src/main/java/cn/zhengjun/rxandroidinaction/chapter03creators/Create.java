package cn.zhengjun.rxandroidinaction.chapter03creators;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/7  12:12
 * Summary : 在这里描述Class的主要功能
 */

public class Create {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                try {
                    if (!observableEmitter.isDisposed()) {
                        for (int i = 0; i < 10; i++) {
                            observableEmitter.onNext(String.valueOf(i));
                        }
                        observableEmitter.onComplete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    observableEmitter.onError(e);
                }
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("Create.accept  " + "s = [" + s + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("Create.accept  onError" + "throwable = [" + throwable + "]");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("Create.run  " + "onComplete");
            }
        });
    }
}
