package com.example.konyvtar;

import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private final List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(String title, String author, int pages) {
        books.add(new Book(title, author, pages));
    }

    public void removeBook(int position) {
        books.remove(position);
    }
}

