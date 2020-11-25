package net.negacz.cron;

interface Field {
  int MAX_COLUMNS_NUMBER = 14;

  String asFormattedFirst14SpaceSeparatedValues();
}
