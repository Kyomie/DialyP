package com.ytang.james.dailyp.test.model;

import android.text.TextUtils;

import com.ytang.james.dailyp.base.mvp.BaseModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 16/10/24.
 */
public class AdvertiseDetailModel implements BaseModel, Serializable {
    private long resourceId;
    private String aliasName;
    private int number;
    private List<AdvertisePlanModel> plans;

    public AdvertiseDetailModel() {
    }

    public List<AdvertisePlanModel> getPlans() {
        if(this.plans == null) {
            return null;
        } else {
            ArrayList list = new ArrayList();

            for(int i = 0; i < this.plans.size(); ++i) {
                AdvertisePlanModel plan = (AdvertisePlanModel)this.plans.get(i);
                if(!TextUtils.isEmpty(plan.getUrlContent())) {
                    list.add(plan);
                }
            }

            return list;
        }
    }
}
