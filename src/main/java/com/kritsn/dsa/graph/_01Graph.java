package com.kritsn.dsa.graph;

import com.kritsn.utils.Edge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
use for loop in main method, so that we can cover disconnected graphs as well

e.g.:
  1st example===>
        // use for loop, this covers discontinued graph as well (if we do not use below for loop
        // there are chance we may miss aon or more discontinued graphs (which are parts of given graphs)
        boolean[] visited1 = new boolean[v];
        for (int i = 0; i < visited1.length; i++) {
            if (!visited1[i]) { //not visited
                bfsMuchBetter(graph, visited1, i);
            }
        }


   2nd example===>
        // use for loop, if we do not use below for loop
        // there are chance we may miss aon or more discontinued graphs (which are parts of given graphs)
        boolean[] visited3 = new boolean[v];
        for (int i = 0; i < visited3.length; i++) {
            if (!visited3[i]) { //not visited
                dfs(graph, visited3, i);
            }
        }
 */

public class _01Graph {

    private static void createGraph(ArrayList<Edge>[] graph) {
        /*
                1 --- 3
               /      | \
              0       |  5 -- 6
               \      | /
                2 --- 4
         */
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 1, 0));
        graph[0].add(new Edge(0, 2, 0));

        graph[1].add(new Edge(1, 0, 0));
        graph[1].add(new Edge(1, 3, 0));

        graph[2].add(new Edge(2, 0, 0));
        graph[2].add(new Edge(2, 4, 0));

        graph[3].add(new Edge(3, 1, 0));
        graph[3].add(new Edge(3, 4, 0));
        graph[3].add(new Edge(3, 5, 0));

        graph[4].add(new Edge(4, 2, 0));
        graph[4].add(new Edge(4, 3, 0));
        graph[4].add(new Edge(4, 5, 0));

        graph[5].add(new Edge(5, 3, 0));
        graph[5].add(new Edge(5, 4, 0));
        graph[5].add(new Edge(5, 6, 0));

        graph[6].add(new Edge(6, 5, 0));
    }

    //BFS Transversal, same as below, just visited and curr is hardcoded/local
    public static void bfs(ArrayList<Edge>[] graph) {
        //indirect level order transeverse/search
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
//        queue.add(graph[0].get(0).src());
        queue.add(0);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (!visited[curr]) { //not visited
                //step1: print/use the current Vertex
                System.out.print(curr + " ");

                //step 2: smark in visited as true
                visited[curr] = true;

                //step 3: add neighbour to the queue
                ArrayList<Edge> neighbours = graph[curr];
                for (Edge edge : neighbours) {
                    int neighbour = edge.dest();
                    queue.add(neighbour);
                }
            }
        }
        System.out.println();
    }

    //BFS Transversal, same as above, just made it a bit dynamic
    public static void bfsMuchBetter(ArrayList<Edge>[] graph, boolean[] visited, int start) {
        //indirect level order transeverse/search
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (!visited[curr]) { //not visited
                //step1: print/use the current Vertex
                System.out.print(curr + " ");

                //step 2: smark in visited as true
                visited[curr] = true;

                //step 3: add neighbour to the queue
                ArrayList<Edge> neighbours = graph[curr];
                for (Edge edge : neighbours) {
                    int neighbour = edge.dest();
                    queue.add(neighbour);
                }
            }
        }
        System.out.println();
    }

    //DFS Transversal O (V+E)
    public static void dfs(ArrayList<Edge>[] graph, boolean[] visited, int curr) {
        //step1: print/use the current Vertex
        System.out.print(curr + " ");

        //step 2: smark in visited as true
        visited[curr] = true;

        //step 3: add neighbour to the queue
        ArrayList<Edge> neighbours = graph[curr];
        for (Edge edge : neighbours) {
            int nextNeighbour = edge.dest();
            if (!visited[nextNeighbour]) { //not visited
                dfs(graph, visited, nextNeighbour); //recursion ==> DFS
            }
        }

    }

    //Ques1: Print all path from given src to destination/target TC: O(V^V)
    public static void printAllPath(ArrayList<Edge>[] graph, boolean[] visited, int curr, int target, String path) {
        if (curr == target) {
            System.out.println(path);
//            path = "";
            return;
        }
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!visited[e.dest]) {
                visited[curr] = true;
                path = path + e.dest();
                printAllPath(graph, visited, e.dest(), target, path);
                visited[curr] = false;
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Main method
    /// ////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        int v = 7;
         /*
                1 --- 3
               /      | \
              0       |  5 -- 6
               \      | /
                2 --- 4
         */
        ArrayList<Edge>[] graph = new ArrayList[v];
        createGraph(graph);
        bfs(graph);

        // Best approach (this covers discontinued graph as well (if we do not use below for loop
        // there are chance we may miss aon or more discontinued graphs (which are parts of given graphs)
        boolean[] visited1 = new boolean[v];
        for (int i = 0; i < visited1.length; i++) {
            if (!visited1[i]) { //not visited
                bfsMuchBetter(graph, visited1, i);
            }
        }

        //dfs
        boolean[] visited2 = new boolean[v];
        dfs(graph, visited2, 0);

        System.out.println();
        // use for loop, if we do not use below for loop
        // there are chance we may miss aon or more discontinued graphs (which are parts of given graphs)
        boolean[] visited3 = new boolean[v];
        for (int i = 0; i < visited3.length; i++) {
            if (!visited3[i]) { //not visited
                dfs(graph, visited3, i);
            }
        }

        //ques 1: Print all path from given src to destination/target
        System.out.println();
        System.out.println("-----------------------");
        boolean[] visited4 = new boolean[v];
        int src = 0;
        int target = 5;
        printAllPath(graph, visited4, src, target, "" + src);
    }
}
