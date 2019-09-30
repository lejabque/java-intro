import java.io.*;
import java.util.*;

public class Main {
    private static Scanner openInput()
//      throws FileNotFoundException
    {
        try {
            return new Scanner(new File("input.txt"), "UTF-8");
        } catch (FileNotFoundException e) {
            return new Scanner("");
        }
    }

    public static void main(String[] args) {
        try {
            Reader in = new FileReader("input.txt");
            /*
            new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(new File("input.txt")),
                    "utf8"
                ),
                1024
            );
            */
            try {
                PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                        new OutputStreamWriter(
                            new FileOutputStream(new File("output.txt")),
                            "utf8"
                        ),
                        1024
                    )
                );
                try {
                    //in.readLine();
                    char[] buffer = new char[100];
                    while (true) {
                        int count = in.read(buffer);
                        if (count == -1) {
                            System.out.println("read: " + in.read());
                            System.out.println("read: " + in.read());
                            break;
                        }

                        //out.write(buffer, 0, count);
                        out.print(new String(buffer, 0, count));
                    }
                } finally {
                    out.close();
                }
            } finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
        //Scanner in = openInput();
        //System.out.println(in.nextLine());

//        try {
//            Resource r = new Resource("r");
//            Resource rr = new Resource("rr");
//            try {
////                try {
//                    r.use();
//                    rr.use();
////                } finally {
//                }
//            } finally {
//                r.close();
//                rr.close();
//            }
//        } catch (Exception e) {
//            //
//        }
//
//        try {
//        Scanner in = openInput();
//
////        try {
//            if (!in.hasNextInt()) {
//                System.out.println("Expected a");
//                return;
//            }
//            int a = in.nextInt();
//
//            if (!in.hasNextInt()) {
//                System.out.println("Expected b");
//                return;
//            }
//            int b = in.nextInt();
//
//            System.out.println(a + b);
////        } catch (InputMismatchException e) {
//            System.out.println("Invalid input: " + e.getMessage());
//        } catch (NoSuchElementException e) {
//            System.out.println("Expected integer, found EOF");
//        }
//        } catch (FileNotFoundException e) {
//            System.out.println("Input file not found: " +
//                    e.getMessage());
//        }

        /*
        try {
            String s = null;
            System.out.println(s);
            System.out.println(s.length());
            System.out.println(s);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        System.out.println("end");
        */
    }
}