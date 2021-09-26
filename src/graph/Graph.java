/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author mokarromh
 */
public class Graph {
    int numOfVertices;
    List<Integer> adjList[];
    
    public Graph(int v) {
        numOfVertices = v;
        adjList = new LinkedList[v];
        
        for (int i = 0; i < numOfVertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }
    
    public void addEdge(int src, int dst) {
        adjList[src].add(dst);
        adjList[dst].add(src); // add back edge for undirected/bi-directed graph
    }
    
    public void printGraph() {
        for (int i = 0; i < numOfVertices; i++) {
            
            if(adjList[i].size() > 0) {
                System.out.print("Vertex " + i + " is connected to: ");

                for (int j = 0; j < adjList[i].size(); j++) {
                    System.out.print(adjList[i].get(j) + " ");
                }
                
                System.out.println();
            }
        }
    }
    
    public void bfs(int startNode) {
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        boolean visited[] = new boolean[numOfVertices];
        
        visited[startNode] = true;  // mark source vertex as discovered
        bfsQueue.add(startNode);    // push source vertex into the queue
        
        while (!bfsQueue.isEmpty()) {
            // pop front node from the Queue and print it.
            int v = bfsQueue.poll();
            System.out.print(v + ", ");
            
            //for every edge (v -> w)
            Iterator<Integer> itr = adjList[v].listIterator();
            while (itr.hasNext()) {
                int w = itr.next();
                
                if (visited[w] == false) {
                    visited[w] = true;
                    bfsQueue.add(w);
                }
            }
        }
        System.out.println();
    }
    
    public void dfs(int startNode) {
        Stack<Integer> dfsStack = new Stack();
        boolean visited[] = new boolean[numOfVertices];
        
        dfsStack.push(startNode);
        
        while (!dfsStack.isEmpty()) {
            //pop stack top
            int v = dfsStack.pop();
            if (visited[v] == false) {
                visited[v] = true;
                System.out.print(v + ", ");
                
                //for every edge (v -> w)
                Iterator<Integer> itr = adjList[v].iterator();                   
                while (itr.hasNext()) { 
                    int w = itr.next(); 
                    if(visited[w] == false) 
                        dfsStack.push(w); 
                } 
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        test1();
    }
    
    public static void test1() {
        Graph graph = new Graph(5);

        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        graph.printGraph();
        
        graph.bfs(0);
        graph.dfs(0);
    }
}
