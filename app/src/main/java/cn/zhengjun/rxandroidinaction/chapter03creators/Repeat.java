package cn.zhengjun.rxandroidinaction.chapter03creators;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/7  13:28
 * Summary : 在这里描述Class的主要功能
 */

public class Repeat {
    public static void main(String[] args) {
        Observable.just("hello repeat")
//                .repeat()
                .repeat(3)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("Repeat.accept  " + "s = [" + s + "]");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Repeat.accept  " + "throwable = [" + throwable + "]");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Repeat.run  " + "");
                    }
                });

        Observable.range(0,10)
                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                        return Observable.timer(10, TimeUnit.SECONDS);
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("Repeat.accept  " + "integer = [" + integer + "]");
            }
        });
        try {
            Thread.sleep(22000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("===========================");
    }
}
