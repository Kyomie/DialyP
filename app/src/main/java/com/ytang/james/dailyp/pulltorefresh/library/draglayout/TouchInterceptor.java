package com.ytang.james.dailyp.pulltorefresh.library.draglayout;

/**
 * Created by James on 16/10/21.
 */
public interface TouchInterceptor {
    boolean willHandlePullDown();

    boolean willHandlePullUp();
}
