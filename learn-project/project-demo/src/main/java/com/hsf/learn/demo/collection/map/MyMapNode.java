package com.hsf.learn.demo.collection.map;


import java.util.Objects;

public class MyMapNode<K,V> {

    final int hash;
    final K key;
    V value;
    MyMapNode<K,V> next;

    public MyMapNode(int hash, K key, V value, MyMapNode<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public final V getValue() {
        return value;
    }

    public final K getKey() {
        return key;
    }
    public final boolean equals(Object object){
        if(this == object){
            return true;
        }else if(object instanceof MyMapNode){
            MyMapNode temp = (MyMapNode) object;
            if(Objects.equals(key, temp.key) && Objects.equals(value, temp.value)){
                return true;
            }
        }
        return false;
    }
    public final int hashCode(){
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }
    public final V setValue(final V newValue){
        V oldValue = value;
        value = newValue;
        return oldValue;
    }
    public final String toString(){
        return key + "=" + value;
    }
}
