package com.sogou.study.serialize;

/**
 * Created by denglinjie on 2016/9/29.
 */

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class UnSerializeTest {

    public static void main(String[] args) throws Exception {
        //反序列化到内存
        ObjectInputStream oi = new ObjectInputStream
                (new FileInputStream("D:\\test\\file1.txt"));
        Client c_back = (Client) oi.readObject();
        System.out.println(c_back.getName());
        System.out.println(c_back.getId());
        oi.close();

    }
}
