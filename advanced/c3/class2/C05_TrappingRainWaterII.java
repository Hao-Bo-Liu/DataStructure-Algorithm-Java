package advanced.c3.class2;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author andy-liu
 * @date 2020/6/27 - 10:09 AM
 */
public class C05_TrappingRainWaterII {

    /*
    LeetCode 407. Trapping Rain Water II
     */

    public static int trapRainWater(int[][] matrix) {

        int row = matrix.length;
        int col = matrix[0].length;
        PriorityQueue<Node> minHeap = new PriorityQueue<>(new NodeComparator() {
        });
        boolean[][] isEnter = new boolean[row][col];
        int max = Integer.MIN_VALUE;
        int water = 0;

        // 矩阵四周一圈先加入堆
        for (int c = 0; c < col; c++) {
            minHeap.add(new Node(matrix[0][c], 0, c));
            isEnter[0][c] = true;
        }

        for (int r = 0; r < row; r++) {
            minHeap.add(new Node(matrix[r][col - 1], r, col - 1));
            isEnter[r][col - 1] = true;
        }

        for (int c = col - 1; c > 0; c--) {
            minHeap.add(new Node(matrix[row - 1][c], row - 1, c));
            isEnter[row - 1][c] = true;
        }

        for (int r = row - 1; r > 0; r--) {
            minHeap.add(new Node(matrix[r][0], r, 0));
            isEnter[r][0] = true;
        }

        while (!minHeap.isEmpty()) {
            Node first = minHeap.poll();
            max = Math.max(first.val, max);
            int r = first.row;
            int c = first.col;
            if (r > 0 && !isEnter[r - 1][c]) {
                water += Math.max(0, max - matrix[r - 1][c]);
                minHeap.add(new Node(matrix[r - 1][c], r - 1, c));
                isEnter[r - 1][c] = true;
            }
            if (c > 0 && !isEnter[r][c - 1]) {
                water += Math.max(0, max - matrix[r][c - 1]);
                minHeap.add(new Node(matrix[r][c - 1], r, c - 1));
                isEnter[r][c - 1] = true;
            }
            if (r < row - 1 && !isEnter[r + 1][c]) {
                water += Math.max(0, max - matrix[r + 1][c]);
                minHeap.add(new Node(matrix[r + 1][c], r + 1, c));
                isEnter[r + 1][c] = true;
            }
            if (c < col - 1 && !isEnter[r][c + 1]) {
                water += Math.max(0, max - matrix[r][c + 1]);
                minHeap.add(new Node(matrix[r][c + 1], r, c + 1));
                isEnter[r][c + 1] = true;
            }
        }

        return water;
    }


    public static class Node {
        int val;
        int row;
        int col;

        public Node(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
    }

    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.val - o2.val;
        }
    }

}