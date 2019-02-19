package cn.zhengjun.rxandroidinaction.chapter04threads;

import java.util.Arrays;

import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/7  16:18
 * Summary : 在这里描述Class的主要功能
 */

public class Scheduler {
    public static void main(String[] args) {
        LogUtils.print("Scheduler.main  " + "args = [" + Arrays.toString(args) + "]");
        Observable.just("Hello","World")
                .subscribeOn(Schedulers.single())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        LogUtils.print("====Scheduler.apply  " + "s = [" + s + "]");
                        Thread.sleep(40);
                        return s.toUpperCase();
                    }
                })
//                .subscribeOn(Schedulers.single())
                .observeOn(Schedulers.newThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        s = s + " Tony";
                        LogUtils.print("s = " + s);
                        return s;
                    }
                })
                .subscribeOn(Schedulers.single())
                .observeOn(Schedulers.single())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtils.print("Scheduler.accept  " + "s = [" + s + "]");
                    }
                });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtils.print("========================");
    }
}
