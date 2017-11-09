package com.ytang.james.dailyp.base.adapter;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by James on 16/10/17.
 */
public abstract class DataAdapter<T> extends BaseAdapter {

    protected List<T> mData;

    public List<T> getData() {
        return mData;
    }

    public void setData(List<T> mData) {
        this.mData = mData;
        this.notifyDataSetChanged();
    }

    public void clearData(){
        if(mData != null){
            mData.clear();
            this.notifyDataSetChanged();
        }
    }

    public DataAdapter() {
    }

    @Override
    public int getCount() {
        return mData == null? 0:mData.size();
    }

    public T getItem(int position){
        return getCount() <= position?null:mData.get(position);
    }

    public long getItemId(int position){
        return (long)position;
    }

}
