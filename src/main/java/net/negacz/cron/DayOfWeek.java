package net.negacz.cron;

import static net.negacz.cron.Parser.parse;
import static net.negacz.cron.Sets.sortedSetOf;

import java.util.SortedSet;
import lombok.EqualsAndHashCode;
import lombok.val;

@EqualsAndHashCode(callSuper = true)
class DayOfWeek extends Field {

  static final int MIN_VALUE = 0;
  static final int MAX_VALUE = 6;

  private DayOfWeek(SortedSet<Integer> values) {
    super(values);
  }

  static DayOfWeek ofExpression(String expression) {
    val values = parse(expression, MIN_VALUE, MAX_VALUE);
    return new DayOfWeek(sortedSetOf(values));
  }

  static DayOfWeek ofValues(int... values) {
    return new DayOfWeek(sortedSetOf(values));
  }

  @Override
  String fieldName() {
    return "day of week  ";
  }
}
