package com.hsf.learn.demo.datastructure.customsort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements MyComparable<Book>{
    private String name;
    private int price;
    @Override
    public int compare(Book book) {
        return price - book.price;
    }
}
