package com.amigos.amigos.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class DriverOnDutyDetails {
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
        @SerializedName("driver_off")
        @Expose
        private String driverOff;
        @SerializedName("driverof_time")
        @Expose
        private String driverofTime;

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

        public String getDriverOff() {
            return driverOff;
        }

        public void setDriverOff(String driverOff) {
            this.driverOff = driverOff;
        }

        public String getDriverofTime() {
            return driverofTime;
        }

        public void setDriverofTime(String driverofTime) {
            this.driverofTime = driverofTime;
        }

    }
}
