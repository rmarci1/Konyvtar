package com.example.konyvtar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityDetails extends AppCompatActivity {

    private TextView titleView,authorView,pagesView,yearView;
    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        Intent intent = getIntent();
        titleView.setText(intent.getStringExtra("title"));
        authorView.setText(intent.getStringExtra("author"));
        pagesView.setText(String.valueOf(intent.getIntExtra("pages", 0)));

        int randomYear = (int) (1900 + Math.random() * 121);
        yearView.setText("Kiadatási év:  " + randomYear);

        backButton.setOnClickListener(v -> finish());
    }
    public void init(){
        titleView = findViewById(R.id.detail_title);
        authorView = findViewById(R.id.detail_author);
        pagesView = findViewById(R.id.detail_pages);
        yearView = findViewById(R.id.detail_year);
        backButton = findViewById(R.id.back_button);
    }
}