package com.sogou.study.serialize;

/**
 * Created by denglinjie on 2016/9/29.
 */

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SerializeTest {

    public static void main(String[] args) throws Exception {

        //把对象序列化到文件
        Client client = new Client();
        client.setId(11);
        client.setName("client");

        ObjectOutputStream oo = new ObjectOutputStream
                (new FileOutputStream("D:\\test\\file1.txt"));
        oo.writeObject(client);
        oo.close();
    }
}
