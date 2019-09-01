/*
题目七
【题目】 给定一个整型矩阵matrix，请按照转圈的方式打印它。
1   2   3   4 
5   6   7   8
9  10  11  12
13 14  15  16
打印结果为：1，2，3，4，8，12，16，15，14，13，9，5，6，7，11，10
【要求】 额外空间复杂度为O(1)。
【思路】 使用两个点，左上角和右下角定出一个范围，逐步缩小范围并打印
*/
public class PrintMatrixSpiralOrder {
    // 旋转打印矩阵
    public static void spiralOrderPrint(int[][] matrix) {
        // 左上角的点
        int row1 = 0;
        int column1 = 0;
        // 右下角的点
        int row2 = matrix.length - 1;;
        int column2 = matrix[0].length - 1;
        while (row1 < row2 && column1 < column2) {
            // 循环调用，打印边界
            printEdge(matrix, row1++, column1++, row2--, column2--);
        }
    }
    // 打印边界
    public static void printEdge(int[][] m, int tR, int tC, int dR, int dC) {
        // 只有一行
        if (tR == dR) {
            for (int i = tC; i <= dC; i++) {
                System.out.print(m[tR][i] + " ");
            }
        } else if (tC == dC) {
            // 只有一列
            for (int i = tR; i <= dR; i++) {
                System.out.print(m[i][tC] + " ");
            }
        } else {
            int curC = tC;
            int curR = tR;
            // 打印四个边界
            while (curC != dC) {
                System.out.print(m[tR][curC] + " ");
                curC++;
            }
            while (curR != dR) {
                System.out.print(m[curR][dC] + " ");
                curR++;
            }
            while (curC != tC) {
                System.out.print(m[dR][curC] + " ");
                curC--;
            }
            while (curR != tR) {
                System.out.print(m[curR][tC] + " ");
                curR--;
            }
        }
    }

    // 测试
    public static void main(String[] args) {
        int[][] matrix = { { 1,  2,  3,  4 }, 
                           { 5,  6,  7,  8 }, 
                           { 9, 10, 11, 12 },
                           {13, 14, 15, 16 } };
        spiralOrderPrint(matrix);
    }
}