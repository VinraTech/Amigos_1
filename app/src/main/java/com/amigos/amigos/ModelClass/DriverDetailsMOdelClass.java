package com.amigos.amigos.ModelClass;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class DriverDetailsMOdelClass {
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

        @SerializedName("driver_id")
        @Expose
        private String driverId;
        @SerializedName("driver_name")
        @Expose
        private String driverName;
        @SerializedName("driver_email")
        @Expose
        private String driverEmail;
        @SerializedName("driver_mobile")
        @Expose
        private String driverMobile;
        @SerializedName("aadhar")
        @Expose
        private String aadhar;
        @SerializedName("pan")
        @Expose
        private String pan;
        @SerializedName("driving_license")
        @Expose
        private String drivingLicense;
        @SerializedName("date_of_both")
        @Expose
        private String dateOfBoth;
        @SerializedName("login_type")
        @Expose
        private String loginType;

        public String getDriverId() {
            return driverId;
        }

        public void setDriverId(String driverId) {
            this.driverId = driverId;
        }

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getDriverEmail() {
            return driverEmail;
        }

        public void setDriverEmail(String driverEmail) {
            this.driverEmail = driverEmail;
        }

        public String getDriverMobile() {
            return driverMobile;
        }

        public void setDriverMobile(String driverMobile) {
            this.driverMobile = driverMobile;
        }

        public String getAadhar() {
            return aadhar;
        }

        public void setAadhar(String aadhar) {
            this.aadhar = aadhar;
        }

        public String getPan() {
            return pan;
        }

        public void setPan(String pan) {
            this.pan = pan;
        }

        public String getDrivingLicense() {
            return drivingLicense;
        }

        public void setDrivingLicense(String drivingLicense) {
            this.drivingLicense = drivingLicense;
        }

        public String getDateOfBoth() {
            return dateOfBoth;
        }

        public void setDateOfBoth(String dateOfBoth) {
            this.dateOfBoth = dateOfBoth;
        }

        public String getLoginType() {
            return loginType;
        }

        public void setLoginType(String loginType) {
            this.loginType = loginType;
        }

    }
}
