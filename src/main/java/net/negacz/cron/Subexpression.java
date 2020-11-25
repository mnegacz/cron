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
  private static final String ASTERISK_INCREMENT = "*/";

  private final String expression;
  private final int minValue;
  private final int maxValue;

  static Subexpression of(String expression, int minValue, int maxValue) {
    return new Subexpression(expression, minValue, maxValue);
  }

  IntStream asIntStream() {
    if (ASTERISK.equals(expression)) {
      return parseAsterisk();
    }
    if (expression.contains(ASTERISK_INCREMENT)) {
      return parseAsteriskIncrement();
    }
    if (expression.contains(INCREMENT)) {
      return parseIncrement();
    }
    if (expression.contains(DASH)) {
      return parseRange();
    }
    return parseSimpleValue();
  }

  private IntStream parseSimpleValue() {
    val value = checkIfValueIsWithinAllowedValues(Integer.parseInt(expression));
    return IntStream.of(value);
  }

  private IntStream parseRange() {
    val range = expression.split(DASH);
    val start = checkIfValueIsWithinAllowedValues(Integer.parseInt(range[0]));
    val end = checkIfValueIsWithinAllowedValues(Integer.parseInt(range[1]));
    return IntStream.rangeClosed(start, end);
  }

  private IntStream parseIncrement() {
    val range = expression.split(INCREMENT);
    val start = checkIfValueIsWithinAllowedValues(Integer.parseInt(range[0]));
    val increment = Integer.parseInt(range[1]);
    return IntStream.iterate(start, n -> n <= maxValue, n -> n + increment);
  }

  private IntStream parseAsteriskIncrement() {
    val range = expression.split(INCREMENT);
    val increment = Integer.parseInt(range[1]);
    return IntStream.iterate(minValue, n -> n <= maxValue, n -> n + increment);
  }

  private IntStream parseAsterisk() {
    return IntStream.rangeClosed(minValue, maxValue);
  }

  private int checkIfValueIsWithinAllowedValues(int value) {
    if (value < minValue || value > maxValue) {
      throw new IllegalArgumentException(expression + " is out of the allowed values");
    }
    return value;
  }
}
