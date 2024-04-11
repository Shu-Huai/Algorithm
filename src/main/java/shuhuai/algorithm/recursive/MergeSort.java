package shuhuai.algorithm.recursive;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {
    public void merge(int[] arr, int low, int mid, int high) {
        int[] result = new int[high + 1];
        int i = low;
        int j = mid + 1;
        int k = low;
        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                result[k] = arr[i];
                i++;
            } else {
                result[k] = arr[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            result[k] = arr[i];
            k++;
            i++;
        }
        while (j <= high) {
            result[k] = arr[j];
            k++;
            j++;
        }
        for (k = low; k <= high; k++) {
            arr[k] = result[k];
        }
    }

    public void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.isEmpty()) {
            String[] strs = str.split(" ");
            int[] arr = new int[strs.length];
            for (int i = 0; i < strs.length; i++) {
                arr[i] = Integer.parseInt(strs[i]);
            }
            new MergeSort().mergeSort(arr);
            System.out.println(Arrays.toString(arr));
            str = sc.nextLine();
        }
    }
}