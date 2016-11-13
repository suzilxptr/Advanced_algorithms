
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
public class LinearProbing {
    StringBuilder result;
    int n;
    HashNode node;
    private ArrayList<HashNode> hashTable;
    int len;

    public LinearProbing(int n) {
        len = nextPrime(n);
        this.n = len;
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

    public Object get(String key) {
        int hashIndex = getBucketIndex(key);
        HashNode defaulthead = hashTable.get(hashIndex);
        while (defaulthead != null) {

            if (defaulthead.getV().equals(key)) {
                return defaulthead.getObj();
            }

            hashIndex++;
            if (hashIndex == len) {
                hashIndex = 0;
            }
            defaulthead = hashTable.get(hashIndex);

        }
        return null;
    }

    public int put(Object obj, String key) {
         result=new StringBuilder();
        int bucketIndex = getBucketIndex(key);
        HashNode head = hashTable.get(bucketIndex);
        int flipTozeroIndex;
        int checkArray = 0;
        flipTozeroIndex = bucketIndex;

        while (head != null) {
            if (checkArray == len) {
                checkArray = 0;
                resize(len + 1);

                flipTozeroIndex = bucketIndex;
            }
            flipTozeroIndex++;
            if (flipTozeroIndex == len) {
                flipTozeroIndex = 0;

            }
            head = hashTable.get(flipTozeroIndex);
            if (head == null) {
                checkArray = 0;
                HashNode newNode = new HashNode();
                newNode.setObj(obj);
                newNode.setV(key);
                hashTable.set(flipTozeroIndex, newNode);
                result.append( bucketIndex +":"+ newNode.getV()+ " added at index " + flipTozeroIndex+ "\n");
                return flipTozeroIndex;
            }
            checkArray++;
        }
        HashNode newNode = new HashNode();
        newNode.setObj(obj);
        newNode.setV(key);
        hashTable.set(bucketIndex, newNode);
          result.append(bucketIndex + ":"+ newNode.getV()+ "\n");
        return bucketIndex;

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
          
      HashNode node= hashTable.get(i);
         if(node==null){
         System.out.println(i+ ":[empty]");
         }
      if(node!=null){
      String key= node.getV();
      int index= getBucketIndex(key);
      System.out.println( node.getV()+ " placed at index " +i+ " from index " +index );
      }
      }
    }

    void del(String key) {
        int bucketIndex = getBucketIndex(key);
        HashNode head = hashTable.get(bucketIndex);
        while (head != null) {
            if (head.getV().equals(key)) {
                hashTable.set(bucketIndex, null);
                ArrayList<HashNode> newArray = hashTable;
                hashTable = new ArrayList<HashNode>();
                for (int i = 0; i < newArray.size(); i++) {
                    hashTable.add(null);
                }

                for (int i = 0; i < newArray.size(); i++) {
                    HashNode copyToNewArray = newArray.get(i);
                    if (copyToNewArray != null) {
                        Object obj = copyToNewArray.getObj();
                        String key1 = copyToNewArray.getV();
                        put(obj, key1);
                    }
                }
                return;
            }
            bucketIndex++;
            if (bucketIndex == len) {
                bucketIndex = 0;
            }
            head = hashTable.get(bucketIndex);

        }

    }

    public void resize(int resizeFrom) {
        int resizeTo = nextPrime(resizeFrom);
        len = resizeTo;
        ArrayList<HashNode> newArray = hashTable;
        hashTable = new ArrayList<HashNode>();
        for (int i = 0; i < resizeTo; i++) {
            hashTable.add(null);
        }
        int count = 0;
        for (int i = 0; i < newArray.size(); i++) {
            HashNode copyToNewArray = newArray.get(i);
            if (copyToNewArray != null) {
                Object obj = copyToNewArray.getObj();
                String key = copyToNewArray.getV();
                put(obj, key);

            }
        }

    }

}
