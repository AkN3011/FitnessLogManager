package com.example.mylogs;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Calories#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calories extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Calories() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Calories.
     */
    // TODO: Rename and change types and number of parameters
    public static Calories newInstance(String param1, String param2) {
        Calories fragment = new Calories();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

                View view = inflater.inflate(R.layout.fragment_calories, container, false);

                setCaloriesGoal(view);

                return view;
    }


    public void setCaloriesGoal(View view){

        EditText text = view.findViewById(R.id.editTextText_cal);

        Button saveBtn = view.findViewById(R.id.calBtn);
        TextView toSaveText = view.findViewById(R.id.textViewToSave);


        Fragment calories = new CaloriesProgress();



        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String CalorieInput = text.getText().toString();
                toSaveText.setText(CalorieInput);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        if(saveBtn != null) {
            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String calorieInput = text.getText().toString();



                    Bundle exercise_list_bundle = new Bundle();
                    exercise_list_bundle.putString("Calorie",calorieInput);
                    calories.setArguments(exercise_list_bundle);
                    getParentFragmentManager().setFragmentResult("toSave",exercise_list_bundle);

                    @SuppressLint("CommitTransaction") FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainerView, calories).commit();


                }
            });
        }


    }
}