package net.negacz.cron;

import static java.util.stream.IntStream.rangeClosed;
import static org.assertj.core.api.Assertions.assertThat;

import lombok.val;
import org.junit.jupiter.api.Test;

class HourTest {

  @Test
  void parsesSimpleValueExpression() {
    val expression = "1";

    val result = Hour.ofExpression(expression);

    assertThat(result).isEqualTo(Hour.ofValues(1));
  }

  @Test
  void parsesCommaSeparatedExpression() {
    val expression = "1,15";

    val result = Hour.ofExpression(expression);

    assertThat(result).isEqualTo(Hour.ofValues(1, 15));
  }

  @Test
  void parsesRangeExpression() {
    val expression = "1-3";

    val result = Hour.ofExpression(expression);

    assertThat(result).isEqualTo(Hour.ofValues(1, 2, 3));
  }

  @Test
  void parsesAsteriskExpression() {
    val expression = "*";
    val expectedValues = rangeClosed(Hour.MIN_VALUE, Hour.MAX_VALUE).toArray();

    val result = Hour.ofExpression(expression);

    assertThat(result).isEqualTo(Hour.ofValues(expectedValues));
  }

  @Test
  void parsesIncrementExpression() {
    val expression = "1/5";

    val result = Hour.ofExpression(expression);

    assertThat(result).isEqualTo(Hour.ofValues(1, 6, 11, 16, 21));
  }

  @Test
  void parsesAsteriskIncrementExpression() {
    val expression = "*/5";

    val result = Hour.ofExpression(expression);

    assertThat(result).isEqualTo(Hour.ofValues(0, 5, 10, 15, 20));
  }
}
