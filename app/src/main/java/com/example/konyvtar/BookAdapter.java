package com.example.konyvtar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends BaseAdapter {
    private final Context context;
    private final List<Book> books;

    public BookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_book, parent, false);
        }

        Book book = books.get(position);
        TextView titleView = convertView.findViewById(R.id.book_title);
        TextView authorView = convertView.findViewById(R.id.book_author);
        TextView pagesView = convertView.findViewById(R.id.book_pages);
        Button deleteButton = convertView.findViewById(R.id.delete_button);

        titleView.setText(book.getTitle());
        authorView.setText(book.getAuthor());
        pagesView.setText(String.valueOf(book.getPages()));

        deleteButton.setOnClickListener(v -> {
            if (context instanceof MainActivity) {
                ((MainActivity) context).onDeleteBook(position);
            }
        });

        return convertView;
    }
}

