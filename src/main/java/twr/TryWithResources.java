package twr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TryWithResources {
  public static void main(String[] args) {
    try ( // resource go in here, must "implements AutoCloseable"
          // Java 7 & 8, MUST declare and initialize each resourde
          // as a NEW variable.
        BufferedReader br = Files.newBufferedReader(Path.of("data.txt"));
        BufferedWriter out = Files.newBufferedWriter(Path.of("out.txt"), StandardOpenOption.APPEND);
    ) {
      // do Stuff...
    } catch (IOException ioe) {
      System.out.println("Bad things happen :) ");
    } // Try with resources guarantees to attempt to close all "resources" (in implicit finally
    //  which is generated by the compiler)
    // any explicit finally block runs AFTER (in addition to) the implicit block.

    // In the implicit finally:
    // - preserve any "live" exception
    // - attempt all close operations
    // - if exceptions arise during close, preseve them too.
    // - if there was a live exception, attach any closure exception to it
    //   "suppressed" exception, and rethrow the original.
  }
}