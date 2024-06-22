# Leetcode
A Record of My Problem Solving Journey
## 数组

### 二分查找（704）
#### 题面
https://leetcode.cn/problems/binary-search/

给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
示例 1:
```
输入: nums = [-1,0,3,5,9,12], target = 9     
输出: 4       
解释: 9 出现在 nums 中并且下标为 4     
123
```

示例 2:
```
输入: nums = [-1,0,3,5,9,12], target = 2     
输出: -1        
解释: 2 不存在 nums 中因此返回 -1        
123
```
提示：你可以假设 nums 中的所有元素是不重复的。n 将在 [1, 10000]之间。nums 的每个元素都将在 [-9999, 9999]之间。

#### 思路
二分查找一般有两种写法，左闭右闭即[left, right]，或者左闭右开即[left, right)。
##### 第一种写法
区间为左闭右闭即[left, right]
```java
public static int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int middle = left + ((right - left) / 2);

            if(nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
```
##### 第二种写法
区间为左闭右开即[left, right)
```java
public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int middle = left + ((right - left) >> 1);//此处的 >> 是位运算符，相当与 x/2^1并向下取整
            if(nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                right = middle;
            } else if (nums[middle] < target) {
                left = middle + 1;

            }
        }
        return -1;
    }
```
### 移除元素（27）
#### 题面

给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组。
元素的顺序可以改变。
你不需要考虑数组中超出新长度后面的元素。
示例 1: 给定 nums = [3,2,2,3], val = 3, 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。 你不需要考虑数组中超出新长度后面的元素。
示例 2: 给定 nums = [0,1,2,2,3,0,4,2], val = 2, 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
**你不需要考虑数组中超出新长度后面的元素。**

#### 思路

##### 暴力解法
这道题拿到手后首先考虑到的是利用双重循环进行暴力搜索。第一层循环搜索数组中值等于`val`的元素，当找到之后，进行第二层循环，将数组整体前移。
代码如下：
```java
public int removeElement(int[] nums, int val) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if(nums[i] == val) {
                for (int j = i+1; j < size; j++) {
                    nums[j - 1] == nums[j];
                }
                i--;
                size--;
            }

        }
        return size;
    }
```
暴力的时间复杂度表现不好，为O(n^2)。继续思考其他做法。
##### 双指针法（快慢指针法）

双指针法（快慢指针法）： 通过一个快指针和慢指针在一个for循环下完成两个for循环的工作。

定义快慢指针

- 快指针：寻找新数组的元素 ，新数组就是不含有目标元素的数组
- 慢指针：指向更新 新数组下标的位置

```java
public int removeElement(int[] nums, int val) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if(nums[fastIndex] != val) {
                nums[slowIndex] = nums[fastIndex];
                slowIndex ++;
            }
        }

        return slowIndex;
    }
```

### 有序数组的平方（977）

#### 题面

给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
示例 1：
输入：nums = [-4,-1,0,3,10]
输出：[0,1,9,16,100]
解释：平方后，数组变为 [16,1,0,9,100]，排序后，数组变为 [0,1,9,16,100]
示例 2：
输入：nums = [-7,-3,2,3,11]
输出：[4,9,9,49,121]

#### 思路

##### 暴力思路
首先想到暴力思路，直接所有元素平方然后排序就好。
```java
public static int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);

        return nums;
    }
```
时间复杂度为：`O(n + nlog n)`

##### 双指针（快慢指针）

数组其实是有序的， 只不过负数平方之后可能成为最大数了。
那么数组平方的最大值就在数组的两端，不是最左边就是最右边，不可能是中间。
此时可以考虑双指针法了，left指向起始位置，right指向终止位置。定义一个新数组result，和A数组一样的大小，让index指向result数组终止位置。
如果`nums[left] * nums[left] > nums[right] * nums[right]`那么`result[index--] = nums[left] * nums[left];`。
如果`nums[left] * nums[left] <= nums[right] * nums[right]` 那么`result[index--] = nums[right] * nums[right];`; 。
```java
public static int[] sortedSquares(int[] nums) {
        int right = nums.length - 1;
        int left = 0;
        int result[] = new int[nums.length];
        int index = result.length - 1;
        while(left <= right) {
            if(nums[left] * nums[left] > nums[right] * nums[right]) {
                result[index--] = nums[left] * nums[left];
                left++;
            } else {
                result[index--] = nums[right] * nums[right];
                right--;
            }
        }
        return result;
    }
```
时间复杂度为`O(n)`
