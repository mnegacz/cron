package net.negacz.cron;

import lombok.val;

class Entrypoint {

  public static void main(String[] args) {
    try {
      if (args.length < 1) {
        throw new IllegalArgumentException("No cron expression is provided");
      }
      val expression = args[0];
      val cron = Cron.ofExpression(expression);
      System.out.println(cron.asFormattedFieldString());
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }
}
