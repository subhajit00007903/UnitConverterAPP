package com.example.myapplication;





import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner unitFromSpinner, unitToSpinner;
    private Button convertButton;
    private TextView resultText;


    String[] units = {"Centimeters", "Meters", "Grams", "Kilograms","Pound"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        unitFromSpinner = findViewById(R.id.unitFromSpinner);
        unitToSpinner = findViewById(R.id.unitToSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        unitFromSpinner.setAdapter(adapter);
        unitToSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputValueStr = inputValue.getText().toString().trim();
                if (inputValueStr.isEmpty()) {
                    resultText.setText("Please enter a value.");
                    return ;
                }


                String unitFrom = unitFromSpinner.getSelectedItem().toString();
                String unitTo = unitToSpinner.getSelectedItem().toString();
                double value = Double.parseDouble(inputValue.getText().toString());


                double result = convertUnits(unitFrom, unitTo, value);


                if (result != -1) {
                    resultText.setText(String.format("%.3f", result));
                }

            }
        });
    }

    private double convertUnits(String unitFrom, String unitTo, double value) {


         if ((unitFrom.equals("Centimeters") || unitFrom.equals("Meters")) &&
                (unitTo.equals("Grams") || unitTo.equals("Kilograms"))) {

            resultText.setText("Conversion not possible");
            return -1;
        } else if ((unitFrom.equals("Grams") || unitFrom.equals("Kilograms")) &&
                (unitTo.equals("Centimeters") || unitTo.equals("Meters"))) {

            resultText.setText("Conversion not possible");
            return -1;
        } else if ((unitFrom.equals("Centimeters")||unitFrom.equals("Meters"))&&(unitTo.equals("Pound"))) {
            resultText.setText("Conversion not possible");
            return -1;
        }else if ((unitFrom.equals("Pound")&&(unitTo.equals("Centimeters")  ||  unitTo.equals("Meters")))){
            resultText.setText("Conversion not possible");
            return -1;
        }




        if (unitFrom.equals("Centimeters") && unitTo.equals("Meters")) {
            return value / 100;
        } else if (unitFrom.equals("Meters") && unitTo.equals("Centimeters")) {
            return value * 100;
        } else if (unitFrom.equals("Grams") && unitTo.equals("Kilograms")) {
            return value / 1000;
        } else if (unitFrom.equals("Kilograms") && unitTo.equals("Grams")) {
            return value * 1000;
        }
        else if(unitFrom.equals("Pound") && unitTo.equals("Kilograms")){
            return value*0.45359237;
        }
        else if(unitFrom.equals("Pound")&&unitTo.equals("Grams")){
                return value*453.59237;
        } else if (unitFrom.equals("Grams") && unitTo.equals("Pound")) {
            return value*0.00220462;
        }
        else if (unitFrom.equals("Kilograms") && unitTo.equals("Pound")) {
            return value*2.20462262;
        }


        return value;
    }
}
