package functional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Ex2 {
  public static Stream<String> getLines(String fn) {
    try {
      return Files.lines(Path.of(fn));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  public static void main(String[] args) {
    try {
      List.of("data.txt", "bx.txt", "c.txt")
          .stream()
          .flatMap(fn -> getLines(fn))
          .forEach(s -> System.out.println(s));
    } catch (RuntimeException ioe) {
      System.out.println("oops that failed: " + ioe.getMessage());
    }
  }
}
