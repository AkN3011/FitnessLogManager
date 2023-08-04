package com.example.mylogs;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalenderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calender extends Fragment {

    FirebaseFirestore fireStore;
    String userID;
    FirebaseAuth fauth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Calender() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalenderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Calender newInstance(String param1, String param2) {
        Calender fragment = new Calender();
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

        fireStore = FirebaseFirestore.getInstance();
        fauth = FirebaseAuth.getInstance();



        View view = inflater.inflate(R.layout.fragment_calender, container, false);
        getParentFragmentManager().setFragmentResultListener("toSaveSun",this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                ArrayList<String> list = result.getStringArrayList("Sundaylist");
                userID = fauth.getCurrentUser().getUid();

                @SuppressLint({"MissingInflatedId", "LocalSuppress"})
                ListView exercise_ListView = view.findViewById(R.id.ListviewSunday);

                DocumentReference documentRef = fireStore.collection("WorkoutSunday").document(userID);
                Map<String,Object> user = new HashMap<>();
                user.put("ExerciseSun",list);
                documentRef.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("SavedToFirebase", "Success" + "The usedID " + userID);
                    }
                });
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1,list);
                exercise_ListView.setAdapter(arrayAdapter);


            }
        });

        getParentFragmentManager().setFragmentResultListener("toSaveMon",this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                ArrayList<String> list = result.getStringArrayList("Mondaylist");
                userID = fauth.getCurrentUser().getUid();

                @SuppressLint({"MissingInflatedId", "LocalSuppress"})
                ListView exercise_ListView = view.findViewById(R.id.ListviewMonday);
                DocumentReference documentRef = fireStore.collection("WorkoutMonday").document(userID);
                Map<String,Object> user = new HashMap<>();
                user.put("ExerciseMon",list);
                documentRef.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("SavedToFirebase", "Success" + "The usedID " + userID);
                    }
                });
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1,list);
                exercise_ListView.setAdapter(arrayAdapter);


            }
        });

        getParentFragmentManager().setFragmentResultListener("toSaveTues",this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                ArrayList<String> list = result.getStringArrayList("Tuesdaylist");
                userID = fauth.getCurrentUser().getUid();


                @SuppressLint({"MissingInflatedId", "LocalSuppress"})
                ListView exercise_ListView = view.findViewById(R.id.ListviewTuesday);
                DocumentReference documentRef = fireStore.collection("WorkoutTuesday").document(userID);
                Map<String,Object> user = new HashMap<>();
                user.put("ExerciseTues",list);
                documentRef.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("SavedToFirebase", "Success" + "The usedID " + userID);
                    }
                });
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1,list);
                exercise_ListView.setAdapter(arrayAdapter);


            }
        });

        getParentFragmentManager().setFragmentResultListener("toSaveWed",this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                ArrayList<String> list = result.getStringArrayList("Wednesdaylist");

                @SuppressLint({"MissingInflatedId", "LocalSuppress"})
                ListView exercise_ListView = view.findViewById(R.id.ListviewWednesday);
                userID = fauth.getCurrentUser().getUid();



                DocumentReference documentRef = fireStore.collection("WorkoutWednesday").document(userID);
                Map<String,Object> user = new HashMap<>();
                user.put("ExerciseWed",list);
                documentRef.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("SavedToFirebase", "Success" + "The usedID " + userID);
                    }
                });
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1,list);
                exercise_ListView.setAdapter(arrayAdapter);


            }
        });

        getParentFragmentManager().setFragmentResultListener("toSaveThurs",this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                ArrayList<String> list = result.getStringArrayList("Thursdaylist");

                @SuppressLint({"MissingInflatedId", "LocalSuppress"})
                ListView exercise_ListView = view.findViewById(R.id.ListviewThursday);
                userID = fauth.getCurrentUser().getUid();

                DocumentReference documentRef = fireStore.collection("WorkoutThursday").document(userID);
                Map<String,Object> user = new HashMap<>();
                user.put("ExerciseThurs",list);
                documentRef.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("SavedToFirebase", "Success" + "The usedID " + userID);
                    }
                });



                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1,list);
                exercise_ListView.setAdapter(arrayAdapter);


            }
        });

        getParentFragmentManager().setFragmentResultListener("toSaveFri",this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                ArrayList<String> list = result.getStringArrayList("Fridaylist");

                @SuppressLint({"MissingInflatedId", "LocalSuppress"})
                ListView exercise_ListView = view.findViewById(R.id.ListviewFriday);
                userID = fauth.getCurrentUser().getUid();

                DocumentReference documentRef = fireStore.collection("WorkoutFriday").document(userID);
                Map<String,Object> user = new HashMap<>();
                user.put("ExerciseFri",list);
                documentRef.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("SavedToFirebase", "Success" + "The usedID " + userID);
                    }
                });

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1,list);
                exercise_ListView.setAdapter(arrayAdapter);


            }
        });

        getParentFragmentManager().setFragmentResultListener("toSaveSat",this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                ArrayList<String> list = result.getStringArrayList("Saturdaylist");

                @SuppressLint({"MissingInflatedId", "LocalSuppress"})
                ListView exercise_ListView = view.findViewById(R.id.ListviewSaturday);

                userID = fauth.getCurrentUser().getUid();

                DocumentReference documentRef = fireStore.collection("WorkoutSaturday").document(userID);
                Map<String,Object> user = new HashMap<>();
                user.put("ExerciseSat",list);
                documentRef.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("SavedToFirebase", "Success" + "The usedID " + userID);
                    }
                });

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1,list);
                exercise_ListView.setAdapter(arrayAdapter);


            }
        });

        getParentFragmentManager().setFragmentResultListener("toSaveSun",this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                ArrayList<String> list = result.getStringArrayList("Sundaylist");

                @SuppressLint({"MissingInflatedId", "LocalSuppress"})
                ListView exercise_ListView = view.findViewById(R.id.ListviewSunday);

                userID = fauth.getCurrentUser().getUid();

                DocumentReference documentRef = fireStore.collection("WorkoutSunday").document(userID);
                Map<String,Object> user = new HashMap<>();
                user.put("ExerciseSun",list);
                documentRef.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("SavedToFirebase", "Success" + "The usedID " + userID);
                    }
                });

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1,list);
                exercise_ListView.setAdapter(arrayAdapter);


            }
        });

      //  userID = Objects.requireNonNull(fauth.getCurrentUser()).getUid();













        // Calling the methods
            updateTextColor(view);
            Editschedule(view);

            // Retrieves data from the Firebase Fire store
            MondayDataRetrieval(view);
            TuesdayDataRetrieval(view);
            WednesdayDataRetrieval(view);
            ThursdayDataRetrieval(view);
            FridayDataRetrieval(view);
            SaturdayDataRetrieval(view);
            SundayDataRetrieval(view);








        return view;
    }

// The method uses the Calender to determine the current day and highlight the text.
    // implements the method onClick_list
    public void updateTextColor(View view) {
        ListView listView;
        List<String> list;
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
      Log.d("Yorfragment", String.valueOf(currentDay));



        // Define your day comparison logic here
        // Change this to your desired target day

        if (currentDay == Calendar.MONDAY) {

            TextView textView = view.findViewById(R.id.textView_monday);
            ListView layout = view.findViewById(R.id.ListviewMonday);


            if (textView != null && layout != null) {
                textView.setTextColor(Color.RED);
                OnClick_list(view,textView,layout);



            }

        } else if(currentDay == Calendar.TUESDAY) {

            TextView textView = view.findViewById(R.id.textView_tuesday);
            ListView layout = view.findViewById(R.id.ListviewTuesday);



            if (textView != null && layout != null) {
                textView.setTextColor(Color.RED);
                OnClick_list(view,textView,layout);



            }
        } else if (currentDay == Calendar.WEDNESDAY) {
            TextView textView = view.findViewById(R.id.textView_wednesday);
         ListView layout = view.findViewById(R.id.ListviewWednesday);



            if (textView != null && layout != null) {
                textView.setTextColor(Color.RED);
                OnClick_list(view,textView,layout);



            }


        }else if (currentDay == Calendar.THURSDAY) {
            TextView textView = view.findViewById(R.id.textView_thursday);
          ListView layout = view.findViewById(R.id.ListviewThursday);



            if (textView != null && layout != null) {
                textView.setTextColor(Color.RED);
                OnClick_list(view,textView,layout);


                // Calculate the index of the item you want to be in the middle



            }

        }else if (currentDay == Calendar.FRIDAY) {
            TextView textView = view.findViewById(R.id.textView_friday);
            ListView layout = view.findViewById(R.id.ListviewFriday);



            if (textView != null && layout != null) {
                textView.setTextColor(Color.RED);


                ;
                OnClick_list(view,textView,layout);



            }

        }else if (currentDay == Calendar.SATURDAY) {
            TextView textView = view.findViewById(R.id.textView_saturday);
          ListView layout = view.findViewById(R.id.ListviewSaturday);




            if (textView != null && layout != null) {
                textView.setTextColor(Color.RED);



                OnClick_list(view,textView,layout);



            }

        }else if (currentDay == Calendar.SUNDAY) {
            TextView textView = view.findViewById(R.id.textView_sunday);
            ListView layout = view.findViewById(R.id.ListviewSunday);



            if (textView != null && layout != null) {
                textView.setTextColor(Color.RED);
                OnClick_list(view,textView,layout);



            }

        }else {
            Log.d("You Fragment","Not a valid day");
        }
    }
// The methods increases or decreases the size of the listview on click
    public void OnClick_list(View view, TextView textView, ListView layout) {

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();

                // Update the height to wrap_content
                if(layoutParams.height == 100 ){

                    ListAdapter adapter = layout.getAdapter();
                    int totalHeight = 0;
                    if(adapter != null) {
                        for (int i = 0; i < adapter.getCount(); i++) {
                            View listItem = adapter.getView(i, null, layout);
                            listItem.measure(0, 0);
                            totalHeight += listItem.getMeasuredHeight();
                        }
                    }
                    layoutParams.height = totalHeight;

                }else {
                    layoutParams.height = 100;
                }

                // Apply the updated layout parameters to the layout
                layout.setLayoutParams(layoutParams);




            }


        });

    }

    public void Editschedule(View view) {


        // Make sure the fragment is added or attached before performing the transaction

        Button button = view.findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Fragment edit_schedule = new EditSchedule();
              @SuppressLint("CommitTransaction") FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
              transaction.replace(R.id.fragmentContainerView,edit_schedule).commit();


            }
        });
    }
    public void MondayDataRetrieval(View view){

        userID = Objects.requireNonNull(fauth.getCurrentUser()).getUid();
        DocumentReference documentReference = fireStore.collection("WorkoutMonday").document(userID);
        ListView ListView_Mon = view.findViewById(R.id.ListviewMonday);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    ArrayList<String> list_Mon = (ArrayList<String>) documentSnapshot.get("ExerciseMon");
                    if (list_Mon != null) {
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, list_Mon);
                        ListView_Mon.setAdapter(arrayAdapter);
                    } else {
                        Log.d("Data Retrieval", "ArrayList is null");
                    }
                } else {
                    Log.d("Data Retrieval", "Document does not exist");
                }
            }
        });






    }

    public void TuesdayDataRetrieval(View view){

        userID = Objects.requireNonNull(fauth.getCurrentUser()).getUid();
        DocumentReference documentReference = fireStore.collection("WorkoutTuesday").document(userID);
        ListView ListView_Tues = view.findViewById(R.id.ListviewTuesday);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    ArrayList<String> list_Tues = (ArrayList<String>) documentSnapshot.get("ExerciseTues");
                    if (list_Tues != null) {
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, list_Tues);
                        ListView_Tues.setAdapter(arrayAdapter);
                    } else {
                        Log.d("Data Retrieval", "ArrayList is null");
                    }
                } else {
                    Log.d("Data Retrieval", "Document does not exist");
                }
            }
        });






    }

    public void WednesdayDataRetrieval(View view){

        userID = Objects.requireNonNull(fauth.getCurrentUser()).getUid();
        DocumentReference documentReference = fireStore.collection("WorkoutWednesday").document(userID);
        ListView ListView_wed = view.findViewById(R.id.ListviewWednesday);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    ArrayList<String> list_wed = (ArrayList<String>) documentSnapshot.get("ExerciseWed");
                    if (list_wed != null) {
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, list_wed);
                        ListView_wed.setAdapter(arrayAdapter);
                    } else {
                        Log.d("Data Retrieval", "ArrayList is null");
                    }
                } else {
                    Log.d("Data Retrieval", "Document does not exist");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Data Retrieval", "Error retrieving document", e);
            }
        });



    }

    public void ThursdayDataRetrieval(View view){

        userID = Objects.requireNonNull(fauth.getCurrentUser()).getUid();
        DocumentReference documentReference = fireStore.collection("WorkoutThursday").document(userID);
        ListView ListView_Thur = view.findViewById(R.id.ListviewThursday);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    ArrayList<String> list_Thur = (ArrayList<String>) documentSnapshot.get("ExerciseThurs");
                    if (list_Thur != null) {
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, list_Thur);
                        ListView_Thur.setAdapter(arrayAdapter);
                    } else {
                        Log.d("Data Retrieval", "ArrayList is null");
                    }
                } else {
                    Log.d("Data Retrieval", "Document does not exist");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Data Retrieval", "Error retrieving document", e);
            }
        });



    }

    public void FridayDataRetrieval(View view){

        userID = Objects.requireNonNull(fauth.getCurrentUser()).getUid();
        DocumentReference documentReference = fireStore.collection("WorkoutFriday").document(userID);
        ListView ListView_Fri = view.findViewById(R.id.ListviewFriday);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    ArrayList<String> list_Fri = (ArrayList<String>) documentSnapshot.get("ExerciseFri");
                    if (list_Fri != null) {
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, list_Fri);
                        ListView_Fri.setAdapter(arrayAdapter);
                    } else {
                        Log.d("Data Retrieval", "ArrayList is null");
                    }
                } else {
                    Log.d("Data Retrieval", "Document does not exist");
                }
            }
        });






    }

    public void SaturdayDataRetrieval(View view){

        userID = Objects.requireNonNull(fauth.getCurrentUser()).getUid();
        DocumentReference documentReference = fireStore.collection("WorkoutSaturday").document(userID);
        ListView ListView_Sat = view.findViewById(R.id.ListviewSaturday);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    ArrayList<String> list_Sat = (ArrayList<String>) documentSnapshot.get("ExerciseSat");
                    if (list_Sat != null) {
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, list_Sat);
                        ListView_Sat.setAdapter(arrayAdapter);
                    } else {
                        Log.d("Data Retrieval", "ArrayList is null");
                    }
                } else {
                    Log.d("Data Retrieval", "Document does not exist");
                }
            }
        });






    }

    public void SundayDataRetrieval(View view){

        userID = Objects.requireNonNull(fauth.getCurrentUser()).getUid();
        DocumentReference documentReference = fireStore.collection("WorkoutSunday").document(userID);
        ListView ListView_Sun = view.findViewById(R.id.ListviewSunday);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    ArrayList<String> list_Sun = (ArrayList<String>) documentSnapshot.get("ExerciseSun");
                    if (list_Sun != null) {
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, list_Sun);
                        ListView_Sun.setAdapter(arrayAdapter);
                    } else {
                        Log.d("Data Retrieval", "ArrayList is null");
                    }
                } else {
                    Log.d("Data Retrieval", "Document does not exist");
                }
            }
        });






    }






}



