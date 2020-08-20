package com.example.mooovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText et_email;
    EditText et_password;

    TextView tv_join;
    TextView tv_register;

    class DoLogin extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            Api api = new Api();
            return api.auth(strings[0], strings[1]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s == null) Toast.makeText(Login.this, "Неверный логин или пароль", Toast.LENGTH_LONG).show();
            else {
                Cache cache = new Cache(Login.this);
                cache.writeToken(s);
                Toast.makeText(Login.this, "Успешная авторизация", Toast.LENGTH_LONG).show();
                finish();
            }


        }
    }

    public void initViews() {
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);

        tv_join = findViewById(R.id.tv_auth);
        tv_register = findViewById(R.id.tv_register);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                new DoLogin().execute(email, password);

            }
        });

    }
}