package com.hsf.learn.demo.datastructure;

public class ArrayList {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;
    private int size;
    private int[] elements;

    ArrayList(int capacity){
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = new int[capacity];
    }
    ArrayList(){
        this(DEFAULT_CAPACITY);
    }

    public void clear(){
        size = 0;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(int element){
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    public void add(int element){
        elements[size++] = element;

    }

    public int get(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index: " + index + "; Size: " + size);
        }
        return elements[index];
    }
    public int set(int index, int element){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index: " + index + "; Size: " + size);
        }
        int old = elements[index];
        elements[index] = element;
        return old;
    }
    public void add(int index, int element){
        for(int i = size - 1; i >= index; i++){
            elements[size] = elements[i];
        }
        
    }

    public int remove(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index: " + index + "; Size: " + size);
        }
        int old = elements[index];
        for(int i = index + 1; i <= size-1; i++){
            elements[i-1] = elements[i];
        }
        size--;
        return old;

    }
    public int indexOf(int element){
        for(int i = 0; i < size; i++){
            if(elements[i] == element) return i;
        }
        return ELEMENT_NOT_FOUND;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size:").append(size).append("; [");
        for(int i = 0; i < size; i++){
            stringBuilder.append(elements[i]);
            if(i != size-1){
                stringBuilder.append(" ,");
            }
        }


        stringBuilder.append("]");

        return stringBuilder.toString();
    }

}
