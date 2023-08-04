package com.example.mylogs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeDashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeDashboard extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeDashboard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeDashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeDashboard newInstance(String param1, String param2) {
        HomeDashboard fragment = new HomeDashboard();
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
        View view = inflater.inflate(R.layout.fragment_home_dashboard, container, false);
        trackcalorie_Navigation(view);
        nutrition_Navigation(view);
        calculate_Navigation(view);
        calender_Navigation(view);
        logout_Navigation(view);

        return view;
    }


    public void trackcalorie_Navigation(View view){

        CardView cardView_trackCal = view.findViewById(R.id.CardView_TrackCalories);

        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Fragment fragmentCalorieProgess = new CaloriesProgress();

        cardView_trackCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, fragmentCalorieProgess, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // Name can be null
                        .commit();

            }
        });







    }
    public void nutrition_Navigation(View view){

        CardView cardView_nutrition = view.findViewById(R.id.CardView_Nutrition);
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Fragment fragmentNutrition = new Nutrition();
        //welcome_text.setText(" ");
        cardView_nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, fragmentNutrition)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // Name can be null
                        .commit();

            }
        });






    }
    public void calculate_Navigation(View view){
        CardView cardView_calculate = view.findViewById(R.id.CardView_Calculate);
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Fragment fragmentCalculate = new BMRpage();
        //welcome_text.setText(" ");

        cardView_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView,fragmentCalculate )
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // Name can be null
                        .commit();

            }
        });





    }
    public void calender_Navigation(View view){

        CardView cardView_calender = view.findViewById(R.id.CardView_Calender);
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Fragment fragmentCalender = new Calender();
        //welcome_text.setText(" ");

        cardView_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, fragmentCalender)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // Name can be null
                        .commit();

            }
        });



    }
    public void logout_Navigation(View view){

        CardView cardView_logout = view.findViewById(R.id.CardView_Logout);

        cardView_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent login_page = new Intent(getContext(), MainActivity.class);
                startActivity(login_page);
             getActivity().finish();

            }
        });



    }
}