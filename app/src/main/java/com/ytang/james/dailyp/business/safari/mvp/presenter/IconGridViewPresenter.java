package com.ytang.james.dailyp.business.safari.mvp.presenter;

import com.ytang.james.dailyp.base.mvp.BasePresenter;
import com.ytang.james.dailyp.business.safari.adapter.IconGridviewAdapter;
import com.ytang.james.dailyp.business.safari.mvp.model.IconHttpModel;
import com.ytang.james.dailyp.business.safari.mvp.model.IconModel;
import com.ytang.james.dailyp.business.safari.view.IconGridViewContainer;

import java.util.List;

/**
 * Created by James on 16/10/19.
 */
public class IconGridViewPresenter extends BasePresenter<IconGridViewContainer, IconHttpModel> {

    @Override
    public void bind(IconGridViewContainer view, IconHttpModel model) {
        if(model == null){
            return;
        }
        List<IconModel> modelList = model.getData();
        if(modelList == null || modelList.size() == 0){
            return;
        }

        IconGridviewAdapter adapter = new IconGridviewAdapter();
        adapter.setData(model.getData());
        view.getGridView().setAdapter(adapter);
    }
}
