package com.sogou.study.observer.callerImpl;

import com.sogou.study.observer.CallerInter;
import com.sogou.study.observer.ObserverInter;
import com.sogou.study.observer.Param;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by denglinjie on 2016/8/19.
 */
public class CallImplFirst implements CallerInter, InitializingBean {

    @Autowired
    private ObserverInter observerInter;

    public void call(Param param) {
        //do something
    }

    public void afterPropertiesSet() throws Exception {
        observerInter.addObserver(this);
    }
}
