package com.ytang.james.dailyp.business.safari.mvp.presenter;

import android.view.View;
import android.widget.Toast;

import com.ytang.james.dailyp.base.mvp.BasePresenter;
import com.ytang.james.dailyp.business.safari.mvp.model.ADModel;
import com.ytang.james.dailyp.business.safari.mvp.view.ADView;

/**
 * Created by James on 16/10/20.
 */
public class ADViewPresenter extends BasePresenter<ADView, ADModel> {

  @Override
  public void bind(ADView view, final ADModel model) {
    view.getImageView().setBackgroundResource(model.getResId());
    view.getTextView().setText(model.getDescript());
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(view.getContext(),
            "click id = " + model.getId() + ", dsc = " + model.getDescript(), Toast.LENGTH_SHORT)
            .show();
      }
    });
  }
}
