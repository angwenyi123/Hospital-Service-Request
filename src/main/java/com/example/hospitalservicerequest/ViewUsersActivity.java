package com.example.hospitalservicerequest;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewUsersActivity extends AppCompatActivity {

    ListView lvUsers;
    DatabaseHelper dbHelper;
    ArrayList<String> userList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        lvUsers = findViewById(R.id.lvUsers);
        dbHelper = new DatabaseHelper(this);
        userList = new ArrayList<>();

        loadUsers();
    }

    private void loadUsers() {
        Cursor cursor = dbHelper.getAllUsers();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No users found", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                // column 1 is username, column 3 is role (based on DatabaseHelper onCreate)
                String user = "User: " + cursor.getString(1) + " | Role: " + cursor.getString(3);
                userList.add(user);
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
            lvUsers.setAdapter(adapter);
        }
        cursor.close();
    }
}
