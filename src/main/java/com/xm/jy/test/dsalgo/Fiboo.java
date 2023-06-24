package com.xm.jy.test.dsalgo;

/**
 * @Author: xiangming.fang
 * @Date: 2023/4/18 10:21
 */
public class Fiboo {

    // index from 1
    // recursion
    public static int getIndexNum(int index){
        if (index == 0) return 1;
        if (index == 1) return 1;
        return getIndexNum(index - 1) + getIndexNum(index - 2);
    }

    // dp
    public static int getNumFromIndex(int index){
        int[] arr = new int[index+2];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i -1] + arr[i-2];
        }
        return arr[index];
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 20; i++) {
            if (getIndexNum(i) != getNumFromIndex(i)) {
                System.err.println("写法有问题");
            }
        }
    }

}
