package cn.zhengjun.rxandroidinaction.chapter03creators;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/7  13:42
 * Summary : 在这里描述Class的主要功能
 */

public class RepeatUntil {
    private static final String TAG = "RepeatUntil";
    public static void main(String[] args) {
        final long start = System.currentTimeMillis();
        Observable.interval(500, TimeUnit.MILLISECONDS)
//                .take(5)
                .takeUntil(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        return getAsBoolean();
                    }

                    public boolean getAsBoolean() throws Exception {
                        long l = System.currentTimeMillis() - start;
//                        Log.i(TAG, "getAsBoolean: "+l);
                        return l > 5000;
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("RepeatUntil.accept  " + "aLong = [" + aLong + "]");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("RepeatUntil.accept  " + "throwable = [" + throwable + "]");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("RepeatUntil.run  " + "onComplete");
                    }
                });

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("================");
    }
}
