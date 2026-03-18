package com.example.hospitalservicerequest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateServiceActivity extends AppCompatActivity {

    EditText etId, etName, etDesc;
    Button btnUpdate;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_service);

        etId = findViewById(R.id.etUpdateServiceId);
        etName = findViewById(R.id.etUpdateServiceName);
        etDesc = findViewById(R.id.etUpdateServiceDesc);
        btnUpdate = findViewById(R.id.btnUpdateService);
        dbHelper = new DatabaseHelper(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idStr = etId.getText().toString().trim();
                String name = etName.getText().toString().trim();
                String desc = etDesc.getText().toString().trim();

                if (idStr.isEmpty() || name.isEmpty() || desc.isEmpty()) {
                    Toast.makeText(UpdateServiceActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    int id = Integer.parseInt(idStr);
                    boolean isUpdated = dbHelper.updateService(id, name, desc);
                    if (isUpdated) {
                        Toast.makeText(UpdateServiceActivity.this, "Service updated successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(UpdateServiceActivity.this, "Update failed. Check Service ID", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
