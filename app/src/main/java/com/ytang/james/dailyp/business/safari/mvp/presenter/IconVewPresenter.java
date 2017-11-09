package com.ytang.james.dailyp.business.safari.mvp.presenter;

import android.view.View;
import android.widget.Toast;

import com.ytang.james.dailyp.base.mvp.BasePresenter;
import com.ytang.james.dailyp.business.safari.activity.SafariTabViewPagerActivity;
import com.ytang.james.dailyp.business.safari.mvp.model.IconModel;
import com.ytang.james.dailyp.business.safari.mvp.view.IconView;
import com.ytang.james.dailyp.test.fragment.TestSafariActivity;

/**
 * Created by James on 16/10/18.
 */
public class IconVewPresenter extends BasePresenter<IconView, IconModel> {

  @Override
  public void bind(final IconView iconView, final IconModel iconModel) {
    iconView.getTextView().setText(iconModel.getName());
    iconView.getImageView().setImageResource(iconModel.getResId());

    iconView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(iconView.getContext(),
            "click id = " + iconModel.getId() + ",  name = " + iconModel.getName(),
            Toast.LENGTH_SHORT).show();
        switch (iconModel.getId()){
          case 10:
            SafariTabViewPagerActivity.launch(view.getContext());
            break;
          case 9:
            TestSafariActivity.launch(view.getContext());
            break;
        }
      }
    });

  }
}
