package com.ytang.james.dailyp.base.mvp;

/**
 * Created by James on 16/10/14.
 */
public abstract class BasePresenter<V extends BaseView, T extends BaseModel> {

    public BasePresenter() {}

    public void preBind(V view){}

    public void unBind(){}

    public abstract void bind(V v,T t);

}
