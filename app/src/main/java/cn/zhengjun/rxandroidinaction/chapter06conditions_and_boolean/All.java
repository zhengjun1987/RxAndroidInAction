package cn.zhengjun.rxandroidinaction.chapter06conditions_and_boolean;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/12  15:17
 * Summary : 在这里描述Class的主要功能
 */

public class All {
    public static void main(String[] args) {
        Observable.range(1,10)
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer > 4;
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LogUtils.print("All.accept  " + "aBoolean = [" + aBoolean + "]");
                    }
                });

        LogUtils.print("==================================");
        Observable.intervalRange(1,10,1,1, TimeUnit.SECONDS)
                .all(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        LogUtils.print("All.test  " + "aLong = [" + aLong + "]");
                        return aLong < 9;
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LogUtils.print("All.accept  " + "aBoolean = [" + aBoolean + "]");
                    }
                });

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LogUtils.print("=================END=================");

    }
}
