package com.example.demo;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Arrays;

//教科书排序写法
public class Demo3 {

    @Test
    public void maopao(){
        int size = 100;
        double[] array = new double[size];
        for (int i = 0; i < size; i++) {;
            array[i]= Math.random();;
        }
        System.out.println("随机数组长度："+array.length);
        System.out.println(Arrays.toString(array));
        double[] array1 = array.clone();
        t1(array1);
        array1 = array.clone();
        t2(array1);
        array1 = array.clone();
        t3(array1);
        array1 = array.clone();
        t4(array1);
    }

    /**
     * 冒泡排序
     * @param array 随机数组
     */
    public void t1(double[] array){
        /**数组长度*/
        int size = array.length;
        /**交换次数*/
        long swap = 0;
        /**比较次数*/
        long comp = 0;
        //冒泡排序
        double temp = 0;
        for(int i = 0; i < array.length - 1; i++) {
            for(int j = 0; j < array.length - i - 1; j++) {
                comp++;
                if(array[j] > array[j + 1]) {
                    swap++;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println("冒泡排序:");
        System.out.println(Arrays.toString(array));
        System.out.println("比较次数："+comp);
        System.out.println("交换次数："+swap);
    }

    /**
     * 选择排序，可以看出冒泡排序的改进版
     * @param array 随机数组
     */
    public void t2(double[] array){
        /**数组长度*/
        int size = array.length;
        /**交换次数*/
        long swap = 0;
        /**比较次数*/
        long comp = 0;

        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            // 把当前遍历的数和后面所有的数进行比较，并记录下最小的数的下标
            for (int j = i + 1; j < array.length; j++) {
                comp++;
                if (array[j] < array[minIndex]) {
                    // 记录最小的数的下标
                    minIndex = j;
                }
            }
            // 如果最小的数和当前遍历的下标不一致，则交换
            if (i != minIndex) {
                swap++;
                double temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
        System.out.println("选择排序:");
        System.out.println(Arrays.toString(array));
        System.out.println("比较次数："+comp);
        System.out.println("交换次数："+swap);
    }

    /**
     * 插入排序，
     * @see Arrays#sort(int[]) 中长度小于47时使用
     * @param array 随机数组
     */
    public void t3(double[] array){
        /**数组长度*/
        int size = array.length;
        /**交换次数*/
        long swap = 0;
        /**比较次数*/
        long comp = 0;
        for (int i = 1; i < array.length; ++i) {
            double value = array[i];
            int position = i;
            while (position > 0 && array[position - 1] > value) {
                comp++;
                array[position] = array[position - 1];
                position--;
            }
            if(array[position] != value) {
                swap++;
                array[position] = value;
            }
        }
        System.out.println("插入排序:");
        System.out.println(Arrays.toString(array));
        System.out.println("比较次数："+comp);
        System.out.println("交换次数："+swap);
    }


    /**
     * 希尔排序
     * @param array 随机数组
     */
    public void t4(double[] array) {
        /**数组长度*/
        int size = array.length;
        /**交换次数*/
        long swap = 0;
        /**比较次数*/
        long comp = 0;
        //比较次数：503
        //交换次数：230
//        for (int gap = array.length / 2; gap > 0; gap /= 2) {
//            // 对每一组都执行直接插入排序
//            for (int i = 0 ;i < gap; i++) {
//                // 对本组数据执行直接插入排序
//                for (int j = i + gap; j < array.length; j += gap) {
//                    comp++;
//                    // 如果 a[j] < a[j-gap]，则寻找 a[j] 位置，并将后面数据的位置都后移
//                    if (array[j] < array[j - gap]) {
//                        swap++;
//                        int k;
//                        double temp = array[j];
//                        for (k = j - gap; k >= 0 && array[k] > temp; k -= gap) {
//                            array[k + gap] = array[k];
//                        }
//                        array[k + gap] = temp;
//                    }
//                }
//            }
//        }


        //比较次数：429
        //交换次数：429
        int delta = 1;
        while (delta < array.length / 3) {
            delta = delta * 3 + 1;
        }
        double temp;
        for (; delta >= 1; delta /= 3) {
            for (int i = delta; i < array.length; i++) {
                for (int j = i; j >= delta && array[j] < array[j - delta]; j -= delta) {
                    comp++;
                    swap++;
                    temp = array[j - delta];
                    array[j - delta] = array[j];
                    array[j] = temp;
                }
            }
        }

        System.out.println("希尔排序:");
        System.out.println(Arrays.toString(array));
        System.out.println("比较次数：" + comp);
        System.out.println("交换次数：" + swap);
    }
























}
