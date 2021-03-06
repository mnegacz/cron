package net.negacz.cron;

import static java.util.stream.Collectors.joining;
import static lombok.AccessLevel.PRIVATE;

import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor(access = PRIVATE)
class Cron {

  private static final int NUMBER_OF_FIELDS = 6;
  private static final String ROW_SEPARATOR = "\n";

  static final String FIELD_SEPARATOR = " ";

  private final Minute minute;
  private final Hour hour;
  private final DayOfMonth dayOfMonth;
  private final Month month;
  private final DayOfWeek dayOfWeek;
  private final Command command;

  static Cron ofExpression(String expression) {
    val fields = expression.split(FIELD_SEPARATOR, NUMBER_OF_FIELDS);
    if (fields.length < NUMBER_OF_FIELDS) {
      throw new IllegalArgumentException(
          "Cron expression doesn't contain required fields: (minute) (hour) (day of month) (month) (day of week) (command)");
    }
    val minute = Minute.ofExpression(fields[0]);
    val hour = Hour.ofExpression(fields[1]);
    val dayOfMonth = DayOfMonth.ofExpression(fields[2]);
    val month = Month.ofExpression(fields[3]);
    val dayOfWeek = DayOfWeek.ofExpression(fields[4]);
    val command = Command.of(fields[5]);
    return new Cron(minute, hour, dayOfMonth, month, dayOfWeek, command);
  }

  String asFormattedFieldString() {
    return Stream.of(minute, hour, dayOfMonth, month, dayOfWeek, command)
        .map(Field::asFormattedFirst14SpaceSeparatedValues)
        .collect(joining(ROW_SEPARATOR));
  }
}
