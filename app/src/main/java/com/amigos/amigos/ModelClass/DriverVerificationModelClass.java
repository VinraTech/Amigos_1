package com.amigos.amigos.ModelClass;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class DriverVerificationModelClass {
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

        @SerializedName("Driver")
        @Expose
        private Driver driver;

        public Driver getDriver() {
            return driver;
        }

        public void setDriver(Driver driver) {
            this.driver = driver;
        }

    }
    public class Driver {

        @SerializedName("shower")
        @Expose
        private String shower;
        @SerializedName("charger")
        @Expose
        private String charger;
        @SerializedName("imageUrl")
        @Expose
        private String imageUrl;
        @SerializedName("powerbank")
        @Expose
        private String powerbank;
        @SerializedName("uniform")
        @Expose
        private String uniform;
        @SerializedName("bagPack")
        @Expose
        private String bagPack;
        @SerializedName("swipingMachine")
        @Expose
        private String swipingMachine;
        @SerializedName("idCard")
        @Expose
        private String idCard;
        @SerializedName("bodyTemperature")
        @Expose
        private String bodyTemperature;
        @SerializedName("driverIma")
        @Expose
        private String driverIma;

        public String getShower() {
            return shower;
        }

        public void setShower(String shower) {
            this.shower = shower;
        }

        public String getCharger() {
            return charger;
        }

        public void setCharger(String charger) {
            this.charger = charger;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getPowerbank() {
            return powerbank;
        }

        public void setPowerbank(String powerbank) {
            this.powerbank = powerbank;
        }

        public String getUniform() {
            return uniform;
        }

        public void setUniform(String uniform) {
            this.uniform = uniform;
        }

        public String getBagPack() {
            return bagPack;
        }

        public void setBagPack(String bagPack) {
            this.bagPack = bagPack;
        }

        public String getSwipingMachine() {
            return swipingMachine;
        }

        public void setSwipingMachine(String swipingMachine) {
            this.swipingMachine = swipingMachine;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getBodyTemperature() {
            return bodyTemperature;
        }

        public void setBodyTemperature(String bodyTemperature) {
            this.bodyTemperature = bodyTemperature;
        }

        public String getDriverIma() {
            return driverIma;
        }

        public void setDriverIma(String driverIma) {
            this.driverIma = driverIma;
        }

    }
}
