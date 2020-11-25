package net.negacz.cron;

import java.util.Set;
import java.util.TreeSet;
import lombok.val;

class Parser {

  static Set<Integer> parse(String expression, int minValue, int maxValue) {
    val values = new TreeSet<Integer>();
    Expression.of(expression, minValue, maxValue)
        .split()
        .flatMapToInt(Subexpression::asIntStream)
        .forEach(values::add);
    return values;
  }
}
