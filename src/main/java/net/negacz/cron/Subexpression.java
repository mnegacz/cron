package net.negacz.cron;

import static lombok.AccessLevel.PRIVATE;

import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor(access = PRIVATE)
class Subexpression {

  private static final String DASH = "-";

  private final String value;

  static Subexpression of(String value) {
    return new Subexpression(value);
  }

  IntStream asIntStream() {
    if (value.contains(DASH)) {
      val range = value.split(DASH);
      val start = Integer.parseInt(range[0]);
      val end = Integer.parseInt(range[1]);
      return IntStream.rangeClosed(start, end);
    }
    return IntStream.of(Integer.parseInt(value));
  }
}
