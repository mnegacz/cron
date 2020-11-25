package net.negacz.cron;

import static net.negacz.cron.Parser.parse;
import static net.negacz.cron.Sets.sortedSetOf;

import java.util.SortedSet;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.val;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
class Hour extends Field {

  static final int MIN_VALUE = 0;
  static final int MAX_VALUE = 23;

  private Hour(SortedSet<Integer> values) {
    super(values);
  }

  static Hour ofExpression(String expression) {
    val values = parse(expression, MIN_VALUE, MAX_VALUE);
    return new Hour(sortedSetOf(values));
  }

  static Hour ofValues(int... values) {
    return new Hour(sortedSetOf(values));
  }

  @Override
  String fieldName() {
    return "hour         ";
  }
}
