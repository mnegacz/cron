package net.negacz.cron;

import static java.util.stream.Collectors.joining;

import java.util.SortedSet;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
abstract class Field {

  int MAX_COLUMNS_NUMBER = 14;

  private final SortedSet<Integer> values;

  String asFormattedFirst14SpaceSeparatedValues() {
    return values.stream()
        .limit(MAX_COLUMNS_NUMBER)
        .map(Object::toString)
        .collect(joining(" ", fieldName(), ""));
  }

  abstract String fieldName();
}
