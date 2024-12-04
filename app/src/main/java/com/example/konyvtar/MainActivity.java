package com.example.konyvtar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTitle,editAuthor,editPages;
    private Button addButton;

    private ListView bookList;
    private BookManager bookManager;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        addButton.setOnClickListener(v -> {
            String title = editTitle.getText().toString().trim();
            String author = editAuthor.getText().toString().trim();
            String pages_string = editPages.getText().toString().trim();

            if (title.isEmpty() || author.isEmpty() || pages_string.isEmpty()) {
                Toast.makeText(this, "Az összes adatot ki kell tölteni!", Toast.LENGTH_SHORT).show();
                return;
            }

            int pages = Integer.parseInt(pages_string);
            if (pages < 50) {
                Toast.makeText(this, "Az oldal számának minimum 50-nek kell lennie", Toast.LENGTH_SHORT).show();
                return;
            }

            bookManager.addBook(title, author, pages);
            bookAdapter.notifyDataSetChanged();

            editTitle.setText("");
            editAuthor.setText("");
            editPages.setText("");
        });

        bookList.setOnItemClickListener((parent, view, position, id) -> {
            Book book = bookManager.getBooks().get(position);

            Intent intent = new Intent(MainActivity.this, ActivityDetails.class);
            intent.putExtra("title", book.getTitle());
            intent.putExtra("author", book.getAuthor());
            intent.putExtra("pages", book.getPages());
            startActivity(intent);
        });
    }
    public void init(){
        editTitle = findViewById(R.id.edit_title);
        editAuthor = findViewById(R.id.edit_author);
        editPages = findViewById(R.id.edit_pages);
        addButton = findViewById(R.id.add_button);
        bookList = findViewById(R.id.book_list);

        bookAdapter = new BookAdapter(this, bookManager.getBooks());
        bookList.setAdapter(bookAdapter);
    }
    public void onDeleteBook(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Könyv törlés")
                .setMessage("Biztos kiakarod törölni ezt a könyvet?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    bookManager.removeBook(position);
                    bookAdapter.notifyDataSetChanged();
                })
                .setNegativeButton("No", null)
                .show();
    }
}