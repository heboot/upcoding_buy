package com.upcoding.buy.api;

import rx.Subscriber;

/**
 * Created by Heboot on 2017/3/24.
 */

public abstract class RxSubcribe<T> extends Subscriber<T> {


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();

        // TODO: 2017/3/24 判断网络情况
        // if(网络不可用) _onError("网络不可用");
        // else if(e instanseof ServerException)
        _onError(e.getMessage());
        //else _Error("其他错误");


    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String msg);

}
