package Graph.BFS1.Plan1;
import java.util.*;

/**
 *
 * 这道题的难点：
 * graph node既是bus route，优势bus stop
 * initial nodes: all the bus routes with the  init stop  (can start from any of one)
 * goal nodes: all the bus route with the goal  stop  (can reach any of one)
 * find the shortest Path from any of initial buses that stop at S, to any of the target buses that stop at T.
 *
 * https://docs.google.com/document/d/1sg4RL7iy5N4gFHjzMdjNvLmmA3TaIxP1LjwF_eKEJ1I/edit#
 * vertex:           bus  stop (红色是bus1，本质看成一个vertex；蓝色是bus2，本质看成一个vertex)
 * neighbors:        经过这个stop的所有bus routes
 * edge:             vertex 到neighbors的边，权重相同
 */
public class BusRoutes {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (routes == null || routes.length == 0) {
            return -1;
        }
        if (S == T) {
            return 0;
        }
        /**build map*/
        Map<Integer, List<Integer>> stopToRoutes = new HashMap<>(); //<bus stop, List<bus route>>
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopToRoutes.putIfAbsent(stop, new ArrayList<>());
                stopToRoutes.get(stop).add(i);
            }
        }
        /**Graph.BFS1： init: bus stop S, goal: bus stop T, 对bus route进行mark visited*/
        boolean[] visited = new boolean[routes.length];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(S);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                //expansion
                int curStop = queue.poll();
                //generation
                for (int route : stopToRoutes.get(curStop)) {  /**generate一层route*/
                    if (!visited[route]) {
                        visited[route] = true;  /**仍然算是mark visited at expansion，因为还没进入真正generate循环*/
                        for (int stop : routes[route]) { /**generate一层bus stop,真正的generate部分*/
                            if (stop == T) {
                                return level + 1;
                            }
                            queue.offer(stop);
                        }
                    }
                }
            }
            level++;
        }
        return -1;
    }
}

