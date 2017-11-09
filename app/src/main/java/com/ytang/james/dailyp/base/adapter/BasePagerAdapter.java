package com.ytang.james.dailyp.base.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.ytang.james.dailyp.R;
import com.ytang.james.dailyp.base.mvp.BaseModel;
import com.ytang.james.dailyp.base.mvp.BasePresenter;
import com.ytang.james.dailyp.base.mvp.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 16/10/14.
 */
public abstract class BasePagerAdapter<M extends BaseModel> extends PagerAdapter {

    public static final int TAG_KEY_POSITION;
    private static final int TAG_KEY_CONTROLLER;

    protected List<M> mData = new ArrayList();

    public List<M> getData() {
        return mData;
    }

    public void setData(List<M> data) {
        this.mData = data;
    }

    public void appendData(List<M> data){
        if (data != null) {
            mData.addAll(data);
            this.notifyDataSetChanged();
        }
    }

    public void clearData(){
        if(mData != null){
            mData.clear();
        }
        this.notifyDataSetChanged();
    }

    public BasePagerAdapter() {
    }

    @Override
    public int getCount() {
        return mData == null?0:mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object.equals(view);
    }

    public M getItem(int position){
        return this.getCount() <= position?null:mData.get(position);
    }

    public int getItemType(int position){
        return 0;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        if(object instanceof BaseView) {
            Object presenter = ((BaseView)object).getView().getTag(TAG_KEY_CONTROLLER);
            if(presenter instanceof BasePresenter) {
                ((BasePresenter)presenter).unBind();
            }
        }

        container.removeView((View)object);
    }

    public Object instantiateItem(ViewGroup container, int position) {
        BaseView baseView = this.newView(container, this.getItemType(position));
        baseView.getView().setTag(TAG_KEY_POSITION, Integer.valueOf(position));
        BasePresenter presenter = this.newPresenter(this.getItemType(position));
        baseView.getView().setTag(TAG_KEY_CONTROLLER, presenter);
        this.doBind(presenter, baseView, this.getItem(position));
        container.addView(baseView.getView());
        return baseView.getView();
    }

    protected void doBind(BasePresenter presenter, BaseView baseView, M baseModel) {
        presenter.bind(baseView, baseModel);
    }

    protected abstract BaseView newView(ViewGroup viewGroup,int var);

    protected abstract BasePresenter newPresenter(int var);

    static {
        TAG_KEY_POSITION = R.id.tag_pager_position_key;
        TAG_KEY_CONTROLLER = R.id.tag_presenter_key;
    }

}
