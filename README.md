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

### 长度最小的子数组（209）
#### 题面
给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。

示例：
输入：s = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。


提示：
1 <= target <= 10^9
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^5

#### 思路
##### 暴力
首先考虑暴力解法。两个for循环，然后不断的寻找符合条件的子序列，时间复杂度很明显是O(n^2)。
```java
public static int minSubArrayLen(int target, int[] nums) {

        int result = Integer.MAX_VALUE;
        int sum = 0;
        int subLength = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    subLength = j - i + 1;
                    result = result < subLength ? result : subLength;
                    break;

                }
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
```
该解法并不能通过所有样例点，想一下其他方法。

##### 滑动窗口
滑动窗口其实也是一种双指针方法，但是因为双指针构成的区域比较类似一个窗口，所以叫做滑动窗口。
在滑动窗口中我们需要关注几个重点：


- 窗口内是什么？
- 如何移动窗口的起始位置？
- 如何移动窗口的结束位置？


窗口就是 满足其和 ≥ s 的长度最小的 连续 子数组。
窗口的起始位置如何移动：如果当前窗口的值大于等于s了，窗口就要向前移动了（也就是该缩小了）。
窗口的结束位置如何移动：窗口的结束位置就是遍历数组的指针，也就是for循环里的索引。

```java
while (sum >= target) {
                subLength = right - left + 1;
                result = result < subLength ? result : subLength;
                sum -= nums[left++];
            }
```
这部分代码就是滑动窗口的精髓，**滑动窗口的精妙之处在于根据当前子序列和大小的情况，不断调节子序列的起始位置。从而将O(n^2)暴力解法降为O(n)。**

具体实现：
```java
 public static int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        int sum = 0;//滑动窗口内元素之和
        int left = 0;//滑动窗口起始位置
        int subLength = 0;//滑动窗口的长度
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= target) {
                subLength = right - left + 1;
                result = result < subLength ? result : subLength;
                sum -= nums[left++];
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }
```
### 长度最小的子数组（209）
#### 题面
给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。

示例：
输入：s = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。


提示：
1 <= target <= 10^9
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^5

#### 思路
##### 暴力
首先考虑暴力解法。两个for循环，然后不断的寻找符合条件的子序列，时间复杂度很明显是O(n^2)。
```java
public static int minSubArrayLen(int target, int[] nums) {

        int result = Integer.MAX_VALUE;
        int sum = 0;
        int subLength = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    subLength = j - i + 1;
                    result = result < subLength ? result : subLength;
                    break;

                }
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
```
该解法并不能通过所有样例点，想一下其他方法。

##### 滑动窗口
滑动窗口其实也是一种双指针方法，但是因为双指针构成的区域比较类似一个窗口，所以叫做滑动窗口。
在滑动窗口中我们需要关注几个重点：


- 窗口内是什么？
- 如何移动窗口的起始位置？
- 如何移动窗口的结束位置？


窗口就是 满足其和 ≥ s 的长度最小的 连续 子数组。
窗口的起始位置如何移动：如果当前窗口的值大于等于s了，窗口就要向前移动了（也就是该缩小了）。
窗口的结束位置如何移动：窗口的结束位置就是遍历数组的指针，也就是for循环里的索引。

```java
while (sum >= target) {
                subLength = right - left + 1;
                result = result < subLength ? result : subLength;
                sum -= nums[left++];
            }
```
这部分代码就是滑动窗口的精髓，**滑动窗口的精妙之处在于根据当前子序列和大小的情况，不断调节子序列的起始位置。从而将O(n^2)暴力解法降为O(n)。**

具体实现：
```java
 public static int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        int sum = 0;//滑动窗口内元素之和
        int left = 0;//滑动窗口起始位置
        int subLength = 0;//滑动窗口的长度
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= target) {
                subLength = right - left + 1;
                result = result < subLength ? result : subLength;
                sum -= nums[left++];
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }
```

### 螺旋矩阵II（59）
#### 题面

给定一个正整数 n，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
示例:
输入: 3
输出: [ [ 1, 2, 3 ], [ 8, 9, 4 ], [ 7, 6, 5 ] ]

#### 思路
这道题在写循环的时候可以坚持每条边都**左闭右开**的原则

模拟顺时针画矩阵的过程:
- 填充上行从左到右
- 填充右列从上到下
- 填充下行从右到左
- 填充左列从下到上

```java
 public static int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int startx = 0; //每一次循环开始的x位置
        int starty = 0; //每一次循环开始的y位置
        int loop = n / 2; //循环的圈数
        int mid = n / 2; //如果n为奇数则需要填充中间位置
        int count = 1; //矩阵每一个空格处要填充的元素
        int offset = 1; //用来控制每一条边的遍历长度
        int i,j;

        while ((loop--) != 0) {
            i = startx;
            j = starty;

            //模拟从左往右填充
            for (; j < n - offset; j++) {
                ans[i][j] = count++;
            }
            //模拟从上往下填充
            for (; i < n - offset; i++) {
                ans[i][j] = count++;
            }
            //模拟从右往左填充
            for (; j > startx ; j--) {
                ans[i][j] = count++;
            }
            //模拟从下往上填充
            for (; i > starty ; i--) {
                ans[i][j] = count++;
            }

            //第二圈开始的时候，起始位置要各自加1， 例如：第一圈起始位置是(0, 0)，第二圈起始位置是(1, 1)
            startx++;
            starty++;

            // offset 控制每一圈里每一条边遍历的长度
            offset += 1;
        }

        if (n % 2 == 1) {
            ans[mid][mid] = count;
        }

        return ans;
    }
```

## 链表
### 链表基础理论
#### 链表的类型
##### 单链表
链表是一种通过指针串联在一起的线性结构，每一个节点由两部分组成，一个是数据域一个是指针域（存放指向下一个节点的指针），最后一个节点的指针域指向null（空指针的意思）。

链表的入口节点称为链表的头结点也就是head。



##### 双链表

单链表中的指针域只能指向节点的下一个节点。

双链表：每一个节点有两个指针域，一个指向下一个节点，一个指向上一个节点。

双链表 既可以向前查询也可以向后查询。



##### 循环链表


循环链表，顾名思义，就是链表首尾相连。

循环链表可以用来解决约瑟夫环问题。



#### 链表的定义

```java
public class ListNode {
    // 结点的值
    int val;

    // 下一个结点
    ListNode next;

    // 节点的构造函数(无参)
    public ListNode() {
    }

    // 节点的构造函数(有一个参数)
    public ListNode(int val) {
        this.val = val;
    }

    // 节点的构造函数(有两个参数)
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }}
```

#### 链表的操作
##### 删除节点
删除节点D
只要将C节点的next指针 指向E节点就可以了。在Java中，有内存回收机制，就不用自己手动释放了。

##### 添加节点

可以看出链表的增添和删除都是O(1)操作，也不会影响到其他节点。

#### 性能分析


### 移除链表元素（203）

#### 题面
题意：删除链表中等于给定值 val 的所有节点。

示例 1： 输入：head = [1,2,6,3,4,5,6], val = 6 输出：[1,2,3,4,5]

示例 2： 输入：head = [], val = 1 输出：[]

示例 3： 输入：head = [7,7,7,7], val = 7 输出：[]

#### 思路
##### 使用虚拟头节点

可以设置一个虚拟头结点，这样原链表的所有节点就都可以按照统一的方式进行移除了。

```java
public ListNode removeElements(ListNode head, int val) {

        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(-1,head);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if(cur.val == val)
            {
                pre.next = cur.next;
            } else{
                pre = cur;
            }
            cur = cur.next;
        }

        return dummy.next;
    }
```

##### 不使用虚拟头节点，直接删除

移除头结点和移除其他节点的操作是不一样的，因为链表的其他节点都是通过前一个节点来移除当前节点，而头结点没有前一个节点。所以头结点如何移除呢，其实只要将头结点向后移动一位就可以，这样就从链表中移除了一个头结点。

```java
public static ListNode removeElements(ListNode head, int val) {

        while (head != null && head.val == val) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }
        
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return head;
        
    }
```


### 设计链表（707）
题意：
在链表类中实现这些功能：
- get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
- addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
- addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
- addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。
- 如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
- deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。

