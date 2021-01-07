package functional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@FunctionalInterface
interface ExFunction<A, R> {
  R apply(A a) throws Throwable;
}

public class Ex4 {

  public static <A, R> Function<A, Optional<R>> wrap(ExFunction<A, R> op) {
    return a -> {
      try {
        return Optional.of(op.apply(a));
      } catch (Throwable throwable) {
        return Optional.empty();
      }
    };
  }

  public static void main(String[] args) {
      List.of("data.txt", "bx.txt", "c.txt")
          .stream()
          .map(wrap(fn -> Files.lines(Path.of(fn))))
          .peek(opt -> {
            if (opt.isEmpty()) {
              System.out.println("Something didn't work");
            }
          })
          .filter(opt -> opt.isPresent())
          .flatMap(opt -> opt.get())
          .forEach(s -> System.out.println(s));
  }
}
