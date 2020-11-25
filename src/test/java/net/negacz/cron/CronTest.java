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

    assertThat(result).contains("minute 1 2 3 4");
  }

  @Test
  void formatsHourField() {
    val cron = Cron.ofExpression("1 5,6,7-8 1,15 * 1-5 /usr/bin/find");

    val result = cron.asFormattedFieldString();

    assertThat(result).contains("hour   5 6 7 8");
  }
}
