
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author The BigBang
 */
public class MyDictionary {

    int n;
    HashNode node;
    private ArrayList<HashNode> hashTable;
     int len;

    public MyDictionary(int n) {
       
        len=nextPrime(n);
        this.n=len;
        
        // System.out.println("The number is "+ len);
        hashTable = new ArrayList<HashNode>();
        for (int i = 0; i < len; i++) {
            hashTable.add(null);
           
        }
    }

    private static final int MULTIPLIER = 31;

    private int hash(String str) {
        int h;
        h = 0;
        for (int i = 0; i < str.length(); i++) {
            h = MULTIPLIER * h + str.charAt(i);
        }

        return h & Integer.MAX_VALUE;
    }

    public int getBucketIndex(String key) {
        int hashCode = hash(key);
      
        int index = hashCode % len;
        return index;
    }
/*public Object get(String key){
    int hashIndex=getBucketIndex(key);
    hashNode defaulthead=hashTable.get(hashIndex);
    while()
    
    }   */
    public Object get(String key) {
        int hashIndex = getBucketIndex(key);
        HashNode head = hashTable.get(hashIndex);

        while (head != null) {
            if (head.getV().equals(key)) {
                
                return head.getObj();
            }
            head = head.next;
        }
        return null;
    }
   

    public int put(Object obj, String key) {

        int bucketIndex = getBucketIndex(key);
        HashNode head = hashTable.get(bucketIndex);

        while (head != null) {
            if (head.getV().equals(key)) {               
                head.setObj(obj);  
              //  System.out.println("The key also "+ key + " at "+ bucketIndex);
                return bucketIndex;
            }
            // System.out.println("The key iteration "+ key + " at "+ bucketIndex);
            head = head.next;
        }
        n++;
        head = hashTable.get(bucketIndex);
        HashNode newNode = new HashNode();
        newNode.setObj(obj);
        newNode.setV(key);
        newNode.next = head;
        hashTable.set(bucketIndex, newNode);
       // System.out.println("The element added first time "+ key + " at "+ bucketIndex);
      // System.out.println("The size "+ (1.0*count)/(hashTable.size()));
     
      /*  if(((1.0*count)/(hashTable.size()))>0.8){
            
           System.out.println("sasasasss");
        ArrayList<HashNode> newArray=hashTable;
        hashTable=new ArrayList<HashNode>();
   
        for(int i=0; i<=nextPrime(n); i++){
           
        hashTable.add(null);
        }
        for(HashNode hash:newArray){
        while(hash!=null){
        put(hash.getObj(),hash.getV());
        hash=hash.next;
        }
        }
        n=nextPrime(n);
        
        }
        */

        return 0;
    }
    

    private static int nextPrime(int n) {
        
        int prime = 0, i, nextPrime;
        /* check first if this is a prime number */
        for (i = 2; i < n / 2; i++) {
            if (n % i == 0) {
                prime = 1;
                break;
            }
        }
        if (prime == 1) {
            /* no, try to find next one */
            nextPrime = n;
            prime = 1;
            while (prime != 0) {
                nextPrime++;
                prime = 0;
                for (i = 2; i < nextPrime / 2; i++) {
                    if (nextPrime % i == 0) {
                        prime = 1;
                        break;
                    }
                }
            }
            return (nextPrime);
        } else /* yes, return this as is */ {
           
            return (n);
        }
    }

    void printDictionary() {
        for(int i=0; i<hashTable.size(); i++){
            if(hashTable.get(i)==null){
                System.out.println(i+ ": [empty]");
            }
            else{
                StringBuilder s=new StringBuilder();
            HashNode head=hashTable.get(i);
            s.append(i+ ":"+ head.getV());
            if(head.next!=null){
            s.append( ","+head.next.getV());
            }
            System.out.println(s.toString());
            }
        }

    }

    int del(String key) {
        int index=getBucketIndex(key);
        HashNode head=hashTable.get(index);
        HashNode prev=null;
        while(head!=null){
        if(head.getV().equals(key)){
        break;
        }
        prev=head;
        head=head.next;
        }
        if(head==null){
        return -1;
        }
        n--;
        if(prev!=null){
        prev.next=head.next;
        }
        else{
            hashTable.set(index, head.next);
            }
            
 
        return index;
    }
}
