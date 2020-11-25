package net.negacz.cron;

import static net.negacz.cron.Parser.parse;
import static net.negacz.cron.Sets.sortedSetOf;

import java.util.SortedSet;
import lombok.EqualsAndHashCode;
import lombok.val;

@EqualsAndHashCode(callSuper = true)
class DayOfWeek extends Field {

  private static final String FIELD_NAME = "day of week  ";

  static final int MIN_VALUE = 0;
  static final int MAX_VALUE = 6;

  private DayOfWeek(SortedSet<Integer> values) {
    super(values);
  }

  static DayOfWeek ofExpression(String expression) {
    try {
      val values = parse(expression, MIN_VALUE, MAX_VALUE);
      return new DayOfWeek(sortedSetOf(values));
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(FIELD_NAME + e.getMessage(), e);
    }
  }

  static DayOfWeek ofValues(int... values) {
    return new DayOfWeek(sortedSetOf(values));
  }

  @Override
  String fieldName() {
    return FIELD_NAME;
  }
}
