package com.kritsn.dsa.graph;

import com.kritsn.utils.Edge;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 02, 2026
 */

public class _02GraphCycleDetection {
    private static void createGraph(ArrayList<Edge>[] graph) {

        /*
                 2
               /  \             ---> Cycle Undirected/bidirected Graph
              0 -- 3
              |
              1
         */
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
        }

//        graph[0].add(new Edge(0, 1, 0));
        graph[0].add(new Edge(0, 2, 0));
//        graph[0].add(new Edge(0, 3, 0));

        graph[1].add(new Edge(1, 0, 0));

//        graph[2].add(new Edge(2, 0, 0));
        graph[2].add(new Edge(2, 3, 0));

//        graph[3].add(new Edge(3, 0, 0));
//        graph[3].add(new Edge(3, 2, 0));
    }


    //Cycle detection : Directed Graph
    public static boolean cycleDetectionDirectional(String[] args) {

        return true;
    }

    //Cycle detection: Undirected/biderectional
    //TC O(V+E)
    public static boolean cycleDetectionUndirectional(List<Edge>[] graph, boolean[] vis, int curr, boolean[] recStack) {
        vis[curr] = true;
        recStack[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (recStack[e.dest]) { //case for cycle detection
                return true;
            } else if (!vis[e.dest()]) {
                if (cycleDetectionUndirectional(graph, vis, e.dest(), recStack)) {
                    //here also if my this neighbour has cycle then also return true;
                    //means cycle is present in my this neighbour
                    return true;
                }
            }
        }
        recStack[curr] = false;
        return false;
    }

    public static void main(String[] args) {
        int v = 4;
        ArrayList<Edge> graph[] = new ArrayList[v];
        createGraph(graph);

        boolean[] vis = new boolean[v];
        boolean[] recStack = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!vis[i]) {
                boolean isCycle = cycleDetectionUndirectional(graph, vis, 0, recStack);
                if (isCycle) {
                    System.out.println("Cycle found status: " + isCycle);
                    break;//cycle found no need to look further
                }
            }
        }

    }
}
