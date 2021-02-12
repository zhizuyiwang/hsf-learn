package com.hsf.learn.demo.datastructure;

public interface List<E> {
    static final int ELEMENT_NOT_FOUND = -1;

    /**
     * 清除所有元素
     */
    void clear();
    /**
     * 元素的数量
     * @return
     */
    int size();
    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();
    /**
     * 是否包含某个元素
     * @param element
     * @return
     */
    boolean contains(E element);
    /**
     * 添加元素到尾部
     * @param element
     */
    void add(E element);

}
