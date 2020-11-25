package net.negacz.cron;

import static java.util.Collections.unmodifiableSortedSet;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toUnmodifiableList;
import static lombok.AccessLevel.PRIVATE;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

@RequiredArgsConstructor(access = PRIVATE)
@EqualsAndHashCode
@ToString
class Minute {

  static final int MIN_VALUE = 0;
  static final int MAX_VALUE = 59;
  private static final int MAX_COLUMNS_NUMBER = 14;
  private static final String COMMA = ",";

  private final Set<Integer> values;

  static Minute ofExpression(String expression) {
    val values = new TreeSet<Integer>();
    splitByComma(expression).flatMapToInt(Subexpression::asIntStream).forEach(values::add);
    return ofValues(values);
  }

  private static Stream<Subexpression> splitByComma(String expression) {
    if (expression.contains(COMMA)) {
      return Stream.of(expression.split(COMMA))
          .map(subexpression -> Subexpression.of(subexpression, MIN_VALUE, MAX_VALUE));
    }
    return Stream.of(Subexpression.of(expression, MIN_VALUE, MAX_VALUE));
  }

  static Minute ofValues(int... values) {
    val copy = IntStream.of(values).boxed().collect(toUnmodifiableList());
    return ofValues(copy);
  }

  static Minute ofValues(Collection<Integer> values) {
    val copy = new TreeSet<>(values);
    val unmodifiableCopy = unmodifiableSortedSet(copy);
    return new Minute(unmodifiableCopy);
  }

  String asFormattedFirst14SpaceSeparatedValues() {
    return values.stream()
        .limit(MAX_COLUMNS_NUMBER)
        .map(Object::toString)
        .collect(joining(" ", "minute ", ""));
  }
}
