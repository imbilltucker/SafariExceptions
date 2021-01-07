package functional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Ex3 {
  public static Stream<String> getLines(String fn) {
    try {
      return Files.lines(Path.of(fn));
    } catch (IOException e) {
      System.out.println("Problem: " + e.getMessage());
      return Stream.empty();
    }
  }

  public static void main(String[] args) {
      List.of("data.txt", "bx.txt", "c.txt")
          .stream()
          .flatMap(fn -> getLines(fn))
          .forEach(s -> System.out.println(s));
  }
}
