package LinkedList;

public class A_LS_Step {
    /**
     * LinkedList解题大方向：
     * 1.先看是用recursion还是iterative
     * 2.
     *      recursion: 分解base case，使用recursion tip
     *      iterative：分析是否需要dummy node;
     *                 分析需要几个，什么类型的pointer (是否可用s,f pointer命名)
     *                      跳跃型pointer:
     *                              e.g. remove系列
     *                              有无dummy，对指针初始位置有影响，进而对指针跳跃步数有影响(slow, fast, prev, cur初始pos..)
     *                      分析性pointer:
     *                              e.g. reverse的iterative写法：prev, cur, next
     *                              e.g. remove dup一个不留: s, f
     *                              有无dummy，对指针初始位置有影响，进而对指针语义(slow的in/ex)有影响，进而对循环体中的处理步骤有影响
     */

    /**
     * 指针跳跃tip: -->跳跃型pointer
     * 1.跳跃k - 1 steps，停留在Kth position
     *      for (int i = 0; i < k - 1; i++) { }
     *   == for (int i = 1; i <= k - 1; i++) { }
     *   == while (k > 0) { k-- }
     *
     * 2.初始停留dummy的指针，会影响跳跃步数(通常需要额外+1)，要计算好
     */

    /**
     * s,f语义tip：--> 分析性pointer
     * 先观察需不需要dummy
     *      技巧：优先观察第1，2，..n位是否一定会留在res中
     *           一定留：slow可从第1，2，...n位开始，此时语义 --》slow: last pos of remained res，excluding
     *           不一定留：需要dummy,slow从dummy位开始，此时语义 --》slow: last pos of remained res，including
     *      其余需要dummy情况时，slow也从dummy位开始，此时语义 --》slow: last pos of remained res，including
     *      无论slow语义变化，始终有fast语义 == slow.next语义： first pos of unexplored area
     *
     * 根据语义不同，update pointer和保留值的先后顺序会不同，谨慎处理
     */

    /**
     * reverse LinkedList Tip:
     * recursion大方针：
     *      找到curHead, curTail, nextHead
     *      //recursion
     *      ListNode newHead = recursion(nextHead);
     *
     *      //induction rule
     *      1.reverse当前层(简单就直接处理，复杂就用helper func)   //处理当前层
     *      2.curHead.next = newHead;                          //处理层与层之间
     *
     *      //return val
     *      return curTail;
     */
}
