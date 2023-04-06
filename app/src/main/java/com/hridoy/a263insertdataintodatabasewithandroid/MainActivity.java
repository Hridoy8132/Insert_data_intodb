package com.hridoy.a263insertdataintodatabasewithandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    EditText EdName, EdPhone, EdAddress, EdEmail;
    Button buttonInsert;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        EdName = findViewById(R.id.EdName);
        EdPhone = findViewById(R.id.Edphone);
        EdAddress = findViewById(R.id.EdAddress);
        EdEmail = findViewById(R.id.EdEmail);
        buttonInsert = findViewById(R.id.buttonInsert);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = EdName.getText().toString();
                String phone = EdPhone.getText().toString();
                String address = EdAddress.getText().toString();
                String email = EdEmail.getText().toString();
                String url = "https://hridoy5765bd.000webhostapp.com/apps/link.php?n=" + name + "&p="
                        + phone + "&a=" + address + "&e=" + email;

                progressBar.setVisibility(View.VISIBLE);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Server Response")
                                .setMessage(response)
                                .show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                if (name.length() > 0) {
                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(stringRequest);

                } else EdName.setError("Input Your Name");


            }
        });

    }

}














