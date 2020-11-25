package net.negacz.cron;

import static net.negacz.cron.Sets.sortedSetOf;

import java.util.SortedSet;

class Command extends Field {

  private Command(SortedSet<String> values) {
    super(values);
  }

  static Command of(String value) {
    return new Command(sortedSetOf(value));
  }

  @Override
  String fieldName() {
    return "command      ";
  }
}
