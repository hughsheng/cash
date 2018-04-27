package com.tl.tplus.utils;

/**
 * Created by sztangli on 2017/6/22.
 */

public class ExampleDistanceTest {

    private String fourEmpty(String str){
        StringBuilder star=new StringBuilder();
        int lengths=str.length();
        for(int i=0;i<lengths-8;i++){
            star.append("*");
        }
      str=str.replace(str.substring(4, str.length() - 4), star);
        StringBuilder sb=new StringBuilder(str);
        int length=str.length()/4+str.length();

        for(int i=0;i<length;i++){
            if(i%5==0){
                sb.insert(i," ");
            }
        }
        sb.deleteCharAt(0);
        return sb.toString();
    }
}
