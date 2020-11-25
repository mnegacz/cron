package net.negacz.cron;

import static java.util.stream.IntStream.rangeClosed;
import static org.assertj.core.api.Assertions.assertThat;

import lombok.val;
import org.junit.jupiter.api.Test;

class MinuteTest {

  @Test
  void parsesSimpleValueExpression() {
    val expression = "1";

    val result = Minute.ofExpression(expression);

    assertThat(result).isEqualTo(Minute.ofValues(1));
  }

  @Test
  void parsesCommaSeparatedExpression() {
    val expression = "1,15";

    val result = Minute.ofExpression(expression);

    assertThat(result).isEqualTo(Minute.ofValues(1, 15));
  }

  @Test
  void parsesRangeExpression() {
    val expression = "1-3";

    val result = Minute.ofExpression(expression);

    assertThat(result).isEqualTo(Minute.ofValues(1, 2, 3));
  }

  @Test
  void parsesAsteriskExpression() {
    val expression = "*";
    val expectedValues = rangeClosed(Minute.MIN_VALUE, Minute.MAX_VALUE).toArray();

    val result = Minute.ofExpression(expression);

    assertThat(result).isEqualTo(Minute.ofValues(expectedValues));
  }

  @Test
  void parsesIncrementExpression() {
    val expression = "1/15";

    val result = Minute.ofExpression(expression);

    assertThat(result).isEqualTo(Minute.ofValues(1, 16, 31, 46));
  }

  @Test
  void parsesAsteriskIncrementExpression() {
    val expression = "*/15";

    val result = Minute.ofExpression(expression);

    assertThat(result).isEqualTo(Minute.ofValues(0, 15, 30, 45));
  }
}
