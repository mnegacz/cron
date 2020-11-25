package net.negacz.cron;

import static net.negacz.cron.Parser.parse;
import static net.negacz.cron.Sets.sortedSetOf;

import java.util.SortedSet;
import lombok.EqualsAndHashCode;
import lombok.val;

@EqualsAndHashCode(callSuper = true)
class DayOfMonth extends Field {

  static final int MIN_VALUE = 1;
  static final int MAX_VALUE = 31;

  private DayOfMonth(SortedSet<Integer> values) {
    super(values);
  }

  static DayOfMonth ofExpression(String expression) {
    val values = parse(expression, MIN_VALUE, MAX_VALUE);
    return new DayOfMonth(sortedSetOf(values));
  }

  static DayOfMonth ofValues(int... values) {
    return new DayOfMonth(sortedSetOf(values));
  }

  @Override
  String fieldName() {
    return "day of month ";
  }
}
