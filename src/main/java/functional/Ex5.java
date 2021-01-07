package functional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

// VAVr -- function library for Java (has things like Either)
// Scala
class Either<L, R> {
  private L left;
  private R right;
  private Either(L left, R right) {
    this.left = left;
    this.right = right;
  }
  public static <L, R> Either<L, R> success(R right) {
    return new Either<>(null, right);
  }
  public static <L, R> Either<L, R> failure(L left) {
    return new Either<>(left, null);
  }
  public void ifFailure(Consumer<L> op) {
    if (left != null) {
      op.accept(left);
    }
  }
  public boolean isSuccess() {
    return left == null;
  }
  public R getSuccess() {
    if (left != null) {
      throw new IllegalStateException("attempt to access success value from a failed Either");
    }
    return right;
  }
  public Either<L,R> retry(Function<L, Either<L, R>> op) {
    if (left == null) return this;
    return op.apply(left);
  }
}
public class Ex5 {

  public static <A, R> Function<A, Either<Throwable, R>> wrap(ExFunction<A, R> op) {
    return a -> {
      try {
        return Either.success(op.apply(a));
      } catch (Throwable throwable) {
        return Either.failure(throwable);
      }
    };
  }

  public static void main(String[] args) {
      List.of("data.txt", "b.txt", "c.txt")
          .stream()
          .map(wrap(fn -> Files.lines(Path.of(fn))))
          .peek(e -> e.ifFailure(t -> System.out.println("Failed with message " + t.getMessage())))
          .map(e -> e.retry(wrap(l -> Files.lines(Path.of("fallback.txt")))))
          .peek(e -> e.ifFailure(t -> System.out.println("Failed again with message " + t.getMessage())))
          .filter(e -> e.isSuccess())
          .flatMap(e -> e.getSuccess())
          .forEach(s -> System.out.println(s));
  }
}
