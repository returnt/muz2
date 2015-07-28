package com.example.android.astam.box_music2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import libraryjava.HttpRequest;


public class Register extends ActionBarActivity {

    Button btnRegister;
    EditText etName, etEmail, etPassword, etCoPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etCoPassword = (EditText) findViewById(R.id.etCoPassword);

        final HttpRequest req = new HttpRequest();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((etName.getText().toString() == "" || etEmail.getText().toString() == "" || etPassword.getText().toString() == "" || etCoPassword.getText().toString() == "")) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Fill in all the fields",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();
            }else{
                    Map<String, String> map = new HashMap<String, String>();

                    map.put("name", ((EditText) findViewById(R.id.etName)).getText().toString());

                    map.put("email", ((EditText) findViewById(R.id.etEmail)).getText().toString());

                    map.put("password", ((EditText) findViewById(R.id.etPassword)).getText().toString());

                    map.put("confirm_password", ((EditText) findViewById(R.id.etCoPassword)).getText().toString());

                    try {
                        if (!req.makePostRequestGetJsonArray("http://muz.returnt.ru/main/auth", map).isNull("user")) {
                            Log.d("wwww", req.makePostRequestGetJsonArray("http://muz.returnt.ru/main/auth", map).getJSONArray("user").getJSONObject(0).getString("1") + "");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }

    });

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
    }
}


