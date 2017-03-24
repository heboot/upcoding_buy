package upcoding.com.buy.model;

import android.text.TextUtils;

/**
 * Created by Heboot on 16/6/27.
 */
public class BaseModel<T> {
    private Integer error;
    private String error_text;
    private String error_msg;
    private String result;
    private T datas;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getError_text() {
        return error_text;
    }

    public void setError_text(String error_text) {
        this.error_text = error_text;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public BaseModel(Integer error, String error_text, String error_msg) {
        this.error = error;
        this.error_text = error_text;
        this.error_msg = error_msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }

    public BaseModel() {

    }

    public boolean success() {
        return getError() == null && TextUtils.isEmpty(getError_text());
    }

}
