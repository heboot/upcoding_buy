package com.upcoding.buy.service;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import com.upcoding.buy.BuildConfig;
import com.upcoding.buy.api.ApiClient;
import com.upcoding.buy.api.ApiRequest;
import com.upcoding.buy.api.RxHelper;
import com.upcoding.buy.bean.InfoHomeBean;
import com.upcoding.buy.model.InfoModel;

/**
 * Created by Heboot on 16/7/10.
 */
public class InfoService extends HttpService {

    private static InfoService infoService;

    private HashMap<String, InfoModel> infoModelHashMap = new HashMap<>();


    public static InfoService getInstance() {
        if (infoService == null) {
            infoService = new InfoService();
        }
        return infoService;
    }

    public Map<String, InfoModel> loadInfoModelsSync() {
        return new HashMap<String, InfoModel>(this.infoModelHashMap);
    }

    /**
     * 保存到内存
     *
     * @param infoModel
     */
    public void saveInfoModel(InfoModel infoModel) {
        this.infoModelHashMap.put(infoModel.getInfoId(), infoModel);
    }

    /**
     * 更新优惠信息评论数
     *
     * @param infoId
     * @param count
     */
    public void updateInfoModelCommentCount(String infoId, int count) {
        this.infoModelHashMap.get(infoId).setCommentCount(count);
    }

    /**
     * 更新优惠信息赞数
     *
     * @param infoId
     * @param count
     */
    public void updateInfoModelEvaCount(String infoId, int count, boolean isEva) {
        this.infoModelHashMap.get(infoId).setEva(isEva);
        this.infoModelHashMap.get(infoId).setEvaCount(count);
    }

    /**
     * 更新优惠信息分享数
     *
     * @param infoId
     * @param count
     */
    public void updateInfoModelShareCount(String infoId, int count) {
        this.infoModelHashMap.get(infoId).setShareCount(count);
    }


    public Observable<InfoHomeBean> home(int pageSize, int pageNo, final String keywords) {
        ApiRequest httpRequest = new ApiRequest(BuildConfig.HTTP_SERVER + ACTION_INFO_HOME, ApiRequest.Method.GET, true);
        httpRequest.addParams(PARAM_PAGESIZE, String.valueOf(pageSize));
        httpRequest.addParams(PARAM_PAGENO, String.valueOf(pageNo));
        httpRequest.addParams(PARAM_APP, "1");
        httpRequest.addParams(PARAM_KEYWORDS, keywords);
        return RxHelper.handleResult2(ApiClient.getServiceApi(httpRequest).infoHome());
    }

//    public void detail(String infoId) {
//        HttpRequest httpRequest = new HttpRequest(BuildConfig.HTTP_SERVER + ACTION_INFO_DETAIL, HttpRequest.Method.GET, true);
//        httpRequest.addParams(PARAM_INFOID, infoId);
//        httpRequest.addParams(PARAM_APP, "1");
//
//        FormBody formBody = new FormBody.Builder()
//                .add(PARAM_INFOID, infoId)
//                .add(PARAM_APP, "1")
//                .build();
//        httpRequest.setFormBody(formBody);
//
//        HttpUtils.getInstance().execute(httpRequest, new HttpResponse() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(String result) {
//                InfoModel infoModel = JSON.parseObject(result, InfoModel.class);
//                EventBus.getDefault().post(new InfoEvent.InfoDetailEvent(infoModel));
//            }
//        });
//    }
//
//
//    public void eva(String infoId) {
//        HttpRequest httpRequest = new HttpRequest(BuildConfig.HTTP_SERVER + ACTION_INFO_EVA, HttpRequest.Method.POST, false);
//        httpRequest.addParams(PARAM_INFOID, infoId);
//
//        FormBody formBody = new FormBody.Builder()
//                .add(PARAM_INFOID, infoId)
//                .build();
//        httpRequest.setFormBody(formBody);
//
//        HttpUtils.getInstance().execute(httpRequest, new HttpResponse() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//            }
//
//            @Override
//            public void onResponse(String result) {
//                BaseModel baseModel = JSON.parseObject(result, BaseModel.class);
//                EventBus.getDefault().post(new BaseEvent.BasicEvent(baseModel, BasicEventEnum.INFO_EVA));
//            }
//        });
//    }
//
//    public void read(String infoId) {
//        HttpRequest httpRequest = new HttpRequest(BuildConfig.HTTP_SERVER + ACTION_INFO_READ, HttpRequest.Method.POST, true);
//        httpRequest.addParams(PARAM_INFOID, infoId);
//
//        FormBody formBody = new FormBody.Builder()
//                .add(PARAM_INFOID, infoId)
//                .build();
//        httpRequest.setFormBody(formBody);
//
//        HttpUtils.getInstance().execute(httpRequest, new HttpResponse() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//            }
//
//            @Override
//            public void onResponse(String result) {
//            }
//        });
//    }
//
//
//    public void info(String title, String content, String goodsUrl) {
//        HttpRequest httpRequest = new HttpRequest(BuildConfig.HTTP_SERVER + ACTION_INFO_INFO, HttpRequest.Method.POST, false);
//        httpRequest.addParams(PARAM_TITLE, title);
//        httpRequest.addParams(PARAM_CONTENT, content);
//        httpRequest.addParams(PARAM_GOODS_URL, goodsUrl);
//
//        FormBody formBody = new FormBody.Builder()
//                .add(PARAM_TITLE, title)
//                .add(PARAM_CONTENT, content)
//                .add(PARAM_GOODS_URL, goodsUrl)
//                .build();
//        httpRequest.setFormBody(formBody);
//
//        HttpUtils.getInstance().execute(httpRequest, new HttpResponse() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//            }
//
//            @Override
//            public void onResponse(String result) {
//                BaseModel baseModel = JSON.parseObject(result, BaseModel.class);
//                EventBus.getDefault().post(new BaseEvent.BasicEvent(baseModel, BasicEventEnum.INFO_INFO));
//            }
//        });
//    }
//
//    public void comment(String infoId, String content, Integer reUid) {
//        HttpRequest httpRequest = new HttpRequest(BuildConfig.HTTP_SERVER + ACTION_INFO_COMMENT, HttpRequest.Method.POST, false);
//        httpRequest.addParams(PARAM_INFOID, infoId);
//        httpRequest.addParams(PARAM_CONTENT, content);
//        httpRequest.addParams(PARAM_REUID, reUid == null ? "" : String.valueOf(reUid));
//
//        FormBody formBody = new FormBody.Builder()
//                .add(PARAM_INFOID, infoId)
//                .add(PARAM_CONTENT, content)
//                .add(PARAM_REUID, reUid == null ? "" : String.valueOf(reUid))
//                .build();
//        httpRequest.setFormBody(formBody);
//
//        HttpUtils.getInstance().execute(httpRequest, new HttpResponse() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//            }
//
//            @Override
//            public void onResponse(String result) {
//                BaseModel baseModel = JSON.parseObject(result, BaseModel.class);
//                EventBus.getDefault().post(new BaseEvent.BasicEvent(baseModel, BasicEventEnum.INFO_COMMENT));
//            }
//        });
//    }
//
//    public void getComments(String infoId, int pageSize, int pageNo) {
//        HttpRequest httpRequest = new HttpRequest(BuildConfig.HTTP_SERVER + ACTION_INFO_COMMENT, HttpRequest.Method.GET, true);
//        httpRequest.addParams(PARAM_PAGESIZE, String.valueOf(pageSize));
//        httpRequest.addParams(PARAM_PAGENO, String.valueOf(pageNo));
//        httpRequest.addParams(PARAM_INFOID, infoId);
//
//        FormBody formBody = new FormBody.Builder()
//                .add(PARAM_INFOID, infoId)
//                .add(PARAM_PAGESIZE, String.valueOf(pageSize))
//                .add(PARAM_PAGENO, String.valueOf(pageNo))
//                .build();
//        httpRequest.setFormBody(formBody);
//
//        HttpUtils.getInstance().execute(httpRequest, new HttpResponse() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//            }
//
//            @Override
//            public void onResponse(String result) {
//                InfoCommentBean infoCommentBean = JSON.parseObject(result, InfoCommentBean.class);
//                EventBus.getDefault().post(new InfoEvent.InfoCommentsEvent(infoCommentBean));
//            }
//        });
//
//    }
//
//
//    public void share(String infoId) {
//        HttpRequest httpRequest = new HttpRequest(BuildConfig.HTTP_SERVER + ACTION_INFO_SHARE, HttpRequest.Method.POST, false);
//        httpRequest.addParams(PARAM_INFOID, infoId);
//
//        FormBody formBody = new FormBody.Builder()
//                .add(PARAM_INFOID, infoId)
//                .build();
//        httpRequest.setFormBody(formBody);
//
//        HttpUtils.getInstance().execute(httpRequest, new HttpResponse() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//            }
//
//            @Override
//            public void onResponse(String result) {
//                BaseModel baseModel = JSON.parseObject(result, BaseModel.class);
//                EventBus.getDefault().post(new BaseEvent.BasicEvent(baseModel, BasicEventEnum.INFO_SHARE));
//            }
//        });
//    }


}
