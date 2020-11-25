package net.negacz.cron;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.val;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

import static java.util.Collections.unmodifiableSortedSet;
import static java.util.stream.Collectors.toUnmodifiableList;

@EqualsAndHashCode(callSuper = true)
@ToString
class Hour extends Field {

  static final int MIN_VALUE = 0;
  static final int MAX_VALUE = 23;

  private Hour(Set<Integer> values) {
    super(values);
  }

  static Hour ofExpression(String expression) {
    val values = new TreeSet<Integer>();
    Expression.of(expression, MIN_VALUE, MAX_VALUE)
      .split()
      .flatMapToInt(Subexpression::asIntStream)
      .forEach(values::add);
    return ofValues(values);
  }

  static Hour ofValues(int... values) {
    val copy = IntStream.of(values).boxed().collect(toUnmodifiableList());
    return ofValues(copy);
  }

  static Hour ofValues(Collection<Integer> values) {
    val copy = new TreeSet<>(values);
    val unmodifiableCopy = unmodifiableSortedSet(copy);
    return new Hour(unmodifiableCopy);
  }

  @Override
  String fieldName() {
    return "hour   ";
  }
}
