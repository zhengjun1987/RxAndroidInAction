package cn.zhengjun.rxandroidinaction.chapter04threads;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.schedulers.TestScheduler;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/7  17:18
 * Summary : 在这里描述Class的主要功能
 */

public class Test {
    public static void main(String[] args) {
        TestScheduler testScheduler = new TestScheduler();
        testScheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                LogUtils.print("immediate");
            }
        });
        testScheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                LogUtils.print("5s");
            }
        },5, TimeUnit.SECONDS);
        testScheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                LogUtils.print("15s");
            }
        },15, TimeUnit.SECONDS);

        testScheduler.advanceTimeTo(1,TimeUnit.MILLISECONDS);
        LogUtils.print("testScheduler.now(TimeUnit.MILLISECONDS) = " + testScheduler.now(TimeUnit.MILLISECONDS));

        testScheduler.advanceTimeTo(2,TimeUnit.SECONDS);
        LogUtils.print("testScheduler.now(TimeUnit.SECONDS) = " + testScheduler.now(TimeUnit.SECONDS));

        testScheduler.advanceTimeTo(10,TimeUnit.SECONDS);
        LogUtils.print("testScheduler.now(TimeUnit.SECONDS) = " + testScheduler.now(TimeUnit.SECONDS));

        testScheduler.advanceTimeTo(15,TimeUnit.SECONDS);
        LogUtils.print("testScheduler.now(TimeUnit.SECONDS) = " + testScheduler.now(TimeUnit.SECONDS));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtils.print("testScheduler.now(TimeUnit.SECONDS) = " + testScheduler.now(TimeUnit.SECONDS));
    }
}
