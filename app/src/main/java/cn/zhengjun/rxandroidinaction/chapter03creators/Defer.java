package cn.zhengjun.rxandroidinaction.chapter03creators;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/7  14:12
 * Summary : 在这里描述Class的主要功能
 */

public class Defer {
    public static void main(String[] args) {
        Observable<String> defer;
//        defer = Observable.defer(new Callable<ObservableSource<String>>() {
//            @Override
//            public ObservableSource<String> call() throws Exception {
//                return Observable.just("Hello, Defer! " + System.currentTimeMillis());
//            }
//        });
        defer = Observable.just("Hello, Defer! " + System.currentTimeMillis());
        defer.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("Defer.accept  " + "s = [" + s + "]");
            }
        });
        defer.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("Defer.accept  " + "s = [" + s + "]");
            }
        });
    }
}
