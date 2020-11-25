package net.negacz.cron;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableSortedSet;
import static java.util.stream.Collectors.joining;
import static lombok.AccessLevel.PRIVATE;

import java.util.ArrayList;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
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

  private final Set<Integer> values;

  static Minute ofExpression(String expression) {
    val values = new TreeSet<Integer>();
    val subexpressions = new ArrayList<String>();
    if (expression.contains(COMMA)) {
      subexpressions.addAll(asList(expression.split(COMMA)));
    } else {
      subexpressions.add(expression);
    }
    subexpressions.stream().map(Integer::parseInt).forEach(values::add);
    return ofValues(values);
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
