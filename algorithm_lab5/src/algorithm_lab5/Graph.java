/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm_lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author The BigBang
 */
public class Graph {

    int node = 0;

    LinkedList<Integer>[] adjList;
    int route[];

    /* tell how many nodes a graph has */
    public Graph() {
        adjList = new LinkedList[16];
        for (int i = 0; i <= 15; ++i) {
            adjList[i] = new LinkedList();
        }
    }

    public int nodes() {

        return node;
    }

    ;

 /* read a graph from the file */
 public boolean readGraph(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        int vertexCount = 0;
        while (s.hasNext()) {
            node++;
            String aLine = s.nextLine();
            String[] splittedString = aLine.split("\\s+");
            for (int i = splittedString.length - 1; i > 0; i--) {
                String each = splittedString[i];
                int toNum = Integer.parseInt(each);
                adjList[vertexCount].add(toNum);
            }
            vertexCount++;
        }
        return true;
    }

    ;

 /* print a graph */
    void printGraph() {
        StringBuilder theGraph = new StringBuilder();
        for (int i = 0; i < adjList.length; i++) {
            theGraph.append(i + ":");
            LinkedList items = adjList[i];
            for (int j = 0; j < items.size(); j++) {
                theGraph.append(" " + items.get(j));
            }
            theGraph.append("\n");

        }
        System.out.println(theGraph.toString());

    }
    ; 
/* Depth First Search
 * start is the node from where the search begins
 * visited is an array of all the visited nodes
 * pred is an describing the search path
 */

   public void dfs(int start, boolean visited[], int pred[]) {
        visited[start] = true;    
        Iterator<Integer> i = adjList[start].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                pred[n] = start;
                dfs(n, visited, pred);
            }
        }

    }
}
