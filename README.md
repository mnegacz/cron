# Cron Expression Parser

## Prerequisites
* Java Development Kit 11 or higher

## Building

```
$ ./gradlew clean build
```

## Running

```
$ java -jar build/libs/cron-0.0.1.jar  "*/15 0 1,15 * 1-5 /usr/bin/find"
```

## Assumptions
* The `?` character, which in some implementations denotes `no specific value` is not supported.
* No semantic checks are done so e.g. non-existing February, the 30th - `0 0 31 2 * /usr/bin/find` is a valid cron expression.

## Missing parts
* `JAN–DEC` and `SUN–SAT` values for `month` and `day of week` fields.
* Thorough input validation.