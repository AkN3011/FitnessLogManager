package com.example.mylogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditSchedule#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditSchedule extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditSchedule() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditSchedule.
     */
    // TODO: Rename and change types and number of parameters
    public static EditSchedule newInstance(String param1, String param2) {
        EditSchedule fragment = new EditSchedule();
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
        View view = inflater.inflate(R.layout.fragment_edit_schedule, container, false);
        List<String> list = new ArrayList<>();
        ListView listView = view.findViewById(R.id.excerciselist);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);


        NextBTN(view);
        add_btn(view,adapter,list);

        return view;
    }

    public void NextBTN(View view){


        Button button = view.findViewById(R.id.nextbtn);
        ListView listView = view.findViewById(R.id.excerciselist);

        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v){

                Fragment save_schedule = new SaveSchedule();


                ArrayList<String> exercise_Arraylist = new ArrayList<>();
                ArrayAdapter<String> adapter = null;


                if(listView != null) {
                   adapter = (ArrayAdapter<String>) listView.getAdapter();



                }


             if(adapter != null) {

                 for (int i = 0; i < adapter.getCount(); i++) {
                     exercise_Arraylist.add((String) adapter.getItem(i));



                 }
             }

                Bundle exercise_list_bundle = new Bundle();
                exercise_list_bundle.putStringArrayList("Enlist",exercise_Arraylist);
                save_schedule.setArguments(exercise_list_bundle);
                getParentFragmentManager().setFragmentResult("toSave",exercise_list_bundle);


                @SuppressLint("CommitTransaction") FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerView,save_schedule).commit();



            }
        });




    }

    public void add_btn(View view, ArrayAdapter<? extends String> arrayAdapter, List<String> list) {
        ListView listView = view.findViewById(R.id.excerciselist);

        EditText exercise_textview = view.findViewById(R.id.textExcercise);



        Button btn = view.findViewById(R.id.addBtn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (exercise_textview != null ) {
                    String excer_names =  exercise_textview.getText().toString();
                    // Adds the element to the list
                    // Notify the adapter on the new dataset
                    if(!excer_names.isEmpty()) {
                        list.add(exercise_textview.getText().toString());

                        arrayAdapter.notifyDataSetChanged();
                        exercise_textview.setText(" ");




                    }
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmation")
                        .setMessage("Are you removing an item from the list? ")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                list.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

}