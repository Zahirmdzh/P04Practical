package sg.edu.rp.c347.p04_practical;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etName, etGpa;
    Button btnInsert, btnRetrieve;
    TextView tvResult;
    ArrayList<Student> alStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGpa = findViewById(R.id.editTextGPA);

        btnInsert = findViewById(R.id.buttonInsert);
        btnRetrieve = findViewById(R.id.buttonRetrieve);

        tvResult = findViewById(R.id.Result);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(MainActivity.this);
                db.getWritableDatabase();

                // Insert a task
                String name = etName.getText().toString();
                String gpa = etGpa.getText().toString();


                db.insertTask(name, Double.parseDouble(gpa));
                db.close();

            }
        });

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<Student> data = db.retrieveTask();
                db.close();

                String txt = "";


                alStudent = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {

                    alStudent.add(data.get(i));
                    String name = alStudent.get(i).getName();
                    double gpa = alStudent.get(i).getGpa();
                    txt += "Name: " + name + " Gpa: " + Double.toString(gpa) + "\n";
                }


                    tvResult.setText(txt);

            }
        });
    }
}
