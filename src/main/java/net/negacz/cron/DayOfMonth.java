package net.negacz.cron;

import static net.negacz.cron.Parser.parse;
import static net.negacz.cron.Sets.sortedSetOf;

import java.util.SortedSet;
import lombok.EqualsAndHashCode;
import lombok.val;

@EqualsAndHashCode(callSuper = true)
class DayOfMonth extends Field {

  private static final String FIELD_NAME = "day of month ";

  static final int MIN_VALUE = 1;
  static final int MAX_VALUE = 31;

  private DayOfMonth(SortedSet<Integer> values) {
    super(values);
  }

  static DayOfMonth ofExpression(String expression) {
    try {
      val values = parse(expression, MIN_VALUE, MAX_VALUE);
      return new DayOfMonth(sortedSetOf(values));
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(FIELD_NAME + e.getMessage(), e);
    }
  }

  static DayOfMonth ofValues(int... values) {
    return new DayOfMonth(sortedSetOf(values));
  }

  @Override
  String fieldName() {
    return FIELD_NAME;
  }
}
