package com.example.neda;

import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class meni extends AppCompatActivity {
    EditText trajanjeCiklusa, trajanjeMenstruacije;
    DatePicker dateP;
    Button unosPodataka;
    int trajanjeM, trajanjeMC;
    TextView sledM, sledO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meni);

        unosPodataka = findViewById(R.id.dugmeUnosPodataka);
        unosPodataka.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                trajanjeCiklusa = findViewById(R.id.trajanjeMC);
                trajanjeMenstruacije = findViewById(R.id.trajanjeM);
                dateP = findViewById(R.id.datumPoslednje);
                if (trajanjeCiklusa.getText().toString().equals("") || trajanjeMenstruacije.getText().toString().equals("")) {
                    Toast.makeText(meni.this, "Morate unijeti sva polja.", Toast.LENGTH_SHORT).show();
                } else {

                    Calendar pocetakSledece = Calendar.getInstance();
                    pocetakSledece.set(dateP.getYear(), dateP.getMonth(), dateP.getDayOfMonth());
                    trajanjeMC = Integer.parseInt(trajanjeCiklusa.getText().toString());
                    pocetakSledece.add(Calendar.DATE, trajanjeMC);
                    String datumPocetka = pocetakSledece.getTime().toString();

                    Calendar krajSledece = Calendar.getInstance();
                    krajSledece.set(dateP.getYear(), dateP.getMonth(), dateP.getDayOfMonth());
                    trajanjeM = Integer.parseInt(trajanjeMenstruacije.getText().toString());
                    krajSledece.add(Calendar.DATE, trajanjeMC + trajanjeM);
                    String datumKraja = krajSledece.getTime().toString();

                    Calendar sledOvulacija = Calendar.getInstance();
                    sledOvulacija.set(dateP.getYear(), dateP.getMonth(), dateP.getDayOfMonth());
                    sledOvulacija.add(Calendar.DATE, trajanjeMC + trajanjeMC - 14);
                    String sledecaOvulacija = sledOvulacija.getTime().toString();

                    setContentView(R.layout.rezultat);
                    sledM = findViewById(R.id.sledecaMenstruacija);
                    sledM.setText(datumPocetka.substring(3, 10).trim() + "." + datumPocetka.substring(datumPocetka.lastIndexOf(' ')).trim() + ". - " + datumKraja.substring(3, 10).trim() + "." + datumKraja.substring(datumKraja.lastIndexOf(" ")).trim() + ".");

                    sledO = findViewById(R.id.sledecaOvulacija);
                    sledO.setText(sledecaOvulacija.substring(3, 10).trim() + "." + sledecaOvulacija.substring(sledecaOvulacija.lastIndexOf(" ")).trim() + ".");


                }
            }

        });
    }
}
