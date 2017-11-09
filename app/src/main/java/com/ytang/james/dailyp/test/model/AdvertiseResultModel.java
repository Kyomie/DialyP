package com.ytang.james.dailyp.test.model;

import com.ytang.james.dailyp.base.mvp.BaseModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by James on 16/10/24.
 */
public class AdvertiseResultModel implements BaseModel, Serializable {
    private long cityId;
    private long plazaId;
    private Map<Integer, AdvertiseDetailModel> datas;

    public AdvertiseResultModel() {
    }

    public List<AdvertisePlanModel> getPlans() {
        if(this.datas == null) {
            return null;
        } else {
            ArrayList list = new ArrayList();
            Iterator var2 = this.datas.entrySet().iterator();

            while(var2.hasNext()) {
                Map.Entry entry = (Map.Entry)var2.next();
                list.addAll(((AdvertiseDetailModel)entry.getValue()).getPlans());
            }

            return list;
        }
    }
}
