/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm_lab5;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author The BigBang
 */
public class Algorithm_lab5 {

    /* find a maze solution */
private static int mazeSolution(int from, int to, int pred[], int steps[]) {
    for(int i=0; i<pred.length; i++){
        System.out.println("the value "+pred[i]);
    }
int i, n, node;
// first count how many edges between the end and the start
node = to; n = 1;
while ((node = pred[node]) != from){
n++;
}
System.out.println("number of edges "+ n);
// then reverse pred[] array to steps[] array
node = to; i = n;
while (i >= 0) {
  
steps[i--] = node;
node = pred[node];
}
// include also the end vertex
return (n+1);
}
private final static String FILE = "C:\\Users\\The BigBang\\Desktop\\maze.grh";
private final static int FROM = 0;
private final static int TO = 15;
/**
* @param args the command line arguments
*/
public static void main(String[] args) throws FileNotFoundException { 
Graph g = new Graph();
// read the graph. and do the depth-first search
System.out.println("Graph Adjacent list");
g.readGraph(new File(FILE));
g.printGraph();
boolean visited[] = new boolean[g.nodes()];
int pred[] = new int[g.nodes()];
g.dfs(FROM, visited, pred);
//g.printPred();
// then check if there is a solution by looking from the backwards to the start
int steps[] = new int[g.nodes()];
System.out.println("\nMaze solution from " + FROM + " to " + TO);;

int n = mazeSolution(FROM, TO, pred, steps);
for (int i = 0; i < n; i++)
System.out.print(steps[i] + " ");
System.out.println("");
}
    
}
