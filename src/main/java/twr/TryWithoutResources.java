package twr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;

public class TryWithoutResources {
  public static void main(String[] args) throws SQLException {
    Throwable savedException = null; // can we be more specific (please!)
    BufferedReader br = null;
    BufferedWriter out = null;
    try {
//    BufferedReader br = Files.newBufferedReader(Paths.get("data.txt"));
//      BufferedReader br = Files.newBufferedReader(Path.of("data.txt"));
      br = Files.newBufferedReader(Path.of("data.txt"));
      out = Files.newBufferedWriter(Path.of("out.txt"), StandardOpenOption.APPEND);
      if (Math.random() > 0.5) throw new SQLException();
    } catch (IOException ioe) {
      System.out.println("It broke!");
    } catch (Throwable t) {
      savedException = t;
    }
    finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          // preserve??
          e.printStackTrace();
        }
      }
      if (out != null) {
        try {
          out.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (savedException != null) {
        // attach suppressed?
//        throw savedException;
      }
    }
  }
}
