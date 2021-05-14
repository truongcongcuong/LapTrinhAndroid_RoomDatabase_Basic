package com.example.laptrinhandroid_roomdatabase_basic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.laptrinhandroid_roomdatabase_basic.room.DatabaseRoom;
import com.example.laptrinhandroid_roomdatabase_basic.room.dao.StudentDAO;
import com.example.laptrinhandroid_roomdatabase_basic.room.entity.Student;

public class MainActivity extends AppCompatActivity {
    ListView lvi_students;
    EditText edt_student;
    Button btt_add, btt_remove, btt_cancel;
    StudentDAO studentDAO;
    int index = -1;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //database
        DatabaseRoom room = Room.databaseBuilder(getApplicationContext(),DatabaseRoom.class,"student-list").
                allowMainThreadQueries().build();
        studentDAO = room.studentDAO();
        //
        lvi_students = findViewById(R.id.lvi_students);
        edt_student = findViewById(R.id.edtName);
        btt_add = findViewById(R.id.btt_add);
        btt_cancel = findViewById(R.id.btt_cancel);
        btt_remove = findViewById(R.id.btt_remove);
        btt_add.setText("Add");
        btt_remove.setText("Remove");
        btt_cancel.setText("Cancel");
        //adappter
        ArrayAdapter<Student> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,studentDAO.getAll());
        lvi_students.setAdapter(arrayAdapter);
        //

        btt_add.setOnClickListener(v->{
            String txtName = edt_student.getText().toString();
            if(!txtName.equalsIgnoreCase("")){
                studentDAO.insertAll(new Student(txtName));
                dataChange();
                edt_student.setText("");
            }
        });
        btt_remove.setOnClickListener(v->{
            if(index!=-1){
                studentDAO.Delete(studentDAO.getAll().get(index));
                dataChange();
                index=-1;
            }
            else Toast.makeText(this,"Chon sv can xoa",Toast.LENGTH_SHORT).show();
        });
        lvi_students.setOnItemClickListener((parent, view, position, id) -> index = position);

        btt_cancel.setOnClickListener(v-> edt_student.setText(""));

    }

    private void dataChange() {
        ArrayAdapter<Student> adapterChange = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,studentDAO.getAll());
        lvi_students.setAdapter(adapterChange);
    }
}