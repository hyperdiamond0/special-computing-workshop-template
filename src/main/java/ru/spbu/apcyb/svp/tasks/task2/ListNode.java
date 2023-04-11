package ru.spbu.apcyb.svp.tasks.task2;

public class ListNode<E> {
  private E data;
  private ListNode<E> next;
  private ListNode<E> previous;

  public ListNode(E data) {
    this.data = data;
    next = null;
    previous = null;
  }

  public E getData() {
    return data;
  }

  public void setData(E data) {
    this.data = data;
  }

  public ListNode<E> getNext() {
    return next;
  }

  public void setNext(ListNode<E> next) {
    this.next = next;
  }

  public ListNode<E> getPrevious() {
    return previous;
  }

  public void setPrevious(ListNode<E> previous) {
    this.previous = previous;
  }
}
