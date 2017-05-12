package com.upcoding.buy.bean;


import com.upcoding.buy.model.BaseModel;
import com.upcoding.buy.model.PostModel;

import java.util.List;

/**
 * Created by Heboot on 16/7/12.
 */
public class PostHomeBean extends BaseModel {


    private boolean lastPage;
    private List<PostModel> post;


    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public List<PostModel> getPost() {
        return post;
    }

    public void setPost(List<PostModel> post) {
        this.post = post;
    }
}
