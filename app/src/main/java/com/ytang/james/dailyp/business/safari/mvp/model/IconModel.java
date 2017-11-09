package com.ytang.james.dailyp.business.safari.mvp.model;

import com.ytang.james.dailyp.base.mvp.BaseModel;

import java.io.Serializable;

/**
 * Created by James on 16/10/17.
 */
public class IconModel implements Serializable, BaseModel {

    private String name;
    private int id;
    private int resId;

    public IconModel() {
    }

    public IconModel(String name, int id, int resId) {
        this.name = name;
        this.id = id;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getResId() {
        return resId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
