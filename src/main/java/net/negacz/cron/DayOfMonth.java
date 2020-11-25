package net.negacz.cron;

import static java.util.Collections.unmodifiableSortedSet;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;
import lombok.EqualsAndHashCode;
import lombok.val;

@EqualsAndHashCode(callSuper = true)
class DayOfMonth extends Field {

  static final int MIN_VALUE = 1;
  static final int MAX_VALUE = 31;

  private DayOfMonth(Set<Integer> values) {
    super(values);
  }

  static DayOfMonth ofExpression(String expression) {
    val values = new TreeSet<Integer>();
    Expression.of(expression, MIN_VALUE, MAX_VALUE)
        .split()
        .flatMapToInt(Subexpression::asIntStream)
        .forEach(values::add);
    return ofValues(values);
  }

  static DayOfMonth ofValues(int... values) {
    val copy = IntStream.of(values).boxed().collect(toUnmodifiableList());
    return ofValues(copy);
  }

  static DayOfMonth ofValues(Collection<Integer> values) {
    val copy = new TreeSet<>(values);
    val unmodifiableCopy = unmodifiableSortedSet(copy);
    return new DayOfMonth(unmodifiableCopy);
  }

  @Override
  String fieldName() {
    return "day of month ";
  }
}
