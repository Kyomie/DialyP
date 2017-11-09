package com.ytang.james.dailyp.test.model;

import com.ytang.james.dailyp.base.mvp.BaseErrorModel;
import com.ytang.james.dailyp.base.mvp.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by James on 16/10/24.
 */
public class AdvertiseResponseModel extends BaseErrorModel implements BaseModel, Serializable {
    private AdvertiseResultModel data;

    public AdvertiseResponseModel() {
    }

    public AdvertiseResultModel getData() {
        return this.data;
    }

    public List<AdvertisePlanModel> getPlans() {
        return this.data == null?null:this.data.getPlans();
    }
}
