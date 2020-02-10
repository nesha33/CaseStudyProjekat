package com.example.neda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registracija extends AppCompatActivity {
    private EditText username, password;
    private Button dugme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        dugme = findViewById(R.id.dugme);

        dugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registruj();
            }
        });
    }


    public void Registruj() {
        String url = "https://nedakalendar.000webhostapp.com/RegisterNeda.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.trim().equals("0")) {
                    Toast.makeText(Registracija.this, "Doslo je do greske kod Servera", Toast.LENGTH_SHORT).show();
                } else if (response.trim().equals("1")) {
                    Toast.makeText(Registracija.this, "Vec postoji taj username u bazi", Toast.LENGTH_SHORT).show();
                } else if (response.trim().equals("2")) {
                    Toast.makeText(Registracija.this, "Uspjesna vam je registracija!", Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Registracija.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("UserName", username.getText().toString().trim());
                params.put("Password", password.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
