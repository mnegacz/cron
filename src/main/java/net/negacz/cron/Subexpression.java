package net.negacz.cron;

import static lombok.AccessLevel.PRIVATE;

import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor(access = PRIVATE)
class Subexpression {

  private static final String DASH = "-";
  private static final String ASTERISK = "*";
  private static final String INCREMENT = "/";

  private final String expression;
  private final int minValue;
  private final int maxValue;

  static Subexpression of(String expression, int minValue, int maxValue) {
    return new Subexpression(expression, minValue, maxValue);
  }

  IntStream asIntStream() {
    if (ASTERISK.equals(expression)) {
      return IntStream.rangeClosed(minValue, maxValue);
    }
    if (expression.contains(INCREMENT)) {
      val range = expression.split(INCREMENT);
      val start = Integer.parseInt(range[0]);
      val increment = Integer.parseInt(range[1]);
      return IntStream.iterate(start, n -> n <= maxValue, n -> n + increment);
    }
    if (expression.contains(DASH)) {
      val range = expression.split(DASH);
      val start = Integer.parseInt(range[0]);
      val end = Integer.parseInt(range[1]);
      return IntStream.rangeClosed(start, end);
    }
    return IntStream.of(Integer.parseInt(expression));
  }
}
