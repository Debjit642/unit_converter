package com.example.uc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uc.R;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner fromSpinner;
    private Spinner toSpinner;
    private EditText fromValue;
    private TextView toValue;
    private Button convertButton;

    private final String[] units = {"Meters", "Feet", "Inches", "Centimeters"};
    private final double[] conversionFactors = {1, 3.281, 39.37, 100};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromSpinner = findViewById(R.id.from_spinner);
        toSpinner = findViewById(R.id.to_spinner);
        fromValue = findViewById(R.id.from_value);
        toValue = findViewById(R.id.to_value);
        convertButton = findViewById(R.id.convert_button);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        fromSpinner.setOnItemSelectedListener(this);
        toSpinner.setOnItemSelectedListener(this);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
            }});
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Update the conversion factor based on the selected units
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }

    private void convert() {
        // Get the values from the input fields
        double fromValueDouble = Double.parseDouble(fromValue.getText().toString());

        // Get the selected units
        int fromUnitIndex = fromSpinner.getSelectedItemPosition();
        int toUnitIndex = toSpinner.getSelectedItemPosition();

        // Convert the value to the desired unit
        double toValueDouble = fromValueDouble * conversionFactors[fromUnitIndex] / conversionFactors[toUnitIndex];

        // Display the converted value
        toValue.setText(String.valueOf(toValueDouble)); // Add semicolon
    }
}