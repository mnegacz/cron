package net.negacz.cron;

import static java.util.Collections.unmodifiableSortedSet;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.IntStream;
import lombok.val;

class Sets {

  static SortedSet<Integer> sortedSetOf(int... values) {
    val collection = IntStream.of(values).boxed().collect(toUnmodifiableList());
    return sortedSetOf(collection);
  }

  static SortedSet<Integer> sortedSetOf(Collection<Integer> values) {
    return unmodifiableSortedSet(new TreeSet<>(values));
  }
}
