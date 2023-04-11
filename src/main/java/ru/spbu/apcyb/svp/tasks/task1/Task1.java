package ru.spbu.apcyb.svp.tasks.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Задание 1.
 */
public class Task1 {

  private List<String> combinations;
  private final Logger logger;

  public Task1() {
    this.combinations = new ArrayList<>();
    this.logger = Logger.getLogger(Task1.class.getName());
  }

  public void setCombinations(List<String> combinations) {
    this.combinations = combinations;
  }

  public List<String> getCombinations() {
    return combinations;
  }

  /**
   * method to parse the input into 2 fields: sum and the list of banknotes.
   *
   * @param input String from input
   *
   * @return structure with sum and list of available banknotes
   */
  public static BanknoteSet parseInput(String input) {
    long sum = Long.parseLong(input.split(" ")[0]);
    if (sum <= 0) {
      throw new InputMismatchException("Sum is not a positive number");
    }
    input = input.contains(" ") ? input.substring(input.indexOf(" ") + 1) : "";
    long[] banknotes;
    try {
      banknotes = Arrays.stream(input.split(" ")).mapToLong(Long::parseLong).toArray();
    } catch (NumberFormatException exception) {
      throw new NumberFormatException("Incorrect format");
    }

    var set = new HashSet<Long>(banknotes.length);
    for (var banknote : banknotes) {
      if (banknote <= sum) {
        if (banknote > 0) {
          set.add(banknote);
        } else {
          throw new InputMismatchException("Provided a non-positive banknote value");
        }
      }
    }

    var list = new ArrayList<>(set);
    list.sort(Comparator.naturalOrder());
    return new BanknoteSet(sum, list);
  }

  /**
   * adds all the combinations into 'combinations' variable.
   *
   * @param bs BanknoteSet object of the task
   * @param currentSum sum that is currently being split
   * @param previousBanknote value of banknote that was deducted from the
   *                         currentSum on the previous step of recursion
   * @param currentCombination string of combination
   */
  public void countCombinations(
      BanknoteSet bs,
      long currentSum,
      long previousBanknote,
      String currentCombination) {
    if (currentSum == 0) {
      currentCombination = currentCombination.substring(0, currentCombination.lastIndexOf(" "));
      combinations.add(currentCombination);
    }
    if (currentSum < 0) {
      return;
    }

    for (var currentBanknote : bs.getBanknotes()) {
      if (currentBanknote >= previousBanknote) {
        countCombinations(
            bs,
            currentSum - currentBanknote,
            currentBanknote,
            currentCombination + currentBanknote + " ");
      }
    }
  }

  /**
   * main method.
   *
   * @param args args
   *
   */
  public static void main(String[] args) {
    var sc = new Scanner(System.in);
    var input = sc.nextLine();
    var bs = parseInput(input);

    var task = new Task1();
    task.countCombinations(bs, bs.getSum(), bs.getBanknotes().get(0), "");

    task.logger.log(Level.INFO, () -> "sum: " + bs.getSum());
    task.logger.log(Level.INFO, () -> "banknotes: " + bs.getBanknotes().toString());

    task.logger.log(Level.INFO, () -> "number of combinations: " + task.combinations.size());
    task.logger.log(Level.INFO, () -> "combinations: " + task.combinations.toString());
  }
}
