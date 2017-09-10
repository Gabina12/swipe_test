package com.example.admin.swipe;

import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by vako on 20/08/2017.
 */

public class FragmentTwo {

    private EditText tempEditText;
    private Button celButton;
    private TextView showTempTextView;
    public AppCompatActivity activity;
    DecimalFormat round = new DecimalFormat("0.0");



    public void init(View v) {

        tempEditText = (EditText) v.findViewById(R.id.editText);
        celButton = (Button) v.findViewById(R.id.celsiusButtonId);
        showTempTextView = (TextView) v.findViewById(R.id.showResultTextViewId);

        tempEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);

//set up our buttons (event listeners)

        celButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //call convert to celsius function
                String editTextVal = tempEditText.getText().toString();

                if (editTextVal.isEmpty()) {

                    //ტოსტი
                    Toast.makeText(v.getContext(), R.string.inputTempToast, Toast.LENGTH_LONG).show();
                    

                } else {
                    double doubleEditText = Double.parseDouble(editTextVal);

                    double convertedVal = convertToCelsius(doubleEditText);

                    String stringResult = String.valueOf(round.format(convertedVal));

                    showTempTextView.setText(stringResult + " " + activity.getString(R.string.celsius) );

                }
                InputMethodManager inputManager = (InputMethodManager)activity.getSystemService(activity.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        }
    //ფუნქცია
    public double convertToCelsius(double TempInput) {
        double result;

        result = (TempInput - 32) * 5/9 ;

        return result;
    }


}
