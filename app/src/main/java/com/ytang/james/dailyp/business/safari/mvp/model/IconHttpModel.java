package com.ytang.james.dailyp.business.safari.mvp.model;

import com.ytang.james.dailyp.base.mvp.BaseErrorModel;
import com.ytang.james.dailyp.base.mvp.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by James on 16/10/19.
 */
public class IconHttpModel extends BaseErrorModel implements Serializable, BaseModel {

    private List<IconModel> data;

    public List<IconModel> getData() {
        return data;
    }

    public void setData(List<IconModel> data) {
        this.data = data;
    }
}
