/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm_lab1;

/**
 *
 * @author The BigBang
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static algorithm_lab1.MySearch.table;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import algorithm_lab1.MySearch.BinarySearch;
import algorithm_lab1.MySearch.LinearSearch;

/**
 *
 * @author Jarkko
 */
public class MaxSubSeq {
    private static final int N = 10;        // number of test points
    private static final int STARTN = 10000;
    private static final int INCN = 5000;
    private static final String OUTPUTFILE = "C:\\Users\\The BigBang\\Desktop\\data1.csv";
    
    /**
     * @param args the command line arguments
     */
    
   
        
    
    
    public static void main(String[] args) throws IOException {
      
        int arrayN;
         PrintWriter writer1=null;
           arrayN = STARTN;
        try {          
            writer1= new PrintWriter(OUTPUTFILE, "UTF-8");
             writer1.println("N;Linear Search;Binary Search");
              StringBuilder result1 = new StringBuilder();
               Stopwatch sw1 = new Stopwatch();
            System.out.println("Number of CPU core " + Runtime.getRuntime().availableProcessors());
              for (int i = 0; i < N; i++) {
                 MySearch search=new MySearch();
                Comparable[] array= search.intArrayGenerator(arrayN);
               // Comparable[] array= search.stringArrayGenerator(arrayN);
                 Comparable[] elems = search.getTestElems(array);    
            LinearSearch search1=new LinearSearch();
             BinarySearch search2=new BinarySearch(); 
              search.getAnswers(search2,elems);
           result1.append(arrayN);     
            System.out.println("N " + arrayN);           
          sw1.measure(search1); 
          result1.append(";");
          sw1.toValue(result1);
          sw1.measure(search2); 
          result1.append(";");
          sw1.toValue(result1);
            result1.append("\n");
                System.out.println( result1.toString());
               arrayN += INCN;
              }
               writer1.println(result1.toString().replace(".",","));
                 writer1.flush();
                writer1.close();
             
            } 
    catch (FileNotFoundException ex) {
            Logger.getLogger(MaxSubSeq.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MaxSubSeq.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
             writer1.flush();
        writer1.close();
        }
      
      
    }

   

    
}

