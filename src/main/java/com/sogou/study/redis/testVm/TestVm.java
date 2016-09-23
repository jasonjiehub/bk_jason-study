package com.sogou.study.redis.testVm;

import java.nio.ByteBuffer;

/**
 * Created by denglinjie on 2016/6/29.
 */
public class TestVm {
    public static void main(String[] args) {
//        ArrayList<byte[]> list = new ArrayList<byte[]>();
//        for(int i=0; i<1024; i++) {
//            list.add(new byte[1024*1024*1024]);
//        }
        for(int i=0 ;i< 1024; i++) {
            ByteBuffer.allocateDirect(1024 * 1024 * 1024);
        }
    }
}
