package com.example.mooovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Fragment {

    public static Register newInstance() {

        Bundle args = new Bundle();

        Register fragment = new Register();
        fragment.setArguments(args);
        return fragment;
    }

    EditText et_name;
    EditText et_email;
    EditText et_password;
    EditText et_password_repeat;

    TextView tv_register;
    TextView tv_auth;

    class DoRegister extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            Api api = new Api();

            return api.register(strings[0], strings[1], strings[2]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s == null) {
                Toast.makeText(getContext(), "Произошла ошибка", Toast.LENGTH_LONG).show();
            }   else {
                Cache cache = new Cache(getContext());
                cache.writeToken(s);

                getActivity().recreate();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_register, container, false);

        initViews(v);

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et_name.getText().toString();
                String email = et_email.getText().toString();

                String password = et_password.getText().toString();
                String password_r = et_password_repeat.getText().toString();

                if(password.equals(password_r)) {

                    new DoRegister().execute(username, password, email);

                }   else {
                    Toast.makeText(getContext(), "Пароли не совпадают", Toast.LENGTH_LONG).show();
                }
            }
        });

        tv_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Login.class);
                getContext().startActivity(intent);
            }
        });

        return v;
    }

    public void initViews(View v) {
        et_name = v.findViewById(R.id.et_name);
        et_email = v.findViewById(R.id.et_email);
        et_password = v.findViewById(R.id.et_password);
        et_password_repeat = v.findViewById(R.id.et_password_repeat);
        tv_register = v.findViewById(R.id.tv_register);
        tv_auth = v.findViewById(R.id.tv_auth);
    }
}