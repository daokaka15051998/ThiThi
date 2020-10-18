package com.daonv.thithi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private StudentSql mySQL;

    public StudentDAO(Context context) {
        mySQL = new StudentSql(context);
    }

    public static final String  create_table =
            "CREATE TABLE student (id int integer primary key, name varchar, phone varchar)";

    public long Them(Student student){

        SQLiteDatabase db = mySQL.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",student.id);
        contentValues.put("name",student.name);
        contentValues.put("phone",student.phone);
        long result = db.insert("student",null,contentValues);
        db.close();
        return result;

    }

    public List<Student> getAllStudent(){
        SQLiteDatabase db = mySQL.getWritableDatabase();
        List<Student> studentList = new ArrayList<>();
        Cursor cursor = db.query("student",null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() ==false){
            Student student = new Student();
            student.setId(cursor.getString(0));
            student.setName(cursor.getString(1));
            student.setPhone(cursor.getString(2));
            studentList.add(student);
            cursor.moveToNext();

        }
        cursor.close();
        return studentList;
    }

    public long Sua(Student student){
        SQLiteDatabase db = mySQL.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",student.id);
        contentValues.put("name",student.name);
        contentValues.put("phone",student.phone);
        long result = db.update("student",contentValues,"id=?",new String[]{student.id});
        if (result ==0){
            return -1;
        }
            return 1;
    }
    public long Xoa(Student student){
        SQLiteDatabase db = mySQL.getWritableDatabase();
        long result = db.delete("student","id=?",new String[]{student.id});
        if (result == 0){
            return -1;
        }
            return 1;
    }

}
