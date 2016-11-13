/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm_lab1;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author The BigBang
 */
public class MySearch {
    public static Comparable[] table;
    private int n;
    public static int last=0;
   static int[] answers;
   static Comparable[] elems;
    
  
     public  int[] getAnswers(BinarySearch mySearch, Comparable[] elems) {
        int[] answers = new int[500];
        for (int i = 0; i < 500; i++) {
            answers[i] = mySearch.BinarySearch(elems[i]);
        }
        this.answers=answers;
        this.elems=elems;
        return answers;
    }
     
     public Comparable[] intArrayGenerator(int n){
         this.n=n;
          Random randomGenerator = new Random();
            table = new Integer[n];
            int i=0;
            for (Comparable e: table){
                e = randomGenerator.nextInt(Integer.MAX_VALUE); 
                table[i]=e;
                i++;
            }   
               Arrays.sort(table);
               last=table.length-1;
            return table;
        }
     
      public static Comparable[] stringArrayGenerator(int n) {
        Random random = new Random();
         table = new String[n];
        for (int i = 0; i < n; i++) {
            table[i] = new BigInteger(130, random).toString(32);
        }
        Arrays.sort(table);
        return table;
    }
      
     public static Comparable[] getTestElems(Comparable[] array) {
        Random random = new Random();
        Comparable[] elems = new Comparable[500];
        for (int i = 0; i < 500; i++) {
            elems[i] = array[random.nextInt(array.length)];
        }
        return elems;
    }
    
    public static class LinearSearch implements Stopwatch.Test {
        
        private int  answer;
        int valueToBeSearched;
       
     
        
      
        @Override
        public void test() {
               
             for(int i=0; i<500; i++){
            if ((LinearSearchAlgorithm(elems[i])) != answers[i])
                throw new IllegalStateException("Incorrect function to be tested operation");
             }
        }
        
        /* linear-time maximum contiguous subsequence sum algorithm */
       
        int LinearSearchAlgorithm(Comparable elem) {
            //System.out.println("Table lenght "+ table.length);
             
      for ( int c = 0; c <table.length; c++) {     
      if (table[c].compareTo(elem)==0) {
         
     
         return c;
      }
   }

   return -1;
        }
    }
    
    
    public static class BinarySearch implements Stopwatch.Test {
        private int  answer;
        int valueToBeSearched;
        
     
        
        @Override
        public void test() {
            for(int i=0; i<500; i++){
            if ((BinarySearch(elems[i])) != answers[i])       
                throw new IllegalStateException("Incorrect function to be tested operation");
              }
        }
        
        /* linear-time maximum contiguous subsequence sum algorithm */
         public int BinarySearch(Comparable elem) {
        int start = 0;
        int end = table.length - 1;
        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (table[middle].compareTo(elem) == 0) {
                return middle;
            }
            if (table[middle].compareTo(elem) < 0) {
                start = middle + 1;
                continue;
            }
            end = middle - 1;
        }
        return -1;
    }
    }
    
   


     

  
}
 
 

    
    

