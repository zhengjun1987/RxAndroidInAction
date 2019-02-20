package cn.zhengjun.rxandroidinaction;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  14:26
 * Summary : 在这里描述Class的主要功能
 */

public class DefaultSubscriberImpl<T> implements Observer<T> {
    private String tag;
    private Disposable disposable;

    public DefaultSubscriberImpl(String tag) {
        this.tag = tag;
    }

    public void dispose() {
        disposable.dispose();
    }

    public boolean isDisposed() {
        return disposable.isDisposed();
    }

    protected void onStart() {
        LogUtils.print(tag + ".onStart");
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        this.disposable = disposable;
        this.onStart();
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
