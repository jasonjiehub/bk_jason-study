package com.sogou.study.observer;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by denglinjie on 2016/8/19.
 */
@Service
public class ObserverImpl implements ObserverInter, InitializingBean {

    //要通知的对象列表
    private List<CallerInter> callerInterList;

    public void addObserver(CallerInter callInter) {
        callerInterList.add(callInter);
    }

    public void delObserver(CallerInter callInter) {
        callerInterList.remove(callInter);
    }

    public void notifyAllObserver(Param param) {
        for(CallerInter callerInter : callerInterList) {
            callerInter.call(param);
        }
    }

    public void afterPropertiesSet() throws Exception {
        callerInterList = new CopyOnWriteArrayList<CallerInter>();  //这里用一个线程安全的list，因为可能多线程同时操作它
    }

    /**
     * 对外提供一个接口，当这个接口被调用的时候，可以通知所有注册进来的对象的特定call接口
     */
    public void operateService() {
        //构造参数
        Param param = new Param();
        notifyAllObserver(param);
    }
}
