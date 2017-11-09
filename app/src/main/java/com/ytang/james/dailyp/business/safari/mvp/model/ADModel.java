package com.ytang.james.dailyp.business.safari.mvp.model;

import com.ytang.james.dailyp.base.mvp.BaseModel;

/**
 * Created by James on 16/10/20.
 */
public class ADModel implements BaseModel {

    private String descript;
    private int id;
    private int resId;

    public ADModel(){}

    public ADModel(String descript, int id, int resId) {
        this.descript = descript;
        this.id = id;
        this.resId = resId;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
