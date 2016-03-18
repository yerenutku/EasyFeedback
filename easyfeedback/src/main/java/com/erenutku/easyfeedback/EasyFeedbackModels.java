package com.erenutku.easyfeedback.easyfeedback;

import android.graphics.Bitmap;

/**
 * Created by yeutku on 19/01/16.
 */
public class EasyFeedbackModels {
    private String senderEmail;
    private String[] developerEmailList;
    private String userMessage;
    private String subject;
    private String androidVersionRelease;
    private int androidApiLevel;
    private String deviceModel;
    private String deviceBrand;
    private String deviceProduct;
    private String appName;
    private String appVersion;
    private Bitmap image;

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String[] getDeveloperEmailList() {
        return developerEmailList;
    }

    public void setDeveloperEmailList(String[] developerEmailList) {
        this.developerEmailList = developerEmailList;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAndroidVersionRelease() {
        return androidVersionRelease;
    }

    protected void setAndroidVersionRelease(String androidVersionRelease) {
        this.androidVersionRelease = androidVersionRelease;
    }

    public int getAndroidApiLevel() {
        return androidApiLevel;
    }

    protected void setAndroidApiLevel(int androidApiLevel) {
        this.androidApiLevel = androidApiLevel;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    protected void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    protected void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getDeviceProduct() {
        return deviceProduct;
    }

    protected void setDeviceProduct(String deviceProduct) {
        this.deviceProduct = deviceProduct;
    }

    public String getAppName() {
        return appName;
    }

    protected void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    protected void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
