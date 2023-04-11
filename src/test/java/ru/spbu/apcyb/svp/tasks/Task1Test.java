package ru.spbu.apcyb.svp.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import ru.spbu.apcyb.svp.tasks.task1.BanknoteSet;
import ru.spbu.apcyb.svp.tasks.task1.Task1;

/**
 * Тесты для задания 1.
 */
@TestInstance(Lifecycle.PER_CLASS)
class Task1Test {
  private Task1 task;

  @BeforeAll
  void taskInit() {
    task = new Task1();
  }

  @Test
  void task1Test1() {
    var bs1 = new BanknoteSet(10, Arrays.asList(2L, 3L));
    var bs2 = new BanknoteSet(10, Arrays.asList(2L, 3L));
    var bs3 = new BanknoteSet(10, Arrays.asList(3L, 4L));
    Assertions.assertEquals(bs1, bs2);
    Assertions.assertNotEquals(bs2, bs3);
  }

  @Test
  void task1Test2() {
    task.setCombinations(new ArrayList<>());
    task.countCombinations(new BanknoteSet(5, Arrays.asList(2L, 3L)), 5, 2L, "");
    Assertions.assertEquals(1, task.getCombinations().size());
  }

  @Test
  void task1Test3() {
    task.setCombinations(new ArrayList<>());
    task.countCombinations(new BanknoteSet(4, Arrays.asList(2L, 1L)), 4, 1L, "");
    Assertions.assertEquals(3, task.getCombinations().size());
  }

  @Test
  void task1Test4() {
    task.setCombinations(new ArrayList<>());
    task.countCombinations(new BanknoteSet(1000, List.of(1L)), 1000, 1L, "");
    Assertions.assertEquals(1, task.getCombinations().size());
  }

  @Test
  void task1Test5() {
    String input = "5 2 3";
    BanknoteSet expected = new BanknoteSet(5, Arrays.asList(2L, 3L));
    Assertions.assertEquals(expected, Task1.parseInput(input));
  }

  @Test
  void task1Test6() {
    String input = "-5 2 3";
    InputMismatchException e = Assertions.assertThrows(
        InputMismatchException.class, () -> Task1.parseInput(input));
    Assertions.assertEquals("Sum is not a positive number", e.getMessage());
  }

  @Test
  void task1Test7() {
    String input = "5 -2 3";
    InputMismatchException e = Assertions.assertThrows(
        InputMismatchException.class, () -> Task1.parseInput(input));
    Assertions.assertEquals("Provided a non-positive banknote value", e.getMessage());
  }

  @Test
  void task1Test8() {
    String input = "5 j 3";
    NumberFormatException e = Assertions.assertThrows(
        NumberFormatException.class, () -> Task1.parseInput(input));
    Assertions.assertEquals("Incorrect format", e.getMessage());
  }

  @Test
  void task1Test9() {
    var bs = new BanknoteSet(10, List.of(1L));
    Assertions.assertEquals(10, bs.getSum());
  }
}
