
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author The BigBang
 */
public class Main {

    private static final String EASY20 = "C:\\Users\\The BigBang\\Desktop\\easy20.txt";
    private static final String HARD33 = "C:\\Users\\The BigBang\\Desktop\\hard33.txt";
    private static ArrayList<Item> items = new ArrayList<Item>();
    static int capacity = 0;
  
    static private int bestValue;
    static private int bestWgt = 0;   // best value found so far
    static private boolean solution[]; // true entries = items giving best value
    static private boolean current[];
    static int count=1;
    static int displayAt=0;
    static double all;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File(HARD33));
        while (s.hasNext()) {
            String aLine = s.nextLine();
            System.out.println(aLine);
            String[] splittedString = aLine.split("\\s+");
            if (splittedString.length > 1) {
                Item newItem = new Item(Integer.parseInt(splittedString[2]), Integer.parseInt(splittedString[3]), Integer.parseInt(splittedString[1]));
                items.add(newItem);
            } else {
                capacity = Integer.parseInt(aLine);
            }
        }
        all= Math.pow(2, items.size());
        bestValue = Integer.MIN_VALUE;
        solution = new boolean[items.size() + 1];
        current = new boolean[items.size() + 1];   
      solve(items.size()-1);
       printSolution();   
        
        

    }
    

    public static void solve(int k) {

        // Base case: All items have been considered, so check result:
        if (k < 0) {
            
            // Find total weight and total value:
            int wt = 0;
            int val = 0;
            for (int i = 0; i < items.size(); i++) {
                if (current[i]) {
                    wt += items.get(i).getWeight();
                    val += items.get(i).getValue();
                }
            }

            // Check to see if we've got a better solution:
            if (wt <= capacity && val > bestValue) {
                bestValue = val;
                bestWgt = wt;
                copySolution();
                
            }

            return;
        }
            
        // Recursive case: there are two possibilities for item k -- either
        // we select it for the knapsack or we don't. Try each possibility:
        current[k] = true;
        solve(k - 1);
        
        current[k] = false;
        solve(k - 1);
         count++;
         if(count==1000000){
             displayAt=displayAt+count;
             double fraction= (displayAt/all);
             System.out.println( (fraction*100)+ "% of the search space enumerated so far");
             count=1;
         }
    }

    public static void printSolution() {
        StringBuilder bestIndex = new StringBuilder();
        int bestWeight = 0;

        for (int i = 0; i < items.size(); i++) {
            if (solution[i]) {
                bestIndex.append(i + 1 + " ");
            }
        }
        System.out.println("Optimal Solution found: " + bestValue + " " + bestWgt );
        System.out.println(bestIndex.toString());
    }

    public static void copySolution() {
        for (int i = 0; i < items.size(); i++) {
            solution[i] = current[i];
        }
    }
    
    
    
    
    
 

}




