package ru.spbu.apcyb.svp.tasks.task2;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 *  двусвязный список.
 *
 * @param <E> класс, который является типом хранимых объектов в списке
 *
 */

public class DoublyLinkedList<E> implements java.util.List<E> {

  private ListNode<E> head;
  private ListNode<E> tail;
  private int size;


  /**
   * конструктор, создающий пустой список длины 0.
   *
   */
  public DoublyLinkedList() {
    this.head = null;
    this.tail = null;
    size = 0;
  }

  public ListNode<E> getHead() {
    return head;
  }

  public ListNode<E> getTail() {
    return tail;
  }

  //1. добавление в конец
  @Override
  public boolean add(Object o) {
    ListNode<E> newNode;
    try {
      newNode = new ListNode<>((E) o);
    } catch (ClassCastException e) {
      throw new IllegalArgumentException("Неправильный тип аргумента");
    }
    if (this.contains(o)) {
      return false;
    }
    if (size == 0) {
      head = tail = newNode;
      size++;
      return true;
    }
    tail.setNext(newNode);
    newNode.setPrevious(tail);
    tail = newNode;
    size++;
    return true;
  }

  //6. Добавление на конкретную позицию
  @Override
  public void add(int index, Object element) {
    ListNode<E> newNode;
    try {
      newNode = new ListNode<>((E) element);
    } catch (ClassCastException e) {
      throw new IllegalArgumentException("Некорректный тип аргумента");
    }
    if (size == 0) {
      if (index == 0) {
        head = newNode;
        tail = head;
        size++;
        return;
      }
      throw new IndexOutOfBoundsException(
          "Невозможно произвести добавление в пустой список по ненулевому индексу");
    }
    if (index == 0) {
      newNode.setNext(head);
      head.setPrevious(newNode);
      head = newNode;
      size++;
      return;
    }
    if (index == size) {
      this.add(element);
      return;
    }
    if (index > size) {
      throw new IndexOutOfBoundsException(
          "Невозможно разместить элемент по индексу, превыщающему длину списка");
    }

    var tmp = head;
    for (int i = 0; i < index - 1; i++) {
      tmp = tmp.getNext();
    }
    newNode.setNext(tmp.getNext());
    newNode.setPrevious(tmp);
    tmp.getNext().setPrevious(newNode);
    tmp.setNext(newNode);
    size++;
  }


  //2. Удаление по индексу
  @Override
  public E remove(int index) {
    if (size == 0) {
      throw new IllegalArgumentException("Невозможно удаление из пустого списка");
    }
    E returnValue = null;
    if (size == 1) {
      returnValue = head.getData();
      head = tail = null;
    } else if (index == 0) {
      head.getNext().setPrevious(null);
      returnValue = head.getData();
      head = head.getNext();
    } else if (index == size - 1) {
      tail.getPrevious().setNext(null);
      returnValue = tail.getData();
      tail = tail.getPrevious();
    } else {
      ListNode<E> tmp = getNodeWithIndex(index);
      returnValue = tmp.getData();
      tmp.getPrevious().setNext(tmp.getNext());
      tmp.getNext().setPrevious(tmp.getPrevious());
    }
    size--;
    return returnValue;
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException();
  }

  private ListNode<E> getNodeWithIndex(int index) {
    if (size == 0) {
      throw new IndexOutOfBoundsException("Список пуст");
    }
    if (index >= size) {
      throw new IndexOutOfBoundsException("Индекс превышает длину списка");
    }
    var tmp = head;
    for (int i = 0; i < index; i++) {
      tmp = head.getNext();
    }
    return tmp;
  }

  //3. Проверка наличия по значению
  @Override
  public boolean contains(Object o) {
    if (size == 0) {
      return false;
    }
    ListNode<E> newNode;
    try {
      newNode = new ListNode<>((E) o);
    } catch (ClassCastException e) {
      throw new IllegalArgumentException("Некорректный тип аргумента");
    }
    var tmp = head;
    for (int i = 0; i < size - 1; i++) {
      if (tmp.getData().equals(newNode.getData())) {
        return true;
      }
      tmp = tmp.getNext();
    }
    return tmp.getData().equals(newNode.getData());
  }


  //4. Проверка пустоты списка
  @Override
  public boolean isEmpty() {
    return size == 0;
  }


  //5. Получение по индексу
  @Override
  public E get(int index) {
    var tmp = getNodeWithIndex(index);
    return tmp.getData();
  }

  //_________________________________________________________________

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof DoublyLinkedList)) {
      return false;
    }
    if (size != ((DoublyLinkedList) obj).size) {
      return false;
    }
    ListNode<E> tmp1 = head;
    ListNode<E> tmp2 = ((DoublyLinkedList) obj).head;
    while (tmp1.getNext() != null && tmp2.getNext() != null) {
      if (!tmp1.getData().equals(tmp2.getData())) {
        return false;
      }
      tmp1 = tmp1.getNext();
      tmp2 = tmp2.getNext();
    }
    return tmp1.getNext() == null && tmp2.getNext() == null;
  }

  @Override
  public int hashCode() {
    int result = 29;
    var tmp = head;
    for (int i = 0; i < size; i++) {
      result = 37 * result + tmp.hashCode();
      tmp = tmp.getNext();
    }
    return result;
  }

  //________________________

  @Override
  public Iterator iterator() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int size() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void forEach(Consumer action) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object[] toArray() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object[] toArray(IntFunction generator) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object[] toArray(Object[] a) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeIf(Predicate filter) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void replaceAll(UnaryOperator operator) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void sort(Comparator c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean retainAll(Collection c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(Collection c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean containsAll(Collection c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object set(int index, Object element) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int indexOf(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int lastIndexOf(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ListIterator listIterator() {
    throw new UnsupportedOperationException();
  }

  @Override
  public ListIterator listIterator(int index) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List subList(int fromIndex, int toIndex) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Spliterator spliterator() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Stream stream() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Stream parallelStream() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(Collection c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(int index, Collection c) {
    throw new UnsupportedOperationException();
  }
}
