package net.negacz.cron;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableSortedSet;
import static java.util.stream.Collectors.joining;
import static lombok.AccessLevel.PRIVATE;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.IntStream;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

@RequiredArgsConstructor(access = PRIVATE)
@EqualsAndHashCode
@ToString
class Minute {

  private static final int MAX_COLUMNS_NUMBER = 14;
  private static final String COMMA = ",";
  private static final String DASH = "-";

  private final Set<Integer> values;

  static Minute ofExpression(String expression) {
    val values = new TreeSet<Integer>();
    val subexpressions = splitByComma(expression);
    subexpressions.stream().flatMapToInt(Minute::parse).forEach(values::add);
    return ofValues(values);
  }

  private static IntStream parse(String expression) {
    if (expression.contains(DASH)) {
      val range = expression.split(DASH);
      val start = Integer.parseInt(range[0]);
      val end = Integer.parseInt(range[1]);
      return IntStream.rangeClosed(start, end);
    }
    return IntStream.of(Integer.parseInt(expression));
  }

  private static List<String> splitByComma(String expression) {
    if (expression.contains(COMMA)) {
      return asList(expression.split(COMMA));
    }
    return singletonList(expression);
  }

  static Minute ofValues(Integer... values) {
    val copy = new TreeSet<>(asList(values));
    return ofValues(copy);
  }

  static Minute ofValues(SortedSet<Integer> values) {
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
