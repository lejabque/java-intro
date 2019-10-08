import java.util.*;
import java.io.*;

public class test {
    public static void main(String[] args) throws FileNotFoundException {
        java.util.Scanner newScanner = new java.util.Scanner(new File("/home/lejabque/study/ct_y2019/term1/hw/prog_intro/hw5/src/input.txt"));
        System.out.println(newScanner.hasNext());
        System.out.println(newScanner.nextLine());
        System.out.println(newScanner.hasNextInt());
        System.out.println(newScanner.next());
        System.out.println(newScanner.hasNextInt());
        System.out.println(newScanner.nextInt());
        System.out.println(newScanner.hasNextLine());
        System.out.println(newScanner.nextLine());
        System.out.println(newScanner.nextInt());
        System.out.println(newScanner.hasNextInt());


    }
}
