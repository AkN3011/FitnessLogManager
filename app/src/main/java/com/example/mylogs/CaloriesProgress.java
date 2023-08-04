package com.example.mylogs;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaloriesProgress#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaloriesProgress extends Fragment {
    private static final String LAST_SAVED_DAY_KEY = "LAST_SAVED_DAY";

    FirebaseFirestore fireStore;
    FirebaseAuth fauth;
    static int calories_num = 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CaloriesProgress() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home_page.
     */
    // TODO: Rename and change types and number of parameters
    public static CaloriesProgress newInstance(String param1, String param2) {
        CaloriesProgress fragment = new CaloriesProgress();
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
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        TextView textview_setcalories = view.findViewById(R.id.GoalCalories);
        TextView textView_calories = view.findViewById(R.id.Calories);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);




        getParentFragmentManager().setFragmentResultListener("toSave",this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String calorie = result.getString("Calorie");
                textview_setcalories.setText(calorie);
                int goalCalories = Integer.parseInt(textview_setcalories.getText().toString());
                saveGoalCalorie(view,goalCalories);









            }
        });



          add_Calories(view);
          setCalories(view);
        currCalorie_retrieval(view);
        goalCalorie_retrieval(view);
        progressbar_update(view);
        calorieReset(view);




        return view;
    }

    public void add_Calories(View view) {
        Button bottomButton = view.findViewById(R.id.bottomButton);
        EditText calorieText = view.findViewById(R.id.CalorieText);
        TextView progressText = view.findViewById(R.id.Calories);

        TextView textview_setcalories = view.findViewById(R.id.GoalCalories);

        ProgressBar progressBar = view.findViewById(R.id.progressBar);



        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String calorie = calorieText.getText().toString();
                calories_num += Integer.parseInt(calorie);

                calorie = String.valueOf(calories_num).toString();
                progressText.setText(calorie);

                if(!textview_setcalories.getText().equals("Set Goal")) {
                    int goalCalories = Integer.parseInt(textview_setcalories.getText().toString());
                    int currCalories = Integer.parseInt(progressText.getText().toString());

                    double percentage = (double) currCalories / (double) goalCalories * 100;


                    progressBar.setProgress((int) percentage);
                    if(goalCalories != 0 &&  currCalories != 0) {

                        saveCurrCalorie(view,currCalories);


                    }
                } else {
                    Toast.makeText(getContext(), "Set your Calories", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public void setCalories(View view){


        TextView textView_goalcal = view.findViewById(R.id.GoalCalories);
          Fragment calories = new Calories();
            textView_goalcal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    @SuppressLint("CommitTransaction") FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainerView, calories).commit();

                }
            });


    }
// sends data to firebase
    public void saveCurrCalorie(View view, int currValue ){

        fireStore = FirebaseFirestore.getInstance();
        fauth = FirebaseAuth.getInstance();

        String userID = fauth.getCurrentUser().getUid();

        DocumentReference documentRef = fireStore.collection("currentCalorie").document(userID);
        Map<String,Object> currMap = new HashMap<>();
        currMap.put("currCalMap",currValue);

        documentRef.set(currMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("SavedToFirebase", "Success" + "The usedID " + userID);
            }
        });

    }

    public void saveGoalCalorie(View view, int goalValue ){

        fireStore = FirebaseFirestore.getInstance();
        fauth = FirebaseAuth.getInstance();

        String userID = fauth.getCurrentUser().getUid();

        DocumentReference documentRef = fireStore.collection("goalCalorie").document(userID);
        Map<String,Object> currMap = new HashMap<>();
        currMap.put("goalCalMap",goalValue);

        documentRef.set(currMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("SavedToFirebase", "Success");
            }
        });

    }
  // Retrieves current target calories
    public void goalCalorie_retrieval(View view){

        fireStore = FirebaseFirestore.getInstance();
        fauth = FirebaseAuth.getInstance();
        TextView textView_goalcalorie = view.findViewById(R.id.GoalCalories);
        String userID = fauth.getCurrentUser().getUid();

        if (textView_goalcalorie != null) {
            DocumentReference documentReference = fireStore.collection("goalCalorie").document(userID);

            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Long currCalorie = documentSnapshot.getLong("goalCalMap");

                    if (currCalorie != null) {
                        String stringConvert_currCalorie = String.valueOf(currCalorie);
                        textView_goalcalorie.setText(stringConvert_currCalorie);
                    }
                }
            });
        }




    }

    //Gets the most recent calorie information from Firebase.
    public void currCalorie_retrieval(View view){
        fireStore = FirebaseFirestore.getInstance();
        fauth = FirebaseAuth.getInstance();
        TextView textView_currcalorie = view.findViewById(R.id.Calories);
        String userID = fauth.getCurrentUser().getUid();

        if (textView_currcalorie != null) {
            DocumentReference documentReference = fireStore.collection("currentCalorie").document(userID);

            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Long currCalorie = documentSnapshot.getLong("currCalMap");

                    if (currCalorie != null) {
                        String stringConvert_currCalorie = String.valueOf(currCalorie);
                        textView_currcalorie.setText(stringConvert_currCalorie);
                    }
                }
            });
        }
    }


    // Method updates the progress bar after the data is retrieved from firebase
    public void progressbar_update(View view){

      TextView textview_GoalCalories = view.findViewById(R.id.GoalCalories);
      TextView textView_CurrCalories = view.findViewById(R.id.Calories);
      ProgressBar progressBar = view.findViewById(R.id.progressBar);



  Runnable runnable = new Runnable() {
      @Override
      public void run() {

          if(!textview_GoalCalories.getText().equals("Set Goal")&& textView_CurrCalories != null){


              int goalCalories = Integer.parseInt(textview_GoalCalories.getText().toString());
              int currCalories = Integer.parseInt(textView_CurrCalories.getText().toString());

              double percentage = (double) currCalories / (double) goalCalories * 100;


              progressBar.setProgress((int) percentage);

          }

      }
  };

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(runnable,1500);


    }

    public void calorieReset(View view){
            int goalValue = 0;




        // retrieves the number associated with the current day
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        //
        int lastSavedDay = sharedPreferences.getInt(LAST_SAVED_DAY_KEY, -1);



        if (currentDay != lastSavedDay) {
            // It's a new day, so reset your number to zero.

            fireStore = FirebaseFirestore.getInstance();
            fauth = FirebaseAuth.getInstance();

            String userID = fauth.getCurrentUser().getUid();

            DocumentReference documentRef = fireStore.collection("currentCalorie").document(userID);
            Map<String,Object> currMap = new HashMap<>();
            currMap.put("currCalMap",goalValue);

            documentRef.set(currMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("SavedToFirebase", "Success" + "The usedID " + userID);
                }
            });



            // Save the current day as the last saved day for the next comparison.
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(LAST_SAVED_DAY_KEY, currentDay);
            editor.apply();
        }






    }






}