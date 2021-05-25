package com.amigos.amigos.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginModalClass {
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
        private List<User> user = null;

        public List<User> getUser() {
            return user;
        }

        public void setUser(List<User> user) {
            this.user = user;
        }

    }

    public class User {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("consumerMobile")
        @Expose
        private String consumerMobile;
        @SerializedName("password")
        @Expose
        private String password;

        @SerializedName("login_type")
        @Expose
        private String loginType;

        public Integer getId() {
            return id;
        }

        public String getLoginType() {
            return loginType;
        }

        public void setLoginType(String loginType) {
            this.loginType = loginType;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getConsumerMobile() {
            return consumerMobile;
        }

        public void setConsumerMobile(String consumerMobile) {
            this.consumerMobile = consumerMobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}