package top.shellwe.toolbox.controller;

import java.util.Arrays;

/**
 * @Auther: Benjamin Thomas Shellwe
 * @Date: 2023/9/7/20:07
 * @Description: 来自包top.shellwe.toolbox.controller
 * @Annotation: 注释
 * @version: 1.0
 */
public class solution {
    public static int removeElement(int[] nums, int val) {
        int n = nums.length;
        int left = 0; // 左指针

        for (int right = 0; right < n; right++) {
            if (nums[right] != val) { // 如果nums[right]不等于val时将说明找到相同的值，不执行赋值语句
                nums[left] = nums[right]; // 将nums[right]赋给nums[left]
                left++; //
            }
        }
        String arrayAsString = Arrays.toString(nums);
        System.out.println(arrayAsString);
        return left; // 返回新长度，每次找到相等的值时被跳过不会自增
    }
    /*
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
        请必须使用时间复杂度为 O(log n) 的算法。
     */
    public static int searchInsert(int[] nums, int target) {
        for(int right = 0; right < nums.length; right++){
            if(nums[right] >= target){
                return right;
            }
        }
        return nums.length;
    }
    public static int[] mixUp(int[] originalArray, int insertValue, int insertIndex){

        // 创建新数组，长度加 1
        int[] newArray = new int[originalArray.length + 1];

        // 复制插入位置之前的元素到新数组
        for (int i = 0; i < insertIndex; i++) {
            newArray[i] = originalArray[i];
        }

        // 插入新的数
        newArray[insertIndex] = insertValue;

        // 复制插入位置之后的元素到新数组
        for (int i = insertIndex; i < originalArray.length; i++) {
            newArray[i + 1] = originalArray[i];
        }

        // 打印新数组
//        for (int num : newArray) {
//            System.out.print(num + " ");
//        }
        return newArray;

    }
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,3,5,6};
        int val = 5;
//        int a = removeElement(nums1,val);
        int a = searchInsert(nums1,val);
        int[] new1 = mixUp(nums1,val,a);
        System.out.println("下标为" + a);
        System.out.println(Arrays.toString(new1));
    }
}
