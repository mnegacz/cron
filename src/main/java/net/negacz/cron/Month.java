package net.negacz.cron;

import static net.negacz.cron.Parser.parse;
import static net.negacz.cron.Sets.sortedSetOf;

import java.util.SortedSet;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.val;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
class Month extends Field {

  private static final String FIELD_NAME = "month        ";

  static final int MIN_VALUE = 1;
  static final int MAX_VALUE = 12;

  private Month(SortedSet<Integer> values) {
    super(values);
  }

  static Month ofExpression(String expression) {
    try {
      val values = parse(expression, MIN_VALUE, MAX_VALUE);
      return new Month(sortedSetOf(values));
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(FIELD_NAME + e.getMessage(), e);
    }
  }

  static Month ofValues(int... values) {
    return new Month(sortedSetOf(values));
  }

  @Override
  String fieldName() {
    return FIELD_NAME;
  }
}
