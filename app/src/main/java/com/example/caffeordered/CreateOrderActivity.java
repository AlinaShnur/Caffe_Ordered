package com.example.caffeordered;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {

//    будет изменяться от имени клиента
    private TextView textViewHello;
//    что добавить в ваш drink
    private TextView textViewAdditions;
//    тк заказ будет фоормироваться в зависимости от выбранного
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
//    в зависимости от напитка будет меняться список
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;
//для интента и формирования текста приветствия
    private String name;
    private String password;

//будет хранить название выбранного напитка
    private String drink;
//для формирования списка добавок
    private StringBuilder builderAdditions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

//будем получать из интента данные ИМЯ ПАРОЛ
//проверка на наличие таких ключей
        Intent intent = getIntent();
//получить значения для поля с приветствием
// в случае если интент не содержит значений
// имеет ли интент данные ключи
// если нет данных, то используются дефолтные из строковых ресурсов
        if(intent.hasExtra("name") && intent.hasExtra("password")){
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        } else { // иначе присвоить знаяение по умолчанию
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }

        drink = getString(R.string.tea);
//инициализация переменных findViewById
//получить доступ к элементам макета, присвоив
        textViewHello = findViewById(R.id.textViewHello);
//  будет формироваться в зависимости от имени пользователя
//  получать имя и пароль из интента

//  сформировать текст привтствия от имени пользователя из строковых ресурсов
        String hello = String.format(getString(R.string.hello_user), name);
        textViewHello.setText(hello);

        textViewAdditions = findViewById(R.id.textViewAdditions);

        String additions = String.format(getString(R.string.additions), drink);
        textViewAdditions.setText(additions);

        checkBoxMilk = findViewById(R.id.checkboxMilk);
        checkBoxSugar = findViewById(R.id.checkboxSugar);
        checkBoxLemon = findViewById(R.id.checkboxLemon);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);
        builderAdditions = new StringBuilder();
    }

    public void onClickChangeDrink(View view) {
// при клике на кнопку передаются данные с этой кнопки
        RadioButton button = (RadioButton) view;
// получить id кнопки
        int id = button.getId();
        if(id == R.id.radioButtonTea){
            drink = getString(R.string.tea);
//  если выбран чай, то не отображать спиннер вс кофе
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffee.setVisibility(View.INVISIBLE);
// если выбран чай , то лимон виден
            checkBoxLemon.setVisibility(View.VISIBLE);
        }else if (id == R.id.radioButtonCoffee){
            drink = getString(R.string.coffee);
//  если выбран кофе, то не отображать спиннер с чаем
            spinnerTea.setVisibility(View.INVISIBLE);
            spinnerCoffee.setVisibility(View.VISIBLE);
//  если выбран кофе, то лимон не виден
            checkBoxLemon.setVisibility(View.INVISIBLE);
        }
//  что добавить в ваш %напиток
        String additions = String.format(getString(R.string.additions), drink);
        textViewAdditions.setText(additions);
    }

    public void onClickSendOrder(View view) {
// если билдер уже содержал какие-нибудь занчения, то нужно его очистить
        builderAdditions.setLength(0);
// пробежаться по всем чекбоксам и посмоотреть заполнены ли
        if(checkBoxMilk.isChecked()){
            builderAdditions.append(getString(R.string.milk)).append(" ");
        }
        if(checkBoxSugar.isChecked()){
            builderAdditions.append(getString(R.string.sugar)).append(" ");
        }
// лимон проверяем только в том случае, когда выбранный напиток - чай
        if(checkBoxLemon.isChecked() && drink.equals(getString(R.string.tea))){
            builderAdditions.append(getString(R.string.lemon)).append(" ");
        }
        String optionOfDrink = "";
// если напиток является чаем
        if(drink.equals(getString(R.string.tea))){
            optionOfDrink = spinnerTea.getSelectedItem().toString();
        }else {
            optionOfDrink = spinnerCoffee.getSelectedItem().toString();
        }
        String order = String.format(getString(R.string.order), name, password, drink, optionOfDrink);
// строка с добавками
        String additions ;
        if(builderAdditions.length() > 0 ) {
            additions = getString(R.string.need_additions) + builderAdditions.toString();
        }else {
            additions = "";
        }
        String fullOrder = order + additions;

        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("order", fullOrder);
        startActivity(intent);
    }
}