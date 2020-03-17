package com.example.latihan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText edPanjang, edLebar, edTinggi;
    private Button btnHitung;
    private TextView vHasil;
    private static final String STATE_HASIL = "state_hasil";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edPanjang = findViewById(R.id.edit_panjang);
        edLebar = findViewById(R.id.edit_lebar);
        edTinggi = findViewById(R.id.edit_tinggi);
        btnHitung = findViewById(R.id.btn_hitung);
        vHasil = findViewById(R.id.v_hasil);
        btnHitung.setOnClickListener(this);

        if (savedInstanceState != null) 
        {
            String result = savedInstanceState.getString(STATE_HASIL);
            vHasil.setText(result);
        }
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.btn_hitung)
        {
            String inputPanjang = edPanjang.getText().toString().trim();
            String inputLebar = edLebar.getText().toString().trim();
            String inputTinggi = edTinggi.getText().toString().trim();

            boolean isEmpetyFields = false;
            boolean isInvalidDouble = false;

            Double panjang = toDouble(inputPanjang);
            Double lebar = toDouble(inputLebar);
            Double tinggi = toDouble(inputTinggi);

            if (TextUtils.isEmpty(inputPanjang))
            {
                isEmpetyFields = true;
                edPanjang.setError("Field ini tidak boleh kosaong!");
            }

            if (TextUtils.isEmpty(inputLebar))
            {
                isEmpetyFields = true;
                edLebar.setError("Field ini tidak boleh kosong!");
            }

            if (TextUtils.isEmpty(inputTinggi))
            {
                isEmpetyFields = true;
                edTinggi.setError("Field ini tidak boleh kosong!");
            }

            if (panjang == null)
            {
                isInvalidDouble = true;
                edPanjang.setError("Field ini harus berupa nomer yang valid");
            }

            if (lebar == null)
            {
                isInvalidDouble = true;
                edLebar.setError("Field ini harus berupa nomer yang valid");
            }

            if (tinggi == null)
            {
                isInvalidDouble = true;
                edTinggi.setError("Field ini harus berupa nomer yang valid");
            }

            if (!isEmpetyFields && !isInvalidDouble)
            {
                double hasil = panjang * lebar * tinggi;
                vHasil.setText(String.valueOf(hasil));
            }


        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_HASIL, vHasil.getText().toString());
    }

    private Double toDouble(String str)
    {
        try
        {
            return Double.valueOf(str);
        }

        catch (NumberFormatException nfe)
        {
            return null;
        }
    }

}
