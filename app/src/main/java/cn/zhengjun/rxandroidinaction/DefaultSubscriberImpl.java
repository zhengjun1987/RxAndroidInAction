package cn.zhengjun.rxandroidinaction;

import io.reactivex.observers.DefaultObserver;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  14:26
 * Summary : 在这里描述Class的主要功能
 */

public class DefaultSubscriberImpl<T> extends DefaultObserver<T> {
    private String tag;

    public DefaultSubscriberImpl(String tag) {
        this.tag = tag;
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.print(tag + ".onStart");
    }

    @Override
    public void onNext(T t) {
        LogUtils.print(tag + ".onNext  " + "t = [" + t + "]");
    }

    @Override
    public void onError(Throwable throwable) {
        LogUtils.print(tag + ".onError  " + "throwable = [" + throwable + "]");
    }

    @Override
    public void onComplete() {
        LogUtils.print(tag + ".onComplete  " + "");
    }
}
