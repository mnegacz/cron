package net.negacz.cron;

import static java.util.stream.IntStream.rangeClosed;
import static org.assertj.core.api.Assertions.assertThat;

import lombok.val;
import org.junit.jupiter.api.Test;

class MonthTest {

  @Test
  void parsesSimpleValueExpression() {
    val expression = "1";

    val result = Month.ofExpression(expression);

    assertThat(result).isEqualTo(Month.ofValues(1));
  }

  @Test
  void parsesCommaSeparatedExpression() {
    val expression = "1,12";

    val result = Month.ofExpression(expression);

    assertThat(result).isEqualTo(Month.ofValues(1, 12));
  }

  @Test
  void parsesRangeExpression() {
    val expression = "1-3";

    val result = Month.ofExpression(expression);

    assertThat(result).isEqualTo(Month.ofValues(1, 2, 3));
  }

  @Test
  void parsesAsteriskExpression() {
    val expression = "*";
    val expectedValues = rangeClosed(Month.MIN_VALUE, Month.MAX_VALUE).toArray();

    val result = Month.ofExpression(expression);

    assertThat(result).isEqualTo(Month.ofValues(expectedValues));
  }

  @Test
  void parsesIncrementExpression() {
    val expression = "2/5";

    val result = Month.ofExpression(expression);

    assertThat(result).isEqualTo(Month.ofValues(2, 7, 12));
  }

  @Test
  void parsesAsteriskIncrementExpression() {
    val expression = "*/5";

    val result = Month.ofExpression(expression);

    assertThat(result).isEqualTo(Month.ofValues(1, 6, 11));
  }
}
