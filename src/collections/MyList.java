package collections;


import collections.interfaces.MyListInterface;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/*
*  T - should be object.
* */
public class MyList<T> implements MyListInterface<T> {
    private Object[] list;
    private int currentIndex;

    public MyList(int initialCapacity) {
        super();
        list = new Object[initialCapacity];
        currentIndex = 0;
    }

    public MyList() {
        super();
        list = new Object[10];
        currentIndex = 0;
    }

    @Override
    public int size() {
        return currentIndex + 1;
    }

    @Override
    public boolean isEmpty() {
        return currentIndex == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i <= currentIndex; i++) {
            if (o == null && list[i] == null) {
                return true;
            } else if (o.equals(list[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return Arrays.asList(list).iterator();
    }

    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public Object[] toArray() {
        return list.clone();
    }

    // returns false in case if collection not allows to store duplicated elements (Set)
//    @Override
//    public boolean add(Object o) {
//        list[currentIndex] = (T) o;
//        currentIndex += 1;
//        if (list.length - 1 == currentIndex) {
//            doubleCollection();
//        }
//        return true;
//    }

    public boolean add(T o) {
        list[currentIndex] = o;
        currentIndex += 1;
        if (list.length - 1 == currentIndex) {
            // doubleCollection();
            expandCollection();
        }
        return true;
    }


    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            for (int i = index; i < list.length - 1; i++) {
                list[i] = list[i + 1];
            }
            return true;
        }
        return false;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        c.forEach(item -> add(item));
        return true;
    }

    @Override
    public void clear() {
        list = new Object [list.length];
    }

    @Override
    public boolean equals(Object o) {
        return list.equals(o);
    }

    @Override
    public int hashCode() {
        int sum = 0;
        for(Object item: list) {
            sum += item.hashCode();
        }
        return sum + list.length;
    }

    @Override
    public boolean retainAll(Collection c) {
        Object[] result = (T[]) new Object[list.length];
        Object[] collectionAr = c.toArray();
        int index = 0;
        for (Object item : list) {
            if (arrayContains(collectionAr, item)) {
                result[index++] = item;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Object[] collectionArray = c.toArray();
        Object[] itemsToRemove =  new Object[c.size()];
        int index = 0;
        for (Object item : list) {
            if (arrayContains(collectionArray, item)) {
                itemsToRemove[index] = item;
                index++;
            }
        }
        for (Object item : itemsToRemove) {
            remove(item);
        }
        return itemsToRemove.length > 0;
    }

    @Override
    public boolean containsAll(Collection c) {
        int total = c.size();
        Object[] collectionArray = c.toArray();
        for (Object item : list) {
            if (arrayContains(collectionArray, item)) {
                total--;
            }
        }
        return total == 0;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return list.clone();
    }

    private boolean arrayContains(Object[] ar, Object value) {
        for (Object element : ar) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Array getAll() {
        return null;
    }

    @Override
    public Optional getIf(Predicate filter) {
//        for (T item : list) {
//            if (filter.test(item)) {
//                return Optional.of(item);
//            }
//        }
        return Optional.empty();
    }

    @Override
    public boolean addIf(Predicate<T> filter, T value) {
        for (Object item : list) {
            if (filter.test((T) item)) {
                add(value);
                return true;
            }
        }
        return false;
    }

    private void doubleCollection() {
        int currentLength = list.length;
        T[] extended = (T[]) new Object[currentLength * 2];
        for (int i = 0; i < list.length; i ++ ) {
            // extended[i] = list[i];
        }
        list = extended;
    }

    private void expandCollection() {
        Object[] expanded =  new Object[list.length + 1];
        for (int i = 0; i < list.length; i++) {
            expanded[i] = list[i];
        }
        list = expanded;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for(Object item : list) {
            res.append(item).append(" ");
        }
        return res.toString();
    }
}
