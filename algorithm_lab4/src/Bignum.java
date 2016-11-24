
import java.math.BigInteger;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author The BigBang
 */
public class Bignum {
    private byte[] number;          // least significand digit first (index 0), most significand last (index length-1)
    private static int mulCounter;  // variable to count the number of multiplications


    public Bignum(int n) {
        number = new byte[n];
    }
    
    public Bignum(String s) {
        
        int     n = s.length();
        number = new byte[n];

        for (int i = n-1; i >= 0; i--)
            number[n-i-1] = (byte)Character.getNumericValue(s.charAt(i));
    }

    
    /* print out the number to the string s */
    public String toString() {
        StringBuilder s = new StringBuilder();
        
        for (int i = number.length-1; i >= 0; i--)
            s.append(number[i]);
        
        return (s.toString());
    }


    /* print out the given number (for debug only) */
    public void printBigNum(String s) {
        System.out.println(s + ": " + toString());
    }


    /* create a new number whose digits are x[from, to) */
    public Bignum selectBigNum(int from, int to) {
        Bignum r = new Bignum(to-from);

        for (int i = from; i < to; i++)
            r.number[i-from] = number[i];

        return r;
    }


    /* subtract two numbers this - y */
    public Bignum subBigNum(Bignum y) throws Exception {
            Bignum r = new Bignum(number.length);
            int    carry;

            // sub digits, starting from the least significant digit
            carry = 0;
            for (int i = 0; i < number.length; i++) {
                r.number[i] = (byte)(number[i] - (i < y.number.length ? y.number[i] : 0) - carry);
                if (r.number[i] < 0) {
                    carry = 1;
                    r.number[i] += 10;
                } else
                    carry = 0;
        }

        if (carry > 0) {
            throw new Exception("Overflow in subtraction\n");
        }

        return r;
    }


    /* add two numbers together this + y */
    public Bignum addBigNum(Bignum y) {
        Bignum r, a, b;
        int    carry;

        // a is the larger number, b is the smaller
        if (number.length > y.number.length) {
            a = this; b = y;
        } else {
            a = y; b = this;
        }

        r = new Bignum(a.number.length);

        // add digits, starting from the least significant digit
        carry = 0;
        for (int i = 0; i < a.number.length; i++) {
            r.number[i] = (byte)(a.number[i] + (i < b.number.length ? b.number[i] : 0) + carry);
            if (r.number[i] > 9) {
                carry = 1;
                r.number[i] -= 10;
            } else
                carry = 0;
        }

        if (carry > 0) {
            r.number = Arrays.copyOf(r.number, r.number.length+1);
            r.number[r.number.length-1] = 1;
        }

        return r;
    }


    /* multiply two numbers (this * y) together using divide-and-conquer technique 
    public Bignum mulBigNum(Bignum y) throws Exception {
     
        if(this.number.length==1){
        int x= (this.number[0] )*(y.number[0] );
        String s=x+"";
        Bignum single=new Bignum(s);
        return single;
        }
           int thisLength=this.number.length;
        int yLength=y.number.length;
        if(thisLength!=yLength){
            System.out.println("herer");
            if(thisLength>yLength)  {
                for(int i=0; i< thisLength-yLength; i++){              
                    byte[] zero=new byte[1];
                    zero[0]=0;
                     byte[] combined=new byte[zero.length+y.number.length];
                     for (int j = 0; j < combined.length; ++j)
                        {
                            combined[j] = j < zero.length ? zero[j] : y.number[j - y.number.length];
                            y.number=combined;
                    }
                  
                }
            }
            if(thisLength<yLength)  {
                for(int i=0; i<yLength-thisLength; i++){              
                    byte[] zero=new byte[1];
                    zero[0]=0;
                     byte[] combined=new byte[zero.length+this.number.length];
                     for (int j = 0; j < combined.length; ++j)
                        {
                            combined[j] = j < zero.length ? zero[j] : this.number[j - this.number.length];
                            this.number=combined;
                    }
                  
                }
            }
        }
         Bignum current=this;
        int length=this.number.length;
        int fh=length/2;
        //System.out.println(fh);
             
       
        Bignum thisFirstHalf=current.selectBigNum(0, fh);
        //System.out.println("The first "+thisFirstHalf+ " number "+ this);
        Bignum thisSecondHalf=current.selectBigNum(fh, length);
        // System.out.println("The second "+ thisSecondHalf + " number "+ this);
         Bignum otherFirstHalf=y.selectBigNum(0, fh);
        // System.out.println("The first  of y "+ otherFirstHalf + " number "+ y);
         Bignum otherSecondHalf=y.selectBigNum(fh, length);
        // System.out.println("The second of  y "+ otherSecondHalf + " number "+ y);
            
            this.number=thisFirstHalf.number;
             Bignum first=mulBigNum(otherFirstHalf);
             //System.out.println("The first "+first.toString());
             this.number=thisSecondHalf.number;
             Bignum second=mulBigNum(otherSecondHalf);
             // System.out.println("The s "+second.toString());
              this.number=thisFirstHalf.number;
              Bignum added1=addBigNum(thisSecondHalf);
             // System.out.println("The ass "+added1.toString());
              this.number=otherFirstHalf.number;
              Bignum added2=addBigNum(otherSecondHalf);
             // System.out.println("The firsssdtt "+added2.toString());
              this.number=added1.number;
              Bignum third=mulBigNum(added2);
              // System.out.println("The firstt "+((third.toString())* (Math.pow(10,length))));
            
              ///System.out.println( "first value "+ Integer.parseInt(third.toString()) + " The second" +Integer.parseInt(first.toString()));
             // System.out.println("The power check "+ (Integer.parseInt(first.toString()))+"The lne " +Math.pow(10,length) );
              
             //  System.out.println("The num "+ ((Integer.parseInt(third.toString())) - (Integer.parseInt(first.toString()))));
             
            System.out.println(("This "+((new BigInteger(first.number).intValue()))*(int)(Math.pow(10,length))) + ((new BigInteger(third.number).intValue()) - (new BigInteger(first.number).intValue()) - (new BigInteger(second.number).intValue()))*(int)(Math.pow(10,(fh))) + (new BigInteger(second.number).intValue()));
          System.out.println("The n " +new BigInteger(third.number).intValue()*(int)(Math.pow(10,length)));
         return y;
                
    }
*/public Bignum mulBigNum(Bignum y) throws Exception {
        Bignum r, a, b;
        
        // a is the larger number, b is the smaller
        if (number.length > y.number.length) {
            a = this; b = y;
        } else {
            a = y;
            b = this;
        }
        if(a.number.length==1){
            int m = a.number[0]*b.number[0];
            mulCounter++;
            if(m>9){
                r = new Bignum(2);
                r.number[0] = (byte)(m % 10);
                r.number[1] =  (byte)(m / 10);
            }else{
                r = new Bignum(1);
                r.number[0] = (byte)m;
            }
            return r;
        }

        if(a.number.length>b.number.length){
            b.number = Arrays.copyOf(b.number,a.number.length);
        }

        int n = a.number.length;
        int h = (int)Math.ceil(n/2.0);
        Bignum h1 = a.selectBigNum(0,h);
        Bignum h2 = a.selectBigNum(h,n);
        Bignum y1 = b.selectBigNum(0,h);
        Bignum y2 = b.selectBigNum(h,n);

        //multiply p1 * 10^n +( p3-(p2+p1))* 10^(n/2) + p2
        Bignum p1 = h2.mulBigNum(y2); 
        Bignum p2 = h1.mulBigNum(y1);
        Bignum p3 = (h2.addBigNum(h1)).mulBigNum(y2.addBigNum(y1));
        
        //for power
        int k = n%2==0?n:n+1;
        Bignum first = p1.powerGenerate(k);
        
        //subtract
        Bignum second = p3.subBigNum(p1.addBigNum(p2)).powerGenerate(k/2);
        
        //add all
        r = first.addBigNum(second).addBigNum(p2);
        return r;
    }
            private Bignum powerGenerate(int n){
        Bignum r = new Bignum(number.length+n);
        for(int i = 0;i<n;i++){
            r.number[i] = 0;
        }
        for(int i = n ;i < number.length+n;i++){
            r.number[i] = number[i-n];
        }
        return r;
    }

    public void clrMulCounter() {
        mulCounter = 0;
    }


    public int rclMulCounter() {
        return (mulCounter);
    }
}
 