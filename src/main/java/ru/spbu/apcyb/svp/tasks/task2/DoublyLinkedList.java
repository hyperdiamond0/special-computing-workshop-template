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

  public DoublyLinkedList() {
    this.head = null;
    this.tail = null;
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
    if (head == null && tail == null) {
      head = tail = newNode;
      head.setPrevious(null);
      head.setNext(null);
      tail.setPrevious(null);
      tail.setNext(null);
      return true;
    }
    tail.setNext(newNode);
    newNode.setNext(null);
    newNode.setPrevious(tail);
    tail = newNode;
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
    var tmp = head;
    if (tmp == null) {
      if (index == 0) {
        head = newNode;
        tail = head;
        head.setNext(null);
        head.setPrevious(null);
        tail.setNext(null);
        tail.setPrevious(null);
        return;
      }
      throw new IndexOutOfBoundsException(
          "Невозможно произвести добавление в пустой список по ненулевому индексу");
    }
    while (index > 0) {
      if (tmp.getNext() == null) {
        if (index != 1) {
          throw new IndexOutOfBoundsException(
              "Невозможно разместить элемент по индексу, превыщающему длину списка более чем на 1");
        }
        break;
      }
      tmp = tmp.getNext();
      index--;
    }
    if (index == 1) { //если после цикла индекс 1, значит добавление происходит в конец
      this.add(element);
      return;
    }
    newNode.setNext(tmp);
    newNode.setPrevious(tmp.getPrevious());
    if (tmp.getPrevious() == null) {
      head = newNode;
    } else {
      tmp.getPrevious().setNext(newNode);
    }
  }


  //2. Удаление по индексу
  @Override
  public E remove(int index) {
    ListNode<E> tmp = getNodeWithIndex(index);
    if (tmp.getNext() != null) {
      tmp.getNext().setPrevious(tmp.getPrevious());
    }
    if (tmp.getPrevious() != null) {
      tmp.getPrevious().setNext(tmp.getNext());
    }
    if (head == tmp) {
      head = tmp.getNext();
    }
    if (tail == tmp) {
      tail = tmp.getPrevious();
    }
    tmp.setNext(null);
    tmp.setPrevious(null);
    return tmp.getData();
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException();
  }

  private ListNode<E> getNodeWithIndex(int index) {
    var tmp = head;
    if (tmp == null) {
      throw new IndexOutOfBoundsException("Список пуст");
    }
    while (index > 0) {
      if (tmp.getNext() == null) {
        throw new IndexOutOfBoundsException("Индекс превышает длину списка");
      }
      tmp = head.getNext();
      index--;
    }
    return tmp;
  }

  //3. Проверка наличия по значению
  @Override
  public boolean contains(Object o) {
    ListNode<E> newNode;
    try {
      newNode = new ListNode<>((E) o);
    } catch (ClassCastException e) {
      throw new IllegalArgumentException("Некорректный тип аргумента");
    }
    var tmp = head;
    if (tmp == null) {
      return false;
    }
    while (tmp.getNext() != null) {
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
    return head == null && tail == null;
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
    return super.hashCode();
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
