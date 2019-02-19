package cn.zhengjun.rxandroidinaction.chapter06conditions_and_boolean;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/12  15:35
 * Summary : 在这里描述Class的主要功能
 */

public class Amb {
    private static final String TAG = "Amb";
    public static void main(String[] args) {
        Observable.ambArray(
                Observable.range(11,5).delay(400,TimeUnit.MILLISECONDS),
                Observable.intervalRange(1,5,1,1, TimeUnit.SECONDS),
                Observable.error(new Exception("Unexpected Exit"))
        )
                .subscribe(new DefaultSubscriberImpl<>(TAG));

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LogUtils.print("=======================");
    }
}
