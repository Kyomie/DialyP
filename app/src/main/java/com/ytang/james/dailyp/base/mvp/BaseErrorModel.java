package com.ytang.james.dailyp.base.mvp;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by James on 16/10/19.
 */
public class BaseErrorModel implements Serializable {
    private int status;
    private String message;
    private String msg;

    public BaseErrorModel() {
    }

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return !TextUtils.isEmpty(this.message)?this.message:(!TextUtils.isEmpty(this.msg)?this.msg:null);
    }
}