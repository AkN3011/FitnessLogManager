package com.example.mylogs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BMRpage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BMRpage extends Fragment {

    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private Button calculateButton;
    private TextView resultTextView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BMRpage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BMRpage.
     */
    // TODO: Rename and change types and number of parameters
    public static BMRpage newInstance(String param1, String param2) {
        BMRpage fragment = new BMRpage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b_m_rpage, container, false);
        Log.d("YourFragment", "onCreateView called");
        setupButtonClick(view);
        return view;
    }





        private void setupButtonClick(View view) {
            Log.d("YourFragment", "setupButtonClick called");
            Button button = view.findViewById(R.id.button3);
            RadioGroup radioGroup = view.findViewById(R.id.radiogroup);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("YourFragment", "Button clicked");
                    int radioButtonId = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = view.findViewById(radioButtonId);

                    String heightFeetString = "";
                    String weightString = "";
                    String heightInchesString = "";
                    String ageString = "";
                    int heightFeet = 0;
                    int heightInches = 0;

                    double weight = 0;
                    int height = 0;
                    int age = 0;
                    int BMR = 0;

                    EditText weightEditText = (EditText) getView().findViewById(R.id.editTextWeight);
                    EditText heightFeetEditText = (EditText) getView().findViewById(R.id.editTextHeightFeet);
                    EditText heightInchesEditText = (EditText) getView().findViewById(R.id.editTextHeightInches);
                    EditText ageEditText = (EditText) getView().findViewById(R.id.editTextAge);


                    if (radioButtonId == R.id.radioButtonMale) {
                        // Male option selected


                        if (weightEditText != null) {
                            weightString = weightEditText.getText().toString();
                           // Log.d("myFragment", "weightstring: " + weightString);
                            weight = Double.parseDouble(weightString) / 2.2;
                        }

                        if (heightFeetEditText != null) {
                            heightFeetString = heightFeetEditText.getText().toString();
                            heightFeet = Integer.parseInt(heightFeetString);
                        }

                        if (heightInchesEditText != null) {
                            heightInchesString = heightInchesEditText.getText().toString();
                            heightInches = Integer.parseInt(heightInchesString);
                        }

                        height = (int) ((heightFeet * 12 + heightInches) * 2.54);
                        Log.d("YourFragment", String.valueOf(height));

                        if (ageEditText != null) {
                            ageString = ageEditText.getText().toString();
                            age = Integer.parseInt(ageString);
                        }

                        BMR = (int) (88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age));


                    } else if (radioButtonId == R.id.radioButtonFemale) {



                        if (weightEditText != null) {
                            weightString = weightEditText.getText().toString();
                            // Log.d("myFragment", "weightstring: " + weightString);
                            weight = Double.parseDouble(weightString) / 2.2;
                        }

                        if (heightFeetEditText != null) {
                            heightFeetString = heightFeetEditText.getText().toString();
                            heightFeet = Integer.parseInt(heightFeetString);
                        }

                        if (heightInchesEditText != null) {
                            heightInchesString = heightInchesEditText.getText().toString();
                            heightInches = Integer.parseInt(heightInchesString);
                        }

                        height = (int) ((heightFeet * 12 + heightInches) * 2.54);


                        if (ageEditText != null) {
                            ageString = ageEditText.getText().toString();
                            age = Integer.parseInt(ageString);
                        }

                        BMR = (int) (447.593  + (9.247  * weight) + (3.098 * height) - (4.330 * age));


                    }

                    TextView resultTextView = (TextView) getView().findViewById(R.id.textView_id);
                    if (resultTextView != null) {

                        resultTextView.setText(String.valueOf("BMR = " + BMR + " Calories/day"));
                    }
                }
            });
        }


}






