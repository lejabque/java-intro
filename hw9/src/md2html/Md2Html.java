package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Md2Html {
    public static void main(String[] args) {
//        String inFile = "/home/lejabque/Desktop/Study/term1/hw/prog-intro-hw/hw9/src/md2html/md.in";
//        String outFile = "/home/lejabque/Desktop/Study/term1/hw/prog-intro-hw/hw9/src/md2html/md.out";
        String inFile = args[0];
        String outFile = args[1];
        ParagraphConverter converter = new ParagraphConverter();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(inFile)), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(new File(outFile))) {
            String line = in.readLine();
            while (line != null) {
                StringBuilder paragraph = new StringBuilder();
                while (line.isEmpty()) {
                    line = in.readLine();
                }
                while (line != null && !line.isEmpty()) {
                    if (paragraph.length() != 0) {
                        paragraph.append('\n');
                    }
                    paragraph.append(line);
                    line = in.readLine();
                }
                if (paragraph.length() > 0) {
                    StringBuilder resParagraph = new StringBuilder();
                    converter.convert(paragraph.toString(), resParagraph);
                    out.println(resParagraph.toString());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
            return;
        }
    }
}