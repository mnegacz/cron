package net.negacz.cron;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static java.util.stream.Collectors.joining;

@RequiredArgsConstructor
@EqualsAndHashCode
abstract class Field {

  int MAX_COLUMNS_NUMBER = 14;

  private final Set<Integer> values;

  String asFormattedFirst14SpaceSeparatedValues() {
    return values.stream()
      .limit(MAX_COLUMNS_NUMBER)
      .map(Object::toString)
      .collect(joining(" ", fieldName(), ""));
  }

  abstract String fieldName();
}
