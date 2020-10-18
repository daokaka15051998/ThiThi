package com.daonv.thithi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

        EditText edtID,edtName,edtPhone;
        private StudentDAO studentDAO;
        ListView lvStudent;
        StudentAdapter studentAdapter;
        List<Student> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       edtID=  findViewById(R.id.edtId);
       edtName= findViewById(R.id.edtName);
       edtPhone= findViewById(R.id.edtPhone);
       lvStudent = findViewById(R.id.lvDanhSach);

        studentDAO = new StudentDAO(this);

        list = studentDAO.getAllStudent();

       studentAdapter = new StudentAdapter(list,MainActivity.this);

       lvStudent.setAdapter(studentAdapter);
    }


    public void btnThem(View view) {
        studentDAO = new StudentDAO(MainActivity.this);
        Student student = new Student();
        student.id = edtID.getText().toString();
        student.name = edtName.getText().toString();
        student.phone = edtPhone.getText().toString();
        long result = studentDAO.Them(student);
        if (result > 0){
            Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            list.add(student);

        }else {
            Toast.makeText(MainActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }
        edtID.setText("");
        edtName.setText("");
        edtPhone.setText("");

    }

    public void btnSua(View view) {


    }

    public void btnXoa(View view) {
    }

}