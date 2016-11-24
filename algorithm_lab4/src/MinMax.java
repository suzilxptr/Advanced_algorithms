
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
public class MinMax {

    Comparable[] array;
    int min = 0;
    int max = 0;
    int comp = 0;

    MinMax(Comparable[] array) {
        this.array = array;
    }

    public int getMax() {

        return max;
    }

    public int getMin() {

        return min;
    }

    public int getComparisons() {

        return comp;
    }

    public void minmax() {

        int i, start;
// check simple cases first
        if (array.length < 1) {
            return;
        } else if (array.length == 1) {
            min = 0;
            max = 0;
            return;
        }
        start = 1;
        if (array.length % 2 == 1) { // odd lenght
            min = 0;
            max = 0;
        } else { // even lenght
            if (array[0].compareTo(array[1]) > 0) {
                comp++;
                min = 1;
                max = 0;

            } else {
                min = 0;
                max = 1;
                comp++;

            }
            start = 2;
        }
// compare remaining pairs
        for (i = start; i < array.length; i += 2) {
            comp++;

// find larger of base[i] and base[i+1], then compare larger one
// with base[max] and smaller one with base[min]
            if (array[i].compareTo(array[i + 1]) > 0) {
                comp++;
                comp++;
                if (array[i].compareTo(array[max]) > 0) {

                    max = i;
                }

                if (array[i + 1].compareTo(array[min]) < 0) {

                    min = i + 1;
                }
            } else {
                comp++;
                comp++;
                if (array[i + 1].compareTo(array[max]) > 0) {

                    max = i + 1;
                }

                if (array[i].compareTo(array[min]) < 0) {

                    min = i;
                }
            }
        }

    }
// min and max element locations (indexes) are returned

    public void minmax2() {

        for (int i = 1; i < array.length; i++) {

            comp++;
           

            Comparable elem = array[i];
            if (elem.compareTo(array[max]) > 0) {

                max = i;
            }
            comp++;
            if (elem.compareTo(array[min]) < 0) {
                min = i;

            }

        }

    }
}
