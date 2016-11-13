
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author The BigBang
 */
public class Sort {
      static int INIT =100;
      static int ADD=100;
      
      private static final String OUTPUTFILE = "C:\\Users\\The BigBang\\Desktop\\bubble.csv";
      
      
       public static String randomStringArray(){
     Random random=new Random();
      String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();      
        while (salt.length() < 32) {
            int index = (int) (random.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        
        return saltStr;
        
    }
        public static void  main(String args[]) throws FileNotFoundException, UnsupportedEncodingException{    
        PrintWriter writer= new PrintWriter(OUTPUTFILE, "UTF-8");
         writer.println("N;Insert;bubble");
         StringBuilder result = new StringBuilder();
         Stopwatch sw1 = new Stopwatch();
        for(int i=0; i<10; i++){
        insertionsort insert=new insertionsort(INIT);
        bubblesort quick= new bubblesort(INIT);
        //quickSort quick=new quickSort(INIT);
        //insert.arrayGen(INIT);
         result.append(INIT); 
        sw1.measure(insert);
         result.append(";");
         sw1.toValue(result);
         sw1.measure(quick);
         result.append(";");
         sw1.toValue(result);
          result.append("\n");
        INIT=INIT+ADD;
        System.out.println(result.toString());
        
        }
          writer.println(result.toString().replace(".",","));
          writer.flush();
          writer.close();
    }
    
    public static class insertionsort implements Stopwatch.Test{
         Comparable[] array;
         int n;
         
         
         public insertionsort(int n){
             this.n=n;
         
         }
         
         
        @Override
        public void setup() {
            arrayGen(n);
          //Arrays.sort(array);
        }

        @Override
        public void test() {
            doInsertionSort((String[]) array);
        }
         
       public void arrayGen(int n){
           array=new String[n];
         for(int i=0; i<n; i++){
            String random=randomStringArray();
            array[i]=random;
        }
       //  for(int i=0; i<array.length; i++){
         //  System.out.println(array[i]+ "at "+ i);
       //}     
       }
    
   
     
     
    public static String[] doInsertionSort(String[] input){        
        String temp;
        for (int i = 1; i < input.length; i++) {
            for(int j = i ; j > 0 ; j--){
                if(input[j].compareTo(input[j-1])<1){
                    temp = input[j];
                    input[j] = input[j-1];
                    input[j-1] = temp;
                }
            }
        }
        return input;
    }
    }
    public static class quickSort implements Stopwatch.Test{
    Comparable[] array;
         int n;
         
         
         public quickSort(int n){
             this.n=n;
         
         }
         
         
        @Override
        public void setup() {
            arrayGen(n);
          
        }

        @Override
        public void test() {
            Arrays.sort(array);
        }
         
       public void arrayGen(int n){
           array=new String[n];
         for(int i=0; i<n; i++){
            String random=randomStringArray();
            array[i]=random;
        }
     
       }
    
    
    

    
    }
    
    public static class bubblesort implements Stopwatch.Test{
        Comparable[] array;
         int n;
         public void arrayGen(int n){
           array=new String[n];
         for(int i=0; i<n; i++){
            String random=randomStringArray();
            array[i]=random;
        }
     
       }
         
         public bubblesort(int n){
             this.n=n;
         
         }
        @Override
        public void setup() {
            arrayGen(n);
           //  Arrays.sort(array);
           
        }

        @Override
        public void test() {
            bubbleSort((String[]) array);
        }
    
     public static void bubbleSort(String[] strArray) {
         int n = strArray.length;
                String temp ;             
                for(int i=0; i < n-1; i++){
                        for(int j=1; j < n-1; j++){
                               
                                if(strArray[j].compareTo(strArray[j+1])>0){                                   
                                      temp = strArray[j];
                                        strArray[j] = strArray[j+1];
                                        strArray[j+1] = temp;
                                }
                               
                        }
                }
       
        }
    
    }
    
   

    
    }


