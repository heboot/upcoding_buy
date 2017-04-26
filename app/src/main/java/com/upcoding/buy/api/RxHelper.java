package com.upcoding.buy.api;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import com.upcoding.buy.exception.ServerException;
import com.upcoding.buy.model.BaseModel;

/**
 * Created by Heboot on 2017/3/23.
 */

public class RxHelper {


    public static Observable handleResult2(Observable<? extends BaseModel> observable) {

        return observable.flatMap(new Func1<BaseModel, Observable<BaseModel>>() {
            @Override
            public Observable<BaseModel> call(BaseModel result) {
                if (result.success()) {
                    return createData(result);
                } else {
                    return Observable.error(new ServerException(result.getError_msg()));
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

//        return observable.map(new Func1<BaseModel, Object>() {
//            @Override
//            public Object call(BaseModel baseModel) {
//                return createData(baseModel);
//            }
//        });
    }

//
//    /**
//     * 对结果进行预处理
//     *
//     * @param <T>
//     * @return
//     */
//    public static <T> Observable.Transformer<BaseModel<T>, T> handleResult() {
//
//        return new Observable.Transformer<BaseModel<T>, T>() {
//            @Override
//            public Observable<T> call(Observable<BaseModel<T>> tObservable) {
//                return tObservable.flatMap(new Func1<BaseModel<T>, Observable<T>>() {
//                    @Override
//                    public Observable<T> call(BaseModel<T> result) {
//                        if (result.success()) {
//                            return createData(result);
//                        } else {
//                            return Observable.error(new ServerException(result.getError_msg()));
//                        }
//                    }
//                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//            }
//        };
//    }


    /**
     * 创建成功的数据
     *
     * @param data
     * @return
     */
    private static Observable<BaseModel> createData(final BaseModel data) {
        return Observable.create(new Observable.OnSubscribe<BaseModel>() {
            @Override
            public void call(Subscriber<? super BaseModel> subscriber) {
                try {
                    if (data.success()) {
                        subscriber.onNext(data);
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new ServerException(data.getError_msg()));
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

    }

}
