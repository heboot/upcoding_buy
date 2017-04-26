package com.upcoding.buy.bean;


import java.util.List;

import com.upcoding.buy.model.BaseModel;
import com.upcoding.buy.model.InfoModel;

/**
 * Created by Heboot on 16/7/10.
 */
public class InfoHomeBean extends BaseModel {

    private boolean lastPage;
    private List<InfoModel> info;

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public List<InfoModel> getInfo() {
        return info;
    }

    public void setInfo(List<InfoModel> info) {
        this.info = info;
    }
}
