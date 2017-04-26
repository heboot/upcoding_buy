package upcoding.com.buy.api;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import upcoding.com.buy.BuildConfig;
import upcoding.com.buy.MyApplication;
import upcoding.com.buy.bean.CommonGuestBean;
import upcoding.com.buy.model.UserModel;
import upcoding.com.buy.service.UserService;
import upcoding.com.buy.utils.EncryptUtils;
import upcoding.com.buy.utils.LogUtils;
import upcoding.com.buy.utils.NetWorkUtils;

/**
 * Created by Heboot on 2016/12/27.
 */

public class ApiClient {


    private static String token;

    private static String secret;

    private static String TAG = ApiClient.class.getName();

    public static ServiceApi serviceApi;

    /**
     * 头部信息
     */
    private static Headers headers;


    /**
     * 用户ID
     */
    private static Integer uid = 0;

    public static ServiceApi getServiceApi(ApiRequest request) {
//        doSignature(request);
        if (serviceApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(getOkClient(request))
                    .baseUrl("http://api.codingfeel.com/")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(ServiceApi.class);
        }
        return serviceApi;
    }

    private static OkHttpClient getOkClient(ApiRequest request) {
        OkHttpClient client1;
        client1 = getUnsafeOkHttpClient(request);
        return client1;
    }

    private static OkHttpClient getUnsafeOkHttpClient(ApiRequest request) {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            }};
            // Install the all-trusting trust manager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
            okBuilder.readTimeout(20, TimeUnit.SECONDS);
            okBuilder.connectTimeout(10, TimeUnit.SECONDS);
            okBuilder.writeTimeout(20, TimeUnit.SECONDS);
            okBuilder.addInterceptor(new HttpHeadInterceptor(request));
//            okBuilder.addInterceptor(getInterceptor());
            okBuilder.sslSocketFactory(sslSocketFactory);
            okBuilder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
//                    Log.d("HttpUtils", "==come");
                    return true;
                }
            });

            return okBuilder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    static class HttpHeadInterceptor implements Interceptor {

        private ApiRequest apiRequest;

        private HttpHeadInterceptor(ApiRequest request) {
            apiRequest = request;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
            builder.addHeader("Accept", "application/json;versions=1");
            if (NetWorkUtils.isConnectedByState(MyApplication.getInstance())) {
                int maxAge = 60;
                builder.addHeader("Cache-Control", "public, max-age=" + maxAge);
            } else {
                int maxStale = 60 * 60 * 24 * 28;
                builder.addHeader("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);
            }
            // 可添加token
//            if (listener != null) {
//                builder.addHeader("token", listener.getToken());
//            }
            // 如有需要，添加请求头
//            builder.addHeader("a", HttpHead.getHeader(request.method()));


            doSignature(apiRequest, builder);

            return chain.proceed(builder.build());
        }
    }

    /**
     * 初始化头部信息
     */
    private static void doSignature(ApiRequest request, Request.Builder builder) {
        String time = String.valueOf(new Date().getTime());
        UserModel user = UserService.getInstance().getSharePrefUser(MyApplication.getInstance());
        String canonicalizedHeaders =
                "x-codingfeel-time:" + time + "\n" +
                        "x-codingfeel-terminal:" + "android\n" +
                        "x-codingfeel-version:" + MyApplication.getInstance().getVersion();


        if (request.isGuide()) {
            headers = new Headers.Builder()
                    .add("x-codingfeel-terminal", "android")
                    .add("x-codingfeel-version", MyApplication.getInstance().getVersion())
                    .add("Charset", "utf-8")
                    .add("x-codingfeel-time", time)
                    .build();
            builder.headers(headers);
            return;
        }


        if (user != null) {
            uid = user.getUid();
            token = user.getToken();
            secret = user.getSecret();
            canonicalizedHeaders =
                    "x-codingfeel-uid:" + uid + "\n" +
                            "x-codingfeel-time:" + time + "\n" +
                            "x-codingfeel-terminal:" + "android\n" +
                            "x-codingfeel-version:" + MyApplication.getInstance().getVersion();
        } else {
            return;
        }


        //请求参数格式化
        String canonicalizedParmas = getCanonicalizedParams(request);


        //拼接成要加密的内容
        String content =
                request.matchMethod(request.getMethod()) + "\n"
                        + getSignNetworkUri(request) + "\n"
                        + token + "\n"
                        + canonicalizedHeaders + "\n"
                        + canonicalizedParmas + "\n";

        LogUtils.e(TAG + "jiami", content);


        String signature = "";
        //调用签名工具类进行签名
        try {
            String result = new String(android.util.Base64.encode(EncryptUtils.hmacSHA1Encrypt(content, secret), android.util.Base64.DEFAULT | android.util.Base64.NO_WRAP));
            signature = URLEncoder.encode(result, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }


        headers = new Headers.Builder()
                .add("x-codingfeel-terminal", "android")
                .add("x-codingfeel-version", MyApplication.getInstance().getVersion())
                .add("Charset", "utf-8")
                .add("x-codingfeel-uid", String.valueOf(uid))
                .add("x-codingfeel-time", time)
                .add("Authorization", "Basic " + token + ":" + signature)
                .build();

        builder.headers(headers);

    }


    private static String getSignNetworkUri(ApiRequest httpRequest) {
        String signUri = httpRequest.getHttpUrl().replaceFirst(BuildConfig.HTTP_SERVER, "");
        if (signUri.contains("?")) {
            int index = signUri.indexOf("?");
            return signUri.substring(0, index);
        } else {
            return signUri;
        }
    }


    private static String getCanonicalizedParams(ApiRequest httpRequest) {


        List<String> paramList = new ArrayList<>();
        for (Map.Entry<String, String> entry : httpRequest.getParams().entrySet()) {
            try {
                paramList.add(new StringBuffer(entry.getKey()).append("=")
                        .append(URLEncoder.encode(entry.getValue(), "UTF-8")).toString().replaceAll("\\+", "%20"));

//            paramList.add(new StringBuffer(entry.getKey()).append("=")
//                    .append(entry.getValue()).toString());
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        Collections.sort(paramList);
        StringBuilder paramBuilder = new StringBuilder();
        boolean first = true;
        for (String item : paramList) {
            if (first) {
                first = false;
            } else {
                paramBuilder.append("&");
            }
            paramBuilder.append(item);
        }

        return paramBuilder.toString();
    }


}
