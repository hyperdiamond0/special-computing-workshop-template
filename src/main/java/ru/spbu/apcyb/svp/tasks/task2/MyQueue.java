package ru.spbu.apcyb.svp.tasks.task2;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;


/**
 * Очередь.
 *
 * @param <E>
 *     Тип объектов в очереди.
 */
public class MyQueue<E> implements java.util.Queue<E> {

  private DoublyLinkedList<E> queue;

  public DoublyLinkedList<E> getQueue() {
    return queue;
  }

  public MyQueue() {
    queue = new DoublyLinkedList<>();
  }

  public MyQueue(DoublyLinkedList<E> list) {
    queue = list;
  }


  //1. Постановка в очередь
  @Override
  public boolean add(Object o) {
    return queue.add(o);
  }


  //2. Получение первого в очереди
  @Override
  public E peek() {
    return queue.get(0);
  }


  //3. Проверка пустоты
  @Override
  public boolean isEmpty() {
    return queue.isEmpty();
  }

  //___________________________

  @Override
  public int size() {
    throw new UnsupportedOperationException();
  }


  @Override
  public boolean contains(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterator iterator() {
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
  public boolean remove(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public E remove() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(Collection c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeIf(Predicate filter) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void clear() {
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
  public boolean offer(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public E poll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public E element() {
    throw new UnsupportedOperationException();
  }
}
