package sg.edu.rp.c346.id19018582.reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplay;
    EditText etName, etMobile, etPax;
    CheckBox cb;
    DatePicker dp;
    TimePicker tp;
    Button btnReserve, btnReset;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.textViewDisplay);
        cb = findViewById(R.id.checkBox);
        dp = findViewById(R.id.datePickerR);
        tp = findViewById(R.id.timePickerR);
        btnReserve = findViewById(R.id.buttonReserve);
        btnReset = findViewById(R.id.buttonReset);
        etName = findViewById(R.id.editTextName);
        etMobile = findViewById(R.id.editTextMobile);
        etPax = findViewById(R.id.editTextPax);


        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().trim().length() == 0){
                    Toast.makeText(MainActivity.this, "Please enter your Name.", Toast.LENGTH_LONG).show();
                }
                else if(etMobile.getText().toString().trim().length() == 0){
                    Toast.makeText(MainActivity.this, "Please enter your Mobile Number..", Toast.LENGTH_LONG).show();
                }
                else if(etPax.getText().toString().trim().length() == 0){
                    Toast.makeText(MainActivity.this, "Please enter the number of Pax.", Toast.LENGTH_LONG).show();
                }
                else{
                    if(cb.isChecked()){
                        tvDisplay.setText("Hi " + etName.getText() + "! You have successfully reserved a SMOKING table for " + etPax.getText()
                                + " pax on " + dp.getDayOfMonth() + "/" + (dp.getMonth() + 1) + "/" + dp.getYear() + " at " +
                                tp.getCurrentHour() + ":" + tp.getCurrentMinute() + "hour");
                    }
                    else{
                        tvDisplay.setText("Hi " + etName.getText() + "! You have successfully reserved a NON-SMOKING table for " + etPax.getText()
                                + " pax on " + dp.getDayOfMonth() + "/" + (dp.getMonth() + 1) + "/" + dp.getYear() + " at " +
                                tp.getCurrentHour() + ":" + tp.getCurrentMinute() + "hour.");
                    }
                }

            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(tp.getCurrentHour() < 8 || tp.getCurrentHour() > 20.59){
                    if(tp.getCurrentHour() < 8){
                        tp.setCurrentHour(8);
                        tp.setCurrentMinute(0);
                    }
                    else{
                        tp.setCurrentHour(20);
                        tp.setCurrentMinute(59);
                    }
                    Toast.makeText(MainActivity.this, "The reservation hour is only from 8am to 8:59pm", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDisplay.setText("");
                cb.setChecked(false);
                etName.setText("");
                etMobile.setText("");
                etPax.setText("");
                dp.updateDate(2020, 5, 1);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
            }
        });

    }
}
