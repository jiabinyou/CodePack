package Graph.BFS1.Plan1;

import java.util.*;

/**Note:这道题init point s是一定存在在graph中的，所以我们只需要从init point s开始，做一遍BFS即可解决问题
 * 不需要从graph中的每一个node开始,检查是否有path从s到t(这种是针对uconnected graph + s都不一定在graph中的情况)
 *
 * */
public class RouteBetweenTwoNodesInGraph {
    public boolean hasRoute(ArrayList<DirectedGraphNode> graph, DirectedGraphNode s, DirectedGraphNode t) {
        // sanity check
        if (graph == null || graph.size() == 0) {
            return false;
        }
        if (s == t) {
            return true;
        }
        Set<DirectedGraphNode> visited = new HashSet<>();
        Queue<DirectedGraphNode> q = new LinkedList<>();
        q.offer(s);
        while (!q.isEmpty()) {
            //expansion
            DirectedGraphNode cur = q.poll();
            if (cur == t) {
                return true;
            }
            //generation
            for (DirectedGraphNode nei : cur.neighbors) {
                if (visited.add(nei)) {
                    q.offer(nei);
                }
            }
        }
        return false;
    }
}

class DirectedGraphNode {
      int label;
      ArrayList<DirectedGraphNode> neighbors;
      DirectedGraphNode(int x) {
          label = x;
          neighbors = new ArrayList<DirectedGraphNode>();
      }
  }