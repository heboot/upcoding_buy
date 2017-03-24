package upcoding.com.buy.bean;


import upcoding.com.buy.model.BaseModel;

/**
 * Created by Heboot on 16/8/1.
 */
public class CommonGuestBean<T> extends BaseModel<T> {

    private String appVersion;
    private String appDownloadUrl;
    private String appIsForceUpdate;
    private String appUpdateNotes;

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppDownloadUrl() {
        return appDownloadUrl;
    }

    public void setAppDownloadUrl(String appDownloadUrl) {
        this.appDownloadUrl = appDownloadUrl;
    }

    public String getAppIsForceUpdate() {
        return appIsForceUpdate;
    }

    public void setAppIsForceUpdate(String appIsForceUpdate) {
        this.appIsForceUpdate = appIsForceUpdate;
    }

    public String getAppUpdateNotes() {
        return appUpdateNotes;
    }

    public void setAppUpdateNotes(String appUpdateNotes) {
        this.appUpdateNotes = appUpdateNotes;
    }
}
