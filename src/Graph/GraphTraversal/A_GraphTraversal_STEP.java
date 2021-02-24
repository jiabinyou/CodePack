package Graph.GraphTraversal;



/**
 * Graph.DFS3 考虑：
 * 是否需要mark visited ds？
 *      如果需要：在哪里mark visited，即对应的DFS3物理意义是1还是2？（including or excluding）
 *               如果取excluding，即mark visit at generation，需"单独mark visit root"
 * 是否需要实际recover操作？
 */

/**
 * DFS1 & 2 考虑：
 * 是否需要mark visited ds？
 *      如果需要：在哪里mark visited，即对应的DFS3物理意义是1还是2？（including or excluding）
 *      如果取excluding，即mark visit at generation，需"单独mark visit root"
 */

/**
 * BFS  考虑：
 * expansion和generation分做的事情是什么？
 * process res在哪里进行，expansion还是generation时候？
 *      如果process at generation，需"单独process root"
 * 是否需要mark visited ds？
 *      如果需要：在哪里mark visited，即对应的DFS3物理意义是1还是2？（including or excluding）
 *      如果mark visit at generation，需"单独mark visit root"
 */


/**
 * 注意：
 * 1.BFS中，"单独处理" 和 "单独mark visited" root是不一样的，两者在BFS中都需要考虑，
 *   而DFS只要考虑"单独mark visited"， 注意BFS和DFS思考方式区分
 * 2.DFS1 & 2，BFS中即使需要mark visited，直接mark即可，不需要recover操作，只有DFS3需要
 */

/**小结：
 * DFS1, DFS2:
 * 2.need extra process?
 *     no need
 *     at expansion
 *     at generation  --> "单独process root"
 * need V ds?
 *     no need
 *     at expansion
 *     at generation  --> "单独mark visit root"
 *
 *
 * BFS:
 * 1.始终需要init root : q.offer(root)
 * 2.need extra process?
 *     no need
 *     at expansion
 *     at generation  --> "单独process root"
 * 3.need V ds?
 *     no need
 *     at expansion
 *     at generation --> "单独mark visit root"
 *
 *
 * Graph.DFS3:
 * need V ds?
 *     no need
 *     at expansion
 *     at generation  --> "单独mark visit root"
 * need 实际操作recover?
 *     no need
 *     need   --> recover path ds, 如有额外V ds， 也要recover V ds
 */

/**
 * traversal vertex： DFS1, DFS2, BFS
 *                   有环且需要环信息：只能DFS2
 * traversal edge：   Graph.DFS3
 * unconnected:
 *      主函数中需要加外层循环
 * Undirected：
 *      通常要要build graph，注意通常build双向edge
 */

public class A_GraphTraversal_STEP {

}

