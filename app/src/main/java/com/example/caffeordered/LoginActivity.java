package com.example.caffeordered;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //для доступа к полям
    private EditText editTextName;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    public void onClickCreateOrder(View view) {
        //переменные для работы с полями
        String name = editTextName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
//если имя НЕ пустое и пароль НЕ пустой
        if (!name.isEmpty() && !password.isEmpty()) {
// передаём себя в цель - класс со следующей активностью
            Intent intent = new Intent(this, CreateOrderActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("password", password);
// запуск активности
            startActivity(intent);
        } else {
//1 парметр на какой объект будет тост вызываться
//2 параметр - что будет выводиться String
//3 длительность уведомления
            Toast.makeText(this, R.string.warning_fill_fields, Toast.LENGTH_SHORT).show();
        }
    }
}