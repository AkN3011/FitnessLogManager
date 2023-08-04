package com.example.mylogs;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SaveSchedule#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SaveSchedule extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SaveSchedule() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SaveSchedule.
     */
    // TODO: Rename and change types and number of parameters
    public static SaveSchedule newInstance(String param1, String param2) {
        SaveSchedule fragment = new SaveSchedule();
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
        View view = inflater.inflate(R.layout.fragment_save_schedule, container, false);
        getParentFragmentManager().setFragmentResultListener("toSave",this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                ArrayList<String> list = result.getStringArrayList("Enlist");

                @SuppressLint({"MissingInflatedId", "LocalSuppress"})
                ListView exercise_ListView = view.findViewById(R.id.toSavelist);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1,list);
                exercise_ListView.setAdapter(arrayAdapter);


            }
        });

        SaveBtn(view);

        return view;
    }


    public void SaveBtn(View view) {
        Button saveBtn = view.findViewById(R.id.SaveBtn);
        CheckBox checkBox_monday = view.findViewById(R.id.checkBox_monday);
        CheckBox checkBox_tuesday = view.findViewById(R.id.checkBox_tuesday);
        CheckBox checkBox_wednesday = view.findViewById(R.id.checkBox_wednesday);
        CheckBox checkBox_thursday = view.findViewById(R.id.checkBox_thursday);
        CheckBox checkBox_friday = view.findViewById(R.id.checkBox_friday);
        CheckBox checkBox_saturday = view.findViewById(R.id.checkBox_saturday);
        CheckBox checkBox_sunday = view.findViewById(R.id.checkBox_sunday);

        Fragment calender_fragment =  new Calender();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox_monday.isChecked()) {

                    ListView listViewSunday = view.findViewById(R.id.toSavelist);

                    ArrayAdapter<String> adapter = null;
                    ArrayList<String> monday_exerciseList = new ArrayList<>();

                    if(listViewSunday != null){
                        adapter =     (ArrayAdapter<String>) listViewSunday.getAdapter();

                    }

                    if(adapter != null) {

                        for (int i = 0; i < adapter.getCount(); i++) {
                            monday_exerciseList.add((String) adapter.getItem(i));



                        }
                    }

                    Bundle exercise_list_bundle = new Bundle();
                    exercise_list_bundle.putStringArrayList("Mondaylist",monday_exerciseList);
                    calender_fragment.setArguments(exercise_list_bundle);
                    getParentFragmentManager().setFragmentResult("toSaveMon",exercise_list_bundle);

                    @SuppressLint("CommitTransaction") FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainerView,calender_fragment).commit();
                } else if(checkBox_tuesday.isChecked()) {

                    ListView listViewSunday = view.findViewById(R.id.toSavelist);

                    ArrayAdapter<String> adapter = null;
                    ArrayList<String> tuesday_exerciseList = new ArrayList<>();

                    if(listViewSunday != null){
                        adapter =     (ArrayAdapter<String>) listViewSunday.getAdapter();

                    }

                    if(adapter != null) {

                        for (int i = 0; i < adapter.getCount(); i++) {
                            tuesday_exerciseList.add((String) adapter.getItem(i));



                        }
                    }

                    Bundle exercise_list_bundle = new Bundle();
                    exercise_list_bundle.putStringArrayList("Tuesdaylist",tuesday_exerciseList);
                    calender_fragment.setArguments(exercise_list_bundle);
                    getParentFragmentManager().setFragmentResult("toSaveTues",exercise_list_bundle);

                    @SuppressLint("CommitTransaction") FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainerView,calender_fragment).commit();
                    
                } else if (checkBox_wednesday.isChecked()) {

                    ListView listViewSunday = view.findViewById(R.id.toSavelist);

                    ArrayAdapter<String> adapter = null;
                    ArrayList<String> wednesday_exerciseList = new ArrayList<>();

                    if(listViewSunday != null){
                        adapter =     (ArrayAdapter<String>) listViewSunday.getAdapter();

                    }

                    if(adapter != null) {

                        for (int i = 0; i < adapter.getCount(); i++) {
                            wednesday_exerciseList.add((String) adapter.getItem(i));



                        }
                    }

                    Bundle exercise_list_bundle = new Bundle();
                    exercise_list_bundle.putStringArrayList("Wednesdaylist",wednesday_exerciseList);
                    calender_fragment.setArguments(exercise_list_bundle);
                    getParentFragmentManager().setFragmentResult("toSaveWed",exercise_list_bundle);

                    @SuppressLint("CommitTransaction") FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainerView,calender_fragment).commit();
                    
                } else if (checkBox_thursday.isChecked()) {

                    ListView listViewSunday = view.findViewById(R.id.toSavelist);

                    ArrayAdapter<String> adapter = null;
                    ArrayList<String> thursday_exerciseList = new ArrayList<>();

                    if(listViewSunday != null){
                        adapter =     (ArrayAdapter<String>) listViewSunday.getAdapter();

                    }

                    if(adapter != null) {

                        for (int i = 0; i < adapter.getCount(); i++) {
                            thursday_exerciseList.add((String) adapter.getItem(i));



                        }
                    }

                    Bundle exercise_list_bundle = new Bundle();
                    exercise_list_bundle.putStringArrayList("Thursdaylist",thursday_exerciseList);
                    calender_fragment.setArguments(exercise_list_bundle);
                    getParentFragmentManager().setFragmentResult("toSaveThurs",exercise_list_bundle);

                    @SuppressLint("CommitTransaction") FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainerView,calender_fragment).commit();
                    
                } else if (checkBox_friday.isChecked()) {

                    ListView listViewSunday = view.findViewById(R.id.toSavelist);

                    ArrayAdapter<String> adapter = null;
                    ArrayList<String> friday_exerciseList = new ArrayList<>();

                    if(listViewSunday != null){
                        adapter =     (ArrayAdapter<String>) listViewSunday.getAdapter();

                    }

                    if(adapter != null) {

                        for (int i = 0; i < adapter.getCount(); i++) {
                            friday_exerciseList.add((String) adapter.getItem(i));



                        }
                    }

                    Bundle exercise_list_bundle = new Bundle();
                    exercise_list_bundle.putStringArrayList("Fridaylist",friday_exerciseList);
                    calender_fragment.setArguments(exercise_list_bundle);
                    getParentFragmentManager().setFragmentResult("toSaveFri",exercise_list_bundle);

                    @SuppressLint("CommitTransaction") FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainerView,calender_fragment).commit();
                    
                } else if (checkBox_saturday.isChecked()) {

                    ListView listViewSunday = view.findViewById(R.id.toSavelist);

                    ArrayAdapter<String> adapter = null;
                    ArrayList<String> saturday_exerciseList = new ArrayList<>();

                    if(listViewSunday != null){
                        adapter =     (ArrayAdapter<String>) listViewSunday.getAdapter();

                    }

                    if(adapter != null) {

                        for (int i = 0; i < adapter.getCount(); i++) {
                            saturday_exerciseList.add((String) adapter.getItem(i));



                        }
                    }

                    Bundle exercise_list_bundle = new Bundle();
                    exercise_list_bundle.putStringArrayList("Saturdaylist",saturday_exerciseList);
                    calender_fragment.setArguments(exercise_list_bundle);
                    getParentFragmentManager().setFragmentResult("toSaveSat",exercise_list_bundle);

                    @SuppressLint("CommitTransaction") FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainerView,calender_fragment).commit();
                    
                } else if (checkBox_sunday.isChecked()) {
                    ListView listViewSunday = view.findViewById(R.id.toSavelist);

                    ArrayAdapter<String> adapter = null;
                    ArrayList<String> sunday_exerciseList = new ArrayList<>();

                    if(listViewSunday != null){
                       adapter =     (ArrayAdapter<String>) listViewSunday.getAdapter();

                    }

                    if(adapter != null) {

                        for (int i = 0; i < adapter.getCount(); i++) {
                           sunday_exerciseList.add((String) adapter.getItem(i));



                        }
                    }

                    Bundle exercise_list_bundle = new Bundle();
                    exercise_list_bundle.putStringArrayList("Sundaylist",sunday_exerciseList);
                    calender_fragment.setArguments(exercise_list_bundle);
                    getParentFragmentManager().setFragmentResult("toSaveSun",exercise_list_bundle);

                    @SuppressLint("CommitTransaction") FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainerView,calender_fragment).commit();


                }else {
                    Log.d("CHECKBOX", "Not a valid choice");
                }
            }
        });
    }


}