package ru.spbu.apcyb.svp.tasks;

import java.util.LinkedList;
import java.util.function.DoubleBinaryOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import ru.spbu.apcyb.svp.tasks.task2.DoublyLinkedList;
import ru.spbu.apcyb.svp.tasks.task2.ListNode;
import ru.spbu.apcyb.svp.tasks.task2.MyQueue;

/**
 * Тесты для задания 2.
 */
@TestInstance(Lifecycle.PER_CLASS)
class Task2Test {
  private ListNode<Integer> previousNode;
  private ListNode<Integer> nextNode;

  DoublyLinkedList<Integer> listInit() {
    var list = new DoublyLinkedList<Integer>();
    list.add(5);
    list.add(0, 3);
    return list;
  }

  ListNode nodesInit() {
    previousNode = new ListNode<>(1);
    var currentNode = new ListNode<>(2);
    nextNode = new ListNode<>(3);
    currentNode.setNext(nextNode);
    currentNode.setPrevious(previousNode);
    return currentNode;
  }

  private MyQueue<Integer> queueInit() {
    var queue = new MyQueue<Integer>(new DoublyLinkedList<>());
    return queue;
  }

  @Test
  void task2Test1() {
    var currentNode = nodesInit();
    Assertions.assertEquals(2, currentNode.getData());
    currentNode.setData(4);
    Assertions.assertEquals(4, currentNode.getData());
    Assertions.assertEquals(nextNode, currentNode.getNext());
    Assertions.assertEquals(previousNode, currentNode.getPrevious());
  }

  @Test
  void task2Test2() {
    var list = listInit();
    Assertions.assertEquals(3, list.getHead().getData());
    Assertions.assertEquals(5, list.getTail().getData());
  }

  @Test
  void task2Test3() {
    var list = listInit();
    Assertions.assertEquals(false, list.add(5));
  }

  @Test
  void task2Test4() {
    var list = listInit();
    Assertions.assertEquals(true, list.contains(3));
  }

  @Test
  void task2Test5() {
    var list = listInit();
    Assertions.assertEquals(3, list.get(0));
  }

  @Test
  void task2Test6() {
    var list = listInit();
    Assertions.assertEquals(false, list.isEmpty());
    list.remove(0);
    list.remove(0);
    Assertions.assertEquals(true, list.isEmpty());
  }

  @Test
  void task2Test7() {
    var queue = queueInit();
    Assertions.assertEquals(true, queue.isEmpty());
  }

  @Test
  void task2Test8() {
    var queue = queueInit();
    queue.add(2);
    queue.add(3);
    Assertions.assertEquals(2, queue.peek());
  }

  @Test
  void task2Test9() {
    var list = new DoublyLinkedList<Integer>();
    Assertions.assertNull(list.getTail());
    Assertions.assertNull(list.getHead());
  }

  @Test
  void task2Test10() {
    var list = new DoublyLinkedList<>();
    list.add(0, 3);
    Assertions.assertNull(list.getHead().getNext());
    Assertions.assertNull(list.getHead().getPrevious());
    Assertions.assertNull(list.getTail().getNext());
    Assertions.assertNull(list.getTail().getPrevious());
  }

  @Test
  void task2Test11() {
    var list = new DoublyLinkedList<>();
    Assertions.assertEquals(false, list.equals(null));
    Assertions.assertEquals(false, list.equals(5));
    var list2 = new DoublyLinkedList<>();
    list.add(3);
    list2.add(3);
    Assertions.assertEquals(list, list2);
  }

  @Test
  void task2Test12() {
    var list = listInit();
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.remove(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.iterator());
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.size());
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.forEach(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.toArray());
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.clear());
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.toArray(new Object[]{}));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.removeIf(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.replaceAll(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.sort(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.retainAll(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.removeAll(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.containsAll(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.set(0, null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.indexOf(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.lastIndexOf(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.listIterator());
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.listIterator(0));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.subList(0, 0));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.spliterator());
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.stream());
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.parallelStream());
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.addAll(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> list.addAll(0, null));
  }

  @Test
  void test13Task2() {
    var queue = new MyQueue<Integer>();
    Assertions.assertNotNull(queue.getQueue());
  }

  @Test
  void test14Task2() {
    var queue = new MyQueue<Integer>();
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> queue.size());
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> queue.contains(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> queue.iterator());
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> queue.forEach(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> queue.toArray());
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> queue.toArray(new Object[]{}));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> queue.remove(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> queue.addAll(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> queue.removeIf(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> queue.clear());
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> queue.spliterator());
  }
}
