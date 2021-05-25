package com.amigos.amigos.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class RegistrationModalClass {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private Result result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }


    public class Result {

        @SerializedName("user")
        @Expose
        private User user;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }


    }
    public class User {

        @SerializedName("consumerEmail")
        @Expose
        private String consumerEmail;
        @SerializedName("consumerMobile")
        @Expose
        private Long consumerMobile;
        @SerializedName("consumerName")
        @Expose
        private String consumerName;
        @SerializedName("consumerAddress")
        @Expose
        private String consumerAddress;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("login_type")
        @Expose
        private String loginType;
        @SerializedName("ipAddress")
        @Expose
        private String ipAddress;
        @SerializedName("fcmToken")
        @Expose
        private String fcmToken;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("lattitude")
        @Expose
        private String lattitude;
        @SerializedName("deviceID")
        @Expose
        private String deviceID;
        @SerializedName("appVersion")
        @Expose
        private String appVersion;

        public String getConsumerEmail() {
            return consumerEmail;
        }

        public void setConsumerEmail(String consumerEmail) {
            this.consumerEmail = consumerEmail;
        }

        public Long getConsumerMobile() {
            return consumerMobile;
        }

        public void setConsumerMobile(Long consumerMobile) {
            this.consumerMobile = consumerMobile;
        }

        public String getConsumerName() {
            return consumerName;
        }

        public void setConsumerName(String consumerName) {
            this.consumerName = consumerName;
        }

        public String getConsumerAddress() {
            return consumerAddress;
        }

        public void setConsumerAddress(String consumerAddress) {
            this.consumerAddress = consumerAddress;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getLoginType() {
            return loginType;
        }

        public void setLoginType(String loginType) {
            this.loginType = loginType;
        }

        public String getIpAddress() {
            return ipAddress;
        }

        public void setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
        }

        public String getFcmToken() {
            return fcmToken;
        }

        public void setFcmToken(String fcmToken) {
            this.fcmToken = fcmToken;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLattitude() {
            return lattitude;
        }

        public void setLattitude(String lattitude) {
            this.lattitude = lattitude;
        }

        public String getDeviceID() {
            return deviceID;
        }

        public void setDeviceID(String deviceID) {
            this.deviceID = deviceID;
        }

        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

    }
}

