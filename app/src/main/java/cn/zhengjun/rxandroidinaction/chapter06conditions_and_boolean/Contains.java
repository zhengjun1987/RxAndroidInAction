package cn.zhengjun.rxandroidinaction.chapter06conditions_and_boolean;

import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/12  15:28
 * Summary : 在这里描述Class的主要功能
 */

public class Contains {
    public static void main(String[] args) {
        Observable.range(20,35)
                .contains(30)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LogUtils.print("Contains.accept  " + "aBoolean = [" + aBoolean + "]");
                    }
                });
    }
}
