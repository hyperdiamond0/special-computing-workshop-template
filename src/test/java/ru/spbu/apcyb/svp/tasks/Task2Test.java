package ru.spbu.apcyb.svp.tasks;

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

  @BeforeAll
  void nodesInit() {
    previousNode = new ListNode<>(1);
    nextNode = new ListNode<>(3);
  }

  @Test
  void testListNodeGetData() {
    var currentNode = new ListNode<>(2);
    Assertions.assertEquals(2, currentNode.getData());
  }

  @Test
  void testListNodeSetData() {
    var currentNode = new ListNode<>(2);
    currentNode.setData(4);
    Assertions.assertEquals(4, currentNode.getData());
  }

  @Test
  void testListNodeSetAndGetNext() {
    var currentNode = new ListNode<>(2);
    currentNode.setNext(nextNode);
    Assertions.assertEquals(nextNode, currentNode.getNext());
  }

  @Test
  void testListNodeSetAndGetPrevious() {
    var currentNode = new ListNode<>(2);
    currentNode.setPrevious(previousNode);
    Assertions.assertEquals(previousNode, currentNode.getPrevious());
  }

  @Test
  void testDoublyLinkedListAddToEnd() {
    var list = new DoublyLinkedList<Integer>();
    list.add(5);
    Assertions.assertEquals(5, list.getTail().getData());
  }

  @Test
  void testDoublyLinkedListAddToIndex() {
    var list = new DoublyLinkedList<Integer>();
    list.add(5);
    list.add(0, 3);
    Assertions.assertEquals(3, list.getHead().getData());
  }

  @Test
  void testDoublyLinkedListAddToEmptyWithIllegalIndex() {
    var list = new DoublyLinkedList<Integer>();
    Assertions.assertThrows(
        IndexOutOfBoundsException.class, () -> list.add(5, 5)
    );
  }

  @Test
  void testDoublyLinkedListAddToEmptyWithIndexZero() {
    var list = new DoublyLinkedList<Integer>();
    list.add(0, 1);
    Assertions.assertEquals(1, list.getHead().getData());
  }

  @Test
  void testDoublyLinkedListAddToEndWithIndex() {
    var list = new DoublyLinkedList<Integer>();
    list.add(3);
    list.add(5);
    list.add(2, 4);
    Assertions.assertEquals(4, list.getTail().getData());
  }

  @Test
  void testDoublyLinkedListAddToMiddleWithIndex() {
    var list = new DoublyLinkedList<Integer>();
    list.add(3);
    list.add(5);
    list.add(1, 4);
    Assertions.assertEquals(4, list.getTail().getPrevious().getData());
  }

  @Test
  void testDoublyLinkedListAddToEndIfElementAlreadyAdded() {
    var list = new DoublyLinkedList<Integer>();
    list.add(5);
    Assertions.assertFalse(list.add(5));
  }

  @Test
  void testDoublyLinkedListContains() {
    var list = new DoublyLinkedList<Integer>();
    list.add(3);
    Assertions.assertTrue(list.contains(3));
  }

  @Test
  void testDoublyLinkedListContainsInBiggerList() {
    var list = new DoublyLinkedList<Integer>();
    list.add(3);
    list.add(5);
    Assertions.assertTrue(list.contains(5));
  }

  @Test
  void testDoublyLinkedListGet() {
    var list = new DoublyLinkedList<Integer>();
    list.add(3);
    Assertions.assertEquals(3, list.get(0));
  }

  @Test
  void testDoublyLinkedListIsEmpty() {
    var list = new DoublyLinkedList<Integer>();
    Assertions.assertTrue(list.isEmpty());
    list.add(1);
    Assertions.assertFalse(list.isEmpty());
  }

  @Test
  void testDoublyLinkedListRemove() {
    var list = new DoublyLinkedList<Integer>();
    list.add(5);
    Assertions.assertEquals(5, list.remove(0));
    Assertions.assertTrue(list.isEmpty());
  }

  @Test
  void testDoublyLinkedListRemoveFromHead() {
    var list = new DoublyLinkedList<Integer>();
    list.add(3);
    list.add(5);
    Assertions.assertEquals(3, list.remove(0));
  }

  @Test
  void testDoublyLinkedListRemoveFromTail() {
    var list = new DoublyLinkedList<Integer>();
    list.add(3);
    list.add(5);
    Assertions.assertEquals(5, list.remove(1));
  }

  @Test
  void testDoublyLinkedListRemoveFromEmpty() {
    var list = new DoublyLinkedList<Integer>();
    Assertions.assertThrows(
        IllegalArgumentException.class, () -> list.remove(0)
    );
  }

  @Test
  void testDoublyLinkedListRemoveFromMiddle() {
    var list = new DoublyLinkedList<Integer>();
    list.add(3);
    list.add(4);
    list.add(5);
    Assertions.assertEquals(4, list.remove(1));
  }

  @Test
  void testDoublyLinkedListEmptyCtor() {
    var list = new DoublyLinkedList<Integer>();
    Assertions.assertNull(list.getTail());
    Assertions.assertNull(list.getHead());
  }

  @Test
  void testDoublyLinkedListEquals() {
    var list = new DoublyLinkedList<>();
    Assertions.assertNotEquals(null, list);
    Assertions.assertNotEquals(new Object(), list);
    var list2 = new DoublyLinkedList<>();
    list.add(3);
    list2.add(3);
    Assertions.assertEquals(list, list2);
    list.add(4);
    Assertions.assertNotEquals(list, list2);
  }

  @Test
  void testMyQueueIsEmpty() {
    var queue = new MyQueue<Integer>();
    Assertions.assertTrue(queue.isEmpty());
    queue.add(1);
    Assertions.assertFalse(queue.isEmpty());
  }

  @Test
  void testMyQueueAdd() {
    var queue = new MyQueue<Integer>();
    queue.add(2);
    Assertions.assertEquals(2, queue.getQueue().getHead().getData());
  }

  @Test
  void testMyQueuePeek() {
    var queue = new MyQueue<Integer>();
    queue.add(2);
    queue.add(3);
    Assertions.assertEquals(2, queue.peek());
  }

  @Test
  void testMyQueueEmptyCtor() {
    var queue = new MyQueue<Integer>();
    Assertions.assertNotNull(queue.getQueue());
  }

  @Test
  void testDoublyLinkedListUnsupportedMethods() {
    var list = new DoublyLinkedList<Integer>();
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
  void testMyQueueUnsupportedMethods() {
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
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> queue.stream());
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> queue.parallelStream());
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> queue.retainAll(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> queue.containsAll(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> queue.offer(null));
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> queue.poll());
    Assertions.assertThrows(
        UnsupportedOperationException.class, () -> queue.element());
  }
}
