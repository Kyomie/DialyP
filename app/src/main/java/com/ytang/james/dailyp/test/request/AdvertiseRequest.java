package com.ytang.james.dailyp.test.request;

import com.ytang.james.dailyp.test.model.AdvertiseResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by James on 16/10/24.
 *
 *  获取cp首页AD数据
 */
public interface AdvertiseRequest {

    String PATH = "/advertise/v3/materials";

    String KEY_ALIAS = "aliasName";
    String KEY_CITY_ID = "cityId";

    /**
     *
     * @param aliasName
     * @param cityId
     * @return
     */

    @GET(PATH)
    Call<AdvertiseResponseModel> getAdvertiseModel(
            @Query(KEY_ALIAS) String aliasName,
            @Query(KEY_CITY_ID) String cityId);

}
