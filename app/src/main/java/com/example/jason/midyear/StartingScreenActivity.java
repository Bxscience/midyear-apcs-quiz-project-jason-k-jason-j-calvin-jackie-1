package com.example.jason.midyear;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import java.util.List;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
import android.view.View;

public class StartingScreenActivity extends Activity implements OnItemSelectedListener{
    private Spinner spinnerCategory;
    private List<String> categories;
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";
    private Button quit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);
        spinnerCategory=findViewById(R.id.categoriesSpinner);
        spinnerCategory.setOnItemSelectedListener(this);
        loadCategories();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,categories);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
        quit=findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    private void startQuiz() {
        String selectedCategory=(String)spinnerCategory.getSelectedItem();
        Intent intent = new Intent(StartingScreenActivity.this, QuizActivity.class);
        intent.putExtra(EXTRA_CATEGORY_NAME,selectedCategory);
        startActivity(intent);
    }
    private void loadCategories(){
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        categories = dbHelper.getAllCategories();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}