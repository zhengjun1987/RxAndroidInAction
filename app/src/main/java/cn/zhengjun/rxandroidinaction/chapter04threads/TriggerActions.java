package cn.zhengjun.rxandroidinaction.chapter04threads;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.schedulers.TestScheduler;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  12:37
 * Summary : 在这里描述Class的主要功能
 */

public class TriggerActions {
    public static void main(String[] args) {
        TestScheduler testScheduler = new TestScheduler();
        testScheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                LogUtils.print("immediate");
            }
        });
        testScheduler.scheduleDirect(new Runnable() {
            @Override
            public void run() {
                LogUtils.print("20S");
            }
        },20, TimeUnit.SECONDS);
        LogUtils.print("testScheduler.now(TimeUnit.SECONDS) = " + testScheduler.now(TimeUnit.SECONDS));
        testScheduler.advanceTimeBy(20,TimeUnit.SECONDS);
        LogUtils.print("testScheduler.now(TimeUnit.SECONDS) = " + testScheduler.now(TimeUnit.SECONDS));
        testScheduler.triggerActions();
    }
}
