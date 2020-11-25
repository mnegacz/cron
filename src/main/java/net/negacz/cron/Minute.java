package net.negacz.cron;

import static net.negacz.cron.Parser.parse;
import static net.negacz.cron.Sets.sortedSetOf;

import java.util.SortedSet;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.val;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
class Minute extends Field {

  static final int MIN_VALUE = 0;
  static final int MAX_VALUE = 59;

  private Minute(SortedSet<Integer> values) {
    super(values);
  }

  static Minute ofExpression(String expression) {
    val values = parse(expression, MIN_VALUE, MAX_VALUE);
    return new Minute(sortedSetOf(values));
  }

  static Minute ofValues(int... values) {
    return new Minute(sortedSetOf(values));
  }

  @Override
  String fieldName() {
    return "minute       ";
  }
}
