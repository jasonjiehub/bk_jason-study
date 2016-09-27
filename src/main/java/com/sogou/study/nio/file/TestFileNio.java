package com.sogou.study.nio.file;

/**
 * Created by denglinjie on 2016/9/27.
 */

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestFileNio {
    public static void main(String args[]) throws Exception{
        int bufSize = 100;
        File fin = new File("D:\\test\\file1.txt");
        File fout = new File("D:\\test\\file2.txt");

        FileChannel fcin = new RandomAccessFile(fin, "r").getChannel();
        ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);

        FileChannel fcout = new RandomAccessFile(fout, "rws").getChannel();
        ByteBuffer wBuffer = ByteBuffer.allocateDirect(bufSize);

        readFileByLine(bufSize, fcin, rBuffer, fcout, wBuffer);

        System.out.print("OK!!!");
    }

    public static void readFileByLine(int bufSize, FileChannel fcin, ByteBuffer rBuffer, FileChannel fcout, ByteBuffer wBuffer){
        String enterStr = "\n";
        try{
            byte[] bs = new byte[bufSize];
            StringBuffer strBuf = new StringBuffer("");
            while(fcin.read(rBuffer) != -1){
                int rSize = rBuffer.position();
                rBuffer.rewind();   //将position置为0，准备读
                rBuffer.get(bs);    //从上述position=0位置开始读
                rBuffer.clear();
                String tempString = new String(bs, 0, rSize);
                int fromIndex = 0;
                int endIndex = 0;
                //查找换行符符号\n，如果找到了，则写文件，如果没有找到则继续读取
                while((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1){
                    String line = tempString.substring(fromIndex, endIndex);
                    line = strBuf.toString() + line;
                    writeFileByLine(fcout, line);
                    strBuf.delete(0, strBuf.length());
                    fromIndex = endIndex + 1;
                }

                if(rSize > tempString.length()){
                    strBuf.append(tempString.substring(fromIndex, tempString.length()));
                }else{
                    strBuf.append(tempString.substring(fromIndex, rSize));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFileByLine(FileChannel fcout, String line){
        try {
            fcout.write(ByteBuffer.wrap(line.getBytes()), fcout.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
