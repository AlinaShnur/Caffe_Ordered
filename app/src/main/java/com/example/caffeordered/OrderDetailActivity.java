package com.example.caffeordered;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.ims.ImsReasonInfo;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {

    private TextView textViewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        textViewOrder = findViewById(R.id.textViewOrder);

// получить заказ из предыдущей активности
        Intent intent = getIntent();
// проверить содержит ли ключ "order"
        if(intent.hasExtra("order")){
            String order = intent.getStringExtra("order");
            textViewOrder.setText(order);
        }else {
            Intent backToLogin = new Intent(this, LoginActivity.class);
            startActivity(backToLogin);
        }
    }
}