package net.negacz.cron;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import lombok.val;
import org.junit.jupiter.api.Test;

class CronTest {

  @Test
  void throwsIllegalArgumentExceptionWhenExpressionContainsLessThanSixRequiredFields() {
    val expression = "*/15 0 1,15 * 1-5";

    val result = catchThrowable(() -> Cron.ofExpression(expression));

    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void formatsMinuteField() {
    val cron = Cron.ofExpression("1,2,3-4 0 1,15 * 1-5 /usr/bin/find");

    val result = cron.asFormattedFieldString();

    assertThat(result).contains("minute       1 2 3 4");
  }

  @Test
  void formatsHourField() {
    val cron = Cron.ofExpression("0 1,2,3-4 1,15 * 1-5 /usr/bin/find");

    val result = cron.asFormattedFieldString();

    assertThat(result).contains("hour         1 2 3 4");
  }

  @Test
  void formatsDayOfMonthField() {
    val cron = Cron.ofExpression("0 0 1,2,3-4 * 1-5 /usr/bin/find");

    val result = cron.asFormattedFieldString();

    assertThat(result).contains("day of month 1 2 3 4");
  }

  @Test
  void formatsMonthField() {
    val cron = Cron.ofExpression("0 0 1 1,2,3-4 1-5 /usr/bin/find");

    val result = cron.asFormattedFieldString();

    assertThat(result).contains("month        1 2 3 4");
  }

  @Test
  void formatsDayOfWeekField() {
    val cron = Cron.ofExpression("0 0 1 0 1,2,3-4 /usr/bin/find");

    val result = cron.asFormattedFieldString();

    assertThat(result).contains("day of week  1 2 3 4");
  }
}
