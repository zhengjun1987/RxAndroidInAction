package cn.zhengjun.rxandroidinaction.chapter03creators;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/7  12:27
 * Summary : 在这里描述Class的主要功能
 */

public class From {
    public static void main(String[] args) {
        Observable.fromArray("hello", "from")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("From.accept  " + "s = [" + s + "]");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("From.accept  " + "throwable = [" + throwable + "]");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("From.run onCompleted");
                    }
                });

        ArrayList<Integer> integers = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            integers.add(i);
        }
        Observable.fromIterable(integers)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("From.accept  " + "integer = [" + integer + "]");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("From.accept  " + "throwable = [" + throwable + "]");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("From.run  " + "onCompleted");
                    }
                });

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> stringFuture = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("模拟一些耗时任务...");
                Thread.sleep(5000);

                return "RESULT OK";
            }
        });

//        Observable.fromFuture(stringFuture)
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        System.out.println("From.accept  " + "s = [" + s + "]");
//                    }
//                });

        Observable.fromFuture(stringFuture,1, TimeUnit.SECONDS)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("From.accept  " + "s = [" + s + "]");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("From.accept  " + "throwable = [" + throwable + "]");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("From.run  " + "onCompleted");
                    }
                });
    }
}
