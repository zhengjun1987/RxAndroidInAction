package cn.zhengjun.rxandroidinaction.chapter04threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.TestScheduler;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  12:10
 * Summary : TestScheduler类的advanceTimeBy方法
 */

public class AdvanceTimeBy {
    public static void main(String[] args) {
        TestScheduler testScheduler = new TestScheduler();
        final AtomicLong atomicLong = new AtomicLong();
        Observable.timer(2, TimeUnit.SECONDS,testScheduler)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogUtils.print("AdvanceTimeBy.accept  " + "aLong = [" + aLong + "]");
                        atomicLong.incrementAndGet();
                    }
                });
        LogUtils.print("atomicLong.get() = " + atomicLong.get());
        LogUtils.print("virtual time = " + testScheduler.now(TimeUnit.SECONDS));

        testScheduler.advanceTimeBy(1,TimeUnit.SECONDS);

        LogUtils.print("atomicLong.get() = " + atomicLong.get());
        LogUtils.print("virtual time = " + testScheduler.now(TimeUnit.SECONDS));

        testScheduler.advanceTimeBy(1,TimeUnit.SECONDS);

        LogUtils.print("atomicLong.get() = " + atomicLong.get());
        LogUtils.print("virtual time = " + testScheduler.now(TimeUnit.SECONDS));

        testScheduler.advanceTimeBy(1,TimeUnit.SECONDS);

        LogUtils.print("atomicLong.get() = " + atomicLong.get());
        LogUtils.print("virtual time = " + testScheduler.now(TimeUnit.SECONDS));

        testScheduler.advanceTimeBy(-1,TimeUnit.SECONDS);

        LogUtils.print("atomicLong.get() = " + atomicLong.get());
        LogUtils.print("virtual time = " + testScheduler.now(TimeUnit.SECONDS));

        testScheduler.advanceTimeBy(-1,TimeUnit.SECONDS);

        LogUtils.print("atomicLong.get() = " + atomicLong.get());
        LogUtils.print("virtual time = " + testScheduler.now(TimeUnit.SECONDS));

    }
}
