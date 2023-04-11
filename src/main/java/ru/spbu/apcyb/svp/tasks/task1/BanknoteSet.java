package ru.spbu.apcyb.svp.tasks.task1;

import java.util.List;
import java.util.Objects;

/**
 * вспомогательный класс: содержит сумму и варианты банкнот для размена.
 */
public class BanknoteSet {
  private final long sum;
  private final List<Long> banknotes;

  public long getSum() {
    return sum;
  }

  public List<Long> getBanknotes() {
    return banknotes;
  }

  public BanknoteSet(long sum, List<Long> banknotes) {
    this.sum = sum;
    this.banknotes = banknotes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BanknoteSet that = (BanknoteSet) o;
    return sum == that.sum && Objects.equals(banknotes, that.banknotes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sum, banknotes);
  }
}
