package com.example.cristian.shopy11.Tools;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cristian.shopy11.R;

/**
 * Created by Cristian on 6/6/2017.
 */
public class AlertBox {

    private Context c;
    private int option;

    public AlertBox(Context c, int option) {
        this.c = c;
        this.option = option;

        show();
    }

    public void show(){
        if(option == 1)
            good();
        else if (option == 0)
            bad();
    }

    private void bad(){
        Dialog dialog = new Dialog(c);
        dialog.setTitle("Eroare!!!");
        dialog.setCancelable(true);
        dialog.show();
    }


    private void good(){
        final Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Alegeti modalitatea de plata");
        dialog.setCancelable(true);

        RadioGroup radion_group = (RadioGroup) dialog.findViewById(R.id.group);
        Button ok = (Button)dialog.findViewById(R.id.ok_button);
        Button cancel = (Button)dialog.findViewById(R.id.cancel_button);

        radion_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Check which radio button was clicked

                Toast.makeText(c, String.valueOf(checkedId), Toast.LENGTH_SHORT).show();


                switch(checkedId) {
                    case R.id.radio_cash:
                        Toast.makeText(c, "Cash selected", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.radio_card:

                        Toast.makeText(c, "Credit card selected", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.radio_paypal:

                        Toast.makeText(c, "Paypal selected", Toast.LENGTH_SHORT).show();

                        break;
                }

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, "Ok", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                dialog.cancel();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, "cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                dialog.cancel();

            }
        });


        // now that the dialog is set up, it's time to show it
        dialog.show();
    }
}



