package com.ytang.james.dailyp.business.safari.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ytang.james.dailyp.R;
import com.ytang.james.dailyp.base.mvp.BaseView;
import com.ytang.james.dailyp.base.utils.ViewUtils;

/**
 * Created by James on 16/10/20.
 */
public class ADView extends RelativeLayout implements BaseView {

    private ImageView imageView;
    private TextView textView;

    public ADView(Context ctx){
        super(ctx);
    }

    public ADView(Context ctx, AttributeSet attrs){
        super(ctx, attrs);
    }

    public ADView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static ADView newInstance(ViewGroup parent) {
        return (ADView) ViewUtils.newInstance(parent, R.layout.advertise_item_view);
    }

    public static ADView newInstance(Context context) {
        return (ADView) ViewUtils.newInstance(context, R.layout.advertise_item_view);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView(){
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextView() {
        return textView;
    }

}
