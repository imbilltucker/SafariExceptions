package functional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ex1 {
  public static void main(String[] args) {
    try {
      Files.lines(Path.of("datax.txt"))
          .forEach(s -> System.out.println(s));
    } catch (IOException ioe) {
      System.out.println("That didn't work");
    }
  }
}
