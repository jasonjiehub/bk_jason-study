package com.sogou.study.observer;

/**
 * Created by denglinjie on 2016/8/19.
 */

public interface ObserverInter {
    /**
     * 增加观察者
     *
     * @param callInter
     */
    void addObserver(CallerInter callInter);

    /**
     * 删除观察者
     *
     * @param callInter
     */
    void delObserver(CallerInter callInter);

    /**
     * 通知观察者
     *
     * @param param
     */
    void notifyAllObserver(Param param);
}
