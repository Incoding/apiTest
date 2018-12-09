package com.javaapi.test.util.math.algorithm.sort.heapsort;

import org.junit.Test;

/**
 * Created by user on 2018/12/10
 */
public class HeapSortSelf {
    @Test
    public void test(){
        int[] params = {9,2,7,4,6,5,3,8,1};
        //调用需要测试的函数
        buildHeap(params);
        //打印结果
        for (int i : params) {
            System.out.print(i + " ");
        }

    }

    /**
     * 构建大顶堆堆
     * 从最后一个非叶子结点开始调整堆。每次都是从父结点，左节点，右结点三者中选最大值与父结点交换
     *
     * @param array
     */
    private static void buildHeap(int[] array) {
        // 数组中元素的个数
        int n = array.length;
        //i= n/2-1  表示最后一个非叶子结点的索引值
        for (int i = n / 2 - 1; i >= 0; i--)
        {
            adjustHeap(array, i, n);
        }
    }


    private static void adjustHeap(int[] array, int idx, int max) {
        // 左孩子的下标（如果存在的话）
        int left = 2 * idx + 1;
        // 右孩子的下标（如果存在的话）
        int right = left + 1;
        // 寻找3个节点中最大值节点的下标
        //左孩子的值比父结点的值大
        int largest = largestIndex(array, idx, max, left, right);
        //从左节点，右结点中选最大值与父结点交换
        if (largest != idx) {
            swap(array, largest, idx);
            adjustHeap(array, largest, max);
        }
    }


    private static int largestIndex(int[] array, int idx, int max, int left, int right) {
        int largest;
        if (left < max && array[left] > array[idx]) {
            largest = left;
        } else {
            largest = idx;
        }

        if (right < max && array[right] > array[largest]) {
            largest = right;
        }
        return largest;
    }

    private static void swap(int[] array, int i, int i2) {
        int temp = array[i];
        array[i] = array[i2];
        array[i2] = temp;
    }
}
