package com.example.admin.swipe;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;



/**
 * Created by admin on 20.08.17.
 */

public class FragmentOne {

    private EditText milageEditText;
    private Button calcButton;
    private ImageView flag;
    public AppCompatActivity activity;
    private TextView showMilageTextView;
    private Switch milageTypeSwitch;
    private TextView SwithResultText;
    private final String format = "%.2f";



    public void init(View v)
            {
        calcButton = v.findViewById(R.id.calcButtonId);
        showMilageTextView = v.findViewById(R.id.showResultTextViewId);
        SwithResultText = v.findViewById(R.id.SwitchTextId);
        milageTypeSwitch = v.findViewById(R.id.milageTypeSwitch);
        flag = v.findViewById(R.id.flagImageId);
        milageEditText = v.findViewById(R.id.editTextId);
        milageEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);


        SharedPreferences switchPrefs = activity.getSharedPreferences("com.example.admin.swipe", activity.MODE_PRIVATE);
                milageTypeSwitch.setChecked(switchPrefs.getBoolean("switchPosition", true));


                if (milageTypeSwitch.isChecked()) {
                    SwithResultText.setText("KM/L");
                    flag.setBackgroundResource(R.drawable.flag_jp);
                } else {
                    SwithResultText.setText("MPG");
                    flag.setBackgroundResource(R.drawable.flag_us);
                }

        milageTypeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    SwithResultText.setText("KM/L");
                    flag.setBackgroundResource(R.drawable.flag_jp);

                    SharedPreferences.Editor editor = activity.getSharedPreferences("com.example.admin.swipe", activity.MODE_PRIVATE).edit();
                    editor.putBoolean("switchPosition", true);
                    editor.commit();

                } else {
                    SwithResultText.setText("MPG");
                    flag.setBackgroundResource(R.drawable.flag_us);

                    SharedPreferences.Editor editor = activity.getSharedPreferences("com.example.admin.swipe", activity.MODE_PRIVATE).edit();
                    editor.putBoolean("switchPosition", false);
                    editor.commit();

                }
            }
        });

        //set up buttons (event listeners)
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //convert from MPG function

                String editTextVal = milageEditText.getText().toString();

                if (editTextVal.isEmpty()) {

                    Toast.makeText(v.getContext(), R.string.inputDataToast, Toast.LENGTH_SHORT).show();
                } else {
                    if (milageTypeSwitch.isChecked()) {
                        //KM/L (JDM)
                        double DoubleEditText = Double.parseDouble(editTextVal);
                        convertFromJdm(DoubleEditText);

                        double convertedVal = convertFromJdm(DoubleEditText);
                        String stringResult = String.format(format, convertedVal);//String.valueOf(round.format(convertedVal));
                        showMilageTextView.setText(stringResult + " " + activity.getString(R.string.resultKM_L) );

                    } else {
                        //MPG
                        double DoubleEditText = Double.parseDouble(editTextVal);
                        convertFromMpg(DoubleEditText);

                        double convertedVal = convertFromMpg(DoubleEditText);
                        String stringResult = String.format(format, convertedVal);
                        showMilageTextView.setText(stringResult + " " + activity.getString(R.string.resultKM_L) );
                    }

                    //keyboard closing
                    InputMethodManager inputManager = (InputMethodManager)activity.getSystemService(activity.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);


                }
            }
        });


    }

    public double convertFromMpg(double mpgVal) {
        double resultMpg;
        resultMpg = (235.215 / mpgVal);

        return resultMpg;
    }

    public double convertFromJdm(double jdmVal) {
        double resultJdm;
        resultJdm = (100 / jdmVal);

        return resultJdm;
    }


}
