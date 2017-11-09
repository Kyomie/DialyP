package com.ytang.james.dailyp.business.safari.mvp.model;

import com.ytang.james.dailyp.base.mvp.BaseErrorModel;
import com.ytang.james.dailyp.base.mvp.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by James on 16/10/20.
 */
public class ADHttpModel extends BaseErrorModel implements Serializable, BaseModel {

    private List<ADModel> data;

    public List<ADModel> getData() {
        return data;
    }

    public void setData(List<ADModel> data) {
        this.data = data;
    }
}