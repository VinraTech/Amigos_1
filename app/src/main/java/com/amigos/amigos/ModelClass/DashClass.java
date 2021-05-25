package com.amigos.amigos.ModelClass;

public class DashClass {

    String servicecenter,description;
    int image;

    public DashClass(String servicecenter, String description, int image) {
        this.servicecenter = servicecenter;
        this.description = description;
        this.image = image;
    }

    public String getServicecenter() {
        return servicecenter;
    }

    public void setServicecenter(String servicecenter) {
        this.servicecenter = servicecenter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
