package twr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TryWithResourcesJ9 {
  public static void main(String[] args) throws IOException {
    /*final */BufferedReader br = Files.newBufferedReader(Path.of("data.txt"));
//    br = null;
    try (
        br;
        BufferedWriter out = Files.newBufferedWriter(Path.of("out.txt"), StandardOpenOption.APPEND);
    ) {
      // do Stuff...
    } catch (IOException ioe) {
      System.out.println("Bad things happen :) ");
    }
  }
}
