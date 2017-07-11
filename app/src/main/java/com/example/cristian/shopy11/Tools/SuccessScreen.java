package com.example.cristian.shopy11.Tools;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.cristian.shopy11.R;

public class SuccessScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_screen);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_cash:
                if (checked)
                    Toast.makeText(SuccessScreen.this, "Cash selected", Toast.LENGTH_SHORT).show();

                break;
            case R.id.radio_card:
                if (checked)
                    Toast.makeText(SuccessScreen.this, "Credit card selected", Toast.LENGTH_SHORT).show();

                break;
            case R.id.radio_paypal:
                if (checked)
                    Toast.makeText(SuccessScreen.this, "Paypal selected", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
