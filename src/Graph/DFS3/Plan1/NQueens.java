package Graph.DFS3.Plan1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *明确queen定义：
 * <任意>两个棋子，出现在同一列，或者同一正/负对角线都不行
 *
 * DFS:
 * 棋盘有多少行，就有多少层；
 * 每行有多少列放棋子是valid，那层就有多少branch
 *
 * 更新行数方法：下一层idx = curRow + 1
 * 每层build branch方法： traverse all branch candidate， all valid col which can put chess，is a valid branch
 *                       即站在每一层，遍历所有列，找到那些放棋子valid的列，建立branch，进入下一层recursion
 */
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        //sanity check
        if (n == 0) {
            return res;
        }
        List<Integer> usedCol = new ArrayList<>(); //index:row  val: col
        backTracking(n, 0, usedCol, res);
        return res;
    }

    private void backTracking(int n, int curRow, List<Integer> usedCol, List<List<String>> res) {
        //base case
        if (curRow == n) {
            addOneRes(usedCol, res);
            return;
        }
        //iterate all branch + add cur node influence
        for (int curCol = 0; curCol < n; curCol++) {
            if (validPos(curRow, curCol, usedCol)) {
                usedCol.add(curCol);
                backTracking(n, curRow + 1, usedCol, res);
                //recovery
                usedCol.remove(usedCol.size() - 1);
            }
        }
    }

    private boolean validPos(int curRow, int curCol, List<Integer> usedCol) {
        for (int prevRow = 0; prevRow < curRow; prevRow++) {
            int prevCol = usedCol.get(prevRow);
            //有一个false就返回false
            if (prevCol == curCol || (Math.abs(prevCol - curCol) == Math.abs(prevRow - curRow))) {
                return false;
            }
        }
        return true;
    }

    private void addOneRes(List<Integer> usedCol, List<List<String>> res) {
        List<String> oneRes = new ArrayList<>();
        for (int row = 0; row < usedCol.size(); row++) {
            int col = usedCol.get(row);
            String curRowStr = "";
            for (int i = 0; i < col; i++) {
                curRowStr += ".";
            }
            curRowStr += "Q";
            for (int i = col + 1; i < usedCol.size(); i++) {
                curRowStr += ".";
            }
            oneRes.add(curRowStr);
        }
        res.add(new ArrayList<>(oneRes));
    }
}

/**
 * 思考：因为棋盘大小固定，所以usedCol实际上长度最多是n，可以使用int[]来优化这个ds
 * 并且记录res的方法也可以进一步改进
 */
class SolNQ1b {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        //sanity check
        if (n == 0) {
            return res;
        }
        int[] usedCol = new int[n]; //index:row  val: col,实质是curPath，放置一个解中已经找到棋子位置的 所有行的 col的位置
        backTracking(n, 0, usedCol, res);
        return res;
    }

    private void backTracking(int n, int curRow, int[] usedCol, List<List<String>> res) {
        //base case
        if (curRow == n) {
            res.add(buildOneRes(usedCol, n));
            return;
        }
        //trverse all branch candidate, find all valid branch
        for (int i = 0; i < n; i++) {   //i -- cur traversed col
            if (isValidPos(curRow, i, usedCol)) {
                usedCol[curRow] = i; //update path
                backTracking(n, curRow + 1, usedCol, res);
                usedCol[curRow] = 0; //recover
            }
        }
    }

    /**<任意>两个棋子，出现在同一列，或者同一正/负对角线都不行*/
    private boolean isValidPos(int curRow, int curCol, int[] usedCol) {
        for (int i = 0; i < curRow; i++) {   //i -- prevRow
            int j = usedCol[i];              //j -- prevCol
            if (j == curCol || Math.abs(i - curRow) == Math.abs(j - curCol)) {
                return false;
            }
        }
        return true;
    }

    private List<String> buildOneRes(int[] usedCol, int n) {
        List<String> oneRes = new ArrayList<>();
        for (int colIdx : usedCol) {  //build each row
            char[] arr = new char[n];
            Arrays.fill(arr, '.');
            arr[colIdx] = 'Q';
            oneRes.add(new String(arr));
        }
        return oneRes;
    }
}

