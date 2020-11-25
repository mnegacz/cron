package net.negacz.cron;

import static java.util.stream.Collectors.joining;
import static lombok.AccessLevel.PRIVATE;

import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor(access = PRIVATE)
class Cron {

  private static final String ROW_SEPARATOR = "\n";

  static final String FIELD_SEPARATOR = " ";

  private final Minute minute;
  private final Hour hour;
  private final DayOfMonth dayOfMonth;

  static Cron ofExpression(String expression) {
    val fields = expression.split(FIELD_SEPARATOR);
    if (fields.length < 6) {
      throw new IllegalArgumentException(
          "Cron expression doesn't contain required fields: (minute) (hour) (day of month) (month) (day of week) (command)");
    }
    val minute = Minute.ofExpression(fields[0]);
    val hour = Hour.ofExpression(fields[1]);
    val dayOfMonth = DayOfMonth.ofExpression(fields[2]);
    return new Cron(minute, hour, dayOfMonth);
  }

  String asFormattedFieldString() {
    return Stream.of(minute, hour, dayOfMonth)
        .map(Field::asFormattedFirst14SpaceSeparatedValues)
        .collect(joining(ROW_SEPARATOR));
  }
}
