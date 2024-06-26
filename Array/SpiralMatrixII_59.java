package Leetcode.Leetcode.Array;

public class SpiralMatrixII_59 {
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

    public static void main(String[] args) {
        int[][] n =generateMatrix(3);
        for (int i = 0; i < 3; i++) {
            System.out.println(n[i][0] + "" + n[i][1] + ""  + n[i][2]);
        }
    }
}
