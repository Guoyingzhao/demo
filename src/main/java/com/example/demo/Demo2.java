package com.example.demo;

import org.junit.Test;

//独立编写各大排序算法
public class Demo2 {

    @Test
    public void maopao(){
        int size = 100;
        double[] array = new double[size];
        for (int i = 0; i < size; i++) {;
            array[i]= Math.random();;
        }
        System.out.println("随机数组长度："+array.length);
        //System.out.println(Arrays.toString(array));
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
        for (int i = 0; i < size-1; i++) {
            for (int j = i+1; j < size; j++) {
                comp++;
                if(array[i]>array[j]){
                    swap++;
                    double ampt = array[i];
                    array[i]=array[j];
                    array[j]=ampt;
                }
            }
        }
        System.out.println("冒泡排序:");
        //System.out.println(Arrays.toString(array));
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

        for (int h = 0; h < array.length; h++) {
            int minIndex = h;
            double min = array[h];
            for (int i = h+1; i < array.length; i++) {
                comp++;
                if (array[i] < min) {
                    min = array[i];
                    minIndex = i;
                }
            }
            if (minIndex != h){
                swap++;
                double ampt = array[h];
                array[h]=array[minIndex];
                array[minIndex]=ampt;
            }
        }
        System.out.println("选择排序:");
        //System.out.println(Arrays.toString(array));
        System.out.println("比较次数："+comp);
        System.out.println("交换次数："+swap);
    }

    /**
     * 插入排序，
     * @see java.util.Arrays#sort(int[]) 中长度小于47时使用
     * @param array 随机数组
     */
    public void t3(double[] array){
        /**数组长度*/
        int size = array.length;
        /**交换次数*/
        long swap = 0;
        /**比较次数*/
        long comp = 0;
        for (int i = 1; i < size; i++) {
            int j = i;
            double value = array[i];
            while (j>0 && array[j-1]>value){
                comp++;
                array[j]=array[j-1];
                j--;
            }
            if(array[j]!=value){
                swap++;
                array[j]=value;
            }
        }
        System.out.println("插入排序:");
        //System.out.println(Arrays.toString(array));
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
        /**h高度：1,4,13,40,121,364...*/
        int h = 1;
        while (h<size){
            h=(h*3)+1;
        }
        while(h>1){
            h = h/3;//余数会丢弃
            for (int i = 0; size>i+h; i++) {
                int j=i;
                while (j>=0){
                    comp++;
                    if(array[j]>array[j+h]){
                        swap++;
                        double ampt = array[j];
                        array[j]=array[j+h];
                        array[j+h]=ampt;
                    }
                    j--;
                }
            }
        }
        System.out.println("希尔排序:");
        //System.out.println(Arrays.toString(array));
        System.out.println("比较次数：" + comp);
        System.out.println("交换次数：" + swap);
    }
























}
