package cn.zhengjun.rxandroidinaction.chapter03creators;

import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/7  12:19
 * Summary : 在这里描述Class的主要功能
 */

public class Just {
    public static void main(String[] args) {
        System.out.println("Just.main  " + "args = [" + Arrays.toString(args) + "]");
        Observable.just("Hello, RxJava")
//                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("Just.accept  " + "s = [" + s + "]");
                    }
                });

        Observable.just(1,2,3,4,5,6,7,8,9,10)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Just.accept  " + "integer = [" + integer + "]");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Just.accept  " + "throwable = [" + throwable + "]");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Just.run  " + "onCompleted");
                    }
                });
    }
}
