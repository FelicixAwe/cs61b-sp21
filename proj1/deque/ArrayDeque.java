package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
  private T[] items;
  private int size;
  private int nextFirst;
  private int nextLast;

  private static int RFACTOR = 2;

  public ArrayDeque(){
    items = (T[]) new Object[8];
    size = 0;
    nextFirst = 4;
    nextLast = 5;
  }

  private void resize(int capacity){
    T[] a = (T[]) new Object[capacity];
    int oldIndex = (nextFirst + 1) % items.length;
    for(int newIndex = 0; newIndex < size; newIndex++){
      a[newIndex] = items[oldIndex];
      oldIndex = (oldIndex + 1) % items.length;
    }
    items = a;
    nextFirst = capacity - 1;
    nextLast = size;
  }

  private boolean isFull(){
    return size == items.length;
  }

  public void addFirst(T item){
    if (isFull()) {
      resize(RFACTOR * items.length);
    }
    items[nextFirst] = item;
    nextFirst = (nextFirst - 1 + items.length) % items.length;
    size++;
  }

  public void addLast(T item){
    if (isFull()) {
      resize(RFACTOR * items.length);
    }
    items[nextLast] = item;
    nextLast = (nextLast + 1) % items.length;
    size++;
  }

  public T removeFirst(){
    if(isEmpty()){
      return null;
    }
    if(size < items.length / 4 && size > 8){
      resize(items.length / 2);
    }
    nextFirst = (nextFirst + 1) % items.length;
    T item = items[nextFirst];
    items[nextFirst] = null;
    size--;
    return item;
  }

  public T removeLast(){
    if(isEmpty()){
      return null;
    }
    if(size < items.length / 4 && size > 8){
      resize(items.length / 2);
    }
    nextLast = (nextLast - 1 + items.length) % items.length;
    T item = items[nextLast];
    items[nextLast] = null;
    size--;
    return item;
  }

  public int size(){
    return size;
  }

  public T get(int index){
    if(index >= size || index < 0){
      return null;
    }
    int actualIndex = (nextFirst + 1 + index) % items.length;
    return items[actualIndex];
  }

  public void printDeque(){
    int cur = (nextFirst + 1) % items.length;
    for(int i = 0; i < size; i++){
      System.out.print(items[cur] + " ");
      cur = (cur + 1) % items.length;
    }
    System.out.println();
  }

  public Iterator<T> iterator(){
    return new ArrayListDequeIterator();
  }

  private class ArrayListDequeIterator implements Iterator<T> {
    private int cur;
    private int count;
    public ArrayListDequeIterator(){
      cur = (nextFirst + 1) % items.length;
      count = 0;
    }
    @Override
    public boolean hasNext(){
      return count < size();
    }

    @Override
    public T next(){
      if(!hasNext()){
        return null;
      }
      T item = items[cur];
      cur = (cur + 1) % items.length;
      count++;
      return item;
    }
  }

}
