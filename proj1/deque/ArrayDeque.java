package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
  private T[] items;
  private int size;
  private int nextFirst;
  private int nextLast;

  public ArrayDeque(){
    items = (T[]) new Object[8];
    size = 0;
    nextFirst = 4;
    nextLast = 5;
  }

  public void addFirst(T item){
    // TODO: No need to resizing
    items[nextFirst] = item;
    nextFirst = (nextFirst - 1 + items.length) % items.length;
    size++;
  }

  public void addLast(T item){
    // TODO: No need to resizing
    items[nextLast] = item;
    nextLast = (nextLast + 1) % items.length;
    size++;
  }

  public T removeFirst(){
    if(size() == 0){
      return null;
    }

    nextFirst = (nextFirst + 1) % items.length;
    T item = items[nextFirst];
    items[nextFirst] = null;
    size--;
    return item;
  }

  public T removeLast(){
    if(size() == 0){
      return null;
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

  @Override
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
