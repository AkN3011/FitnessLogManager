package com.example.mylogs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Nutrients#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Nutrients extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Nutrients() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Nutrients.
     */
    // TODO: Rename and change types and number of parameters
    public static Nutrients newInstance(String param1, String param2) {
        Nutrients fragment = new Nutrients();
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
        View view = inflater.inflate(R.layout.fragment_nutrients, container, false);

        Retrieve_Calories(view);
        Retrieve_itemName(view);
        Retrieve_Carbohydrates(view);
        Retrieve_Protein(view);
        Retrieve_fats(view);

        return view;
    }

    public  void Retrieve_Calories(View view){
        TextView textView_calories = view.findViewById(R.id.textView_cal);

        getParentFragmentManager().setFragmentResultListener("energy", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String Calorie = result.getString("Calorie");
                if(textView_calories != null) {
                    textView_calories.setText(Calorie);
                }
            }
        });


    }

    public  void Retrieve_Carbohydrates(View view){
        TextView textView_carb = view.findViewById(R.id.textView_carb);

        getParentFragmentManager().setFragmentResultListener("carbohydrateEnergy", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String Calorie = result.getString("Carbs");
                if(textView_carb != null) {
                    textView_carb.setText(Calorie);
                }
            }
        });


    }

    public  void Retrieve_Protein(View view){
        TextView textView_protein = view.findViewById(R.id.textView_prot);

        getParentFragmentManager().setFragmentResultListener("proteinEnergy", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String Calorie = result.getString("Protein");
                if(textView_protein != null) {
                    textView_protein.setText(Calorie);
                }
            }
        });


    }


    public  void Retrieve_fats(View view){
        TextView textView_fats = view.findViewById(R.id.textView_fat);

        getParentFragmentManager().setFragmentResultListener("fatEnergy", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String Calorie = result.getString("Fats");
                if(textView_fats != null) {
                    textView_fats.setText(Calorie);
                }
            }
        });


    }
    public  void Retrieve_itemName(View view){
        TextView textView_itemName = view.findViewById(R.id.textView_foodname);

        getParentFragmentManager().setFragmentResultListener("itemName", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String Calorie = result.getString("Name");
                if(textView_itemName != null) {
                    textView_itemName.setText(Calorie);
                }
            }
        });


    }



}