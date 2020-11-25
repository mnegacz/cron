package net.negacz.cron;

import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
class Expression {

  private static final String COMMA = ",";

  private final String expression;
  private final int minValue;
  private final int maxValue;

  static Expression of(String expression, int minValue, int maxValue) {
    return new Expression(expression, minValue, maxValue);
  }

  Stream<Subexpression> split() {
    if (expression.contains(COMMA)) {
      return Stream.of(expression.split(COMMA))
        .map(subexpression -> Subexpression.of(subexpression, minValue, maxValue));
    }
    return Stream.of(Subexpression.of(expression, minValue, maxValue));
  }
}
