package com.ytang.james.dailyp.business.safari.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ytang.james.dailyp.R;
import com.ytang.james.dailyp.base.mvp.BaseView;
import com.ytang.james.dailyp.base.utils.ViewUtils;

/**
 * Created by James on 16/10/17.
 */
public class IconView extends LinearLayout implements BaseView {

    private ImageView imageView;
    private TextView textView;

    public IconView(Context ctx){
        super(ctx);
    }

    public IconView(Context ctx, AttributeSet attrs){
        super(ctx, attrs);
    }

    public IconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static IconView newInstance(ViewGroup parent) {
        return (IconView) ViewUtils.newInstance(parent, R.layout.icon_item_view);
    }

    public static IconView newInstance(Context context) {
        return (IconView) ViewUtils.newInstance(context, R.layout.icon_item_view);
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
