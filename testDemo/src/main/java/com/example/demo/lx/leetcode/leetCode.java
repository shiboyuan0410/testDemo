package com.example.demo.lx.leetcode;



import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: shiboyuan
 * @Date: 2021/3/15 9:32
 */
public class leetCode {

    public static void main(String[] args){
        List<Integer> intList = new ArrayList<>();

        for (int i = 0 ; i< 10 ;i++){
            intList.add(i);
        }


        List<List<Integer>> subs = ListUtils.partition(intList, 50);
        System.out.println(subs.size());
        System.out.println(subs);
    }

}
