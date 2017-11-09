package com.ytang.james.dailyp.test.model;

import com.ytang.james.dailyp.base.mvp.BaseModel;

import java.io.Serializable;

/**
 * Created by James on 16/10/24.
 */
public class AdvertisePlanModel implements BaseModel, Serializable {
    private int position;
    private String serialName;
    private String startDate;
    private String endDate;
    private String businessName;
    private String image;
    private String urlSort;
    private String urlContent;
    private String target;
    private int defaultImg;
    private String plazaId;

    public AdvertisePlanModel() {
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrlSort() {
        return this.urlSort;
    }

    public String getUrlContent() {
        return this.urlContent;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getDefaultImg() {
        return this.defaultImg;
    }

    public String getPlazaId() {
        return this.plazaId;
    }

    public void setPlazaId(String plazaId) {
        this.plazaId = plazaId;
    }
}
