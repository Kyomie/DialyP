package com.ytang.james.dailyp.base.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.ytang.james.dailyp.R;
import com.ytang.james.dailyp.base.mvp.BaseModel;
import com.ytang.james.dailyp.base.mvp.BasePresenter;
import com.ytang.james.dailyp.base.mvp.BaseView;

/**
 * Created by James on 16/10/17.
 */
public abstract class BaseAdapter<M extends BaseModel> extends DataAdapter<M> {

    private static final int TAG_KEY_PRESENTER;
    private static final int TAG_KEY_TYPE;
    private static final int NO_TYPE = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int viewType = this.getItemViewType(position);
        int convertViewType;
        if(convertView != null) {
            convertViewType = ((Integer)convertView.getTag(TAG_KEY_TYPE)).intValue();
        } else {
            convertViewType = -1;
        }

        BasePresenter presenter;
        BaseView baseView;
        View itemView;
        if(convertView instanceof BaseView && viewType == convertViewType) {
            baseView = (BaseView)convertView;
            itemView = baseView.getView();
            presenter = (BasePresenter) itemView.getTag(TAG_KEY_PRESENTER);
        } else {
            baseView = this.newView(parent, viewType);
            itemView = baseView.getView();
            presenter = this.newPresenter(viewType);
            itemView.setTag(TAG_KEY_PRESENTER, presenter);
            itemView.setTag(TAG_KEY_TYPE, Integer.valueOf(viewType));
        }

        this.doBind(presenter, baseView, (BaseModel)this.getItem(position));
        return itemView;

    }

    protected void doBind(BasePresenter presenter, BaseView view, BaseModel model){
        presenter.bind(view, model);
    }

    protected abstract BaseView newView(ViewGroup viewGroup,int var);

    protected abstract BasePresenter newPresenter(int var);

    static {
        TAG_KEY_PRESENTER = R.id.tag_card_presenter_key;
        TAG_KEY_TYPE = R.id.tag_card_type_key;
    }

}
