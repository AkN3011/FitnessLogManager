package com.example.mylogs;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Nutrition#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Nutrition extends Fragment implements SearchView.OnQueryTextListener {
    View view;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Nutrition() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Nutrition.
     */
    // TODO: Rename and change types and number of parameters
    public static Nutrition newInstance(String param1, String param2) {
        Nutrition fragment = new Nutrition();
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
        view = inflater.inflate(R.layout.fragment_nutrition, container, false);
         @SuppressLint({"MissingInflatedId", "LocalSuppress"}) SearchView searchView = view.findViewById(R.id.searchview_nutrition);
        searchView.setOnQueryTextListener(this);


        return view;
    }
    // API call method only gets executed after the user has entered something into the search bar.

    @Override
    public boolean onQueryTextSubmit(String s) {

        APICall(view,s);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return true;
    }




    public void APICall(View view, String text){

        JSONObject food = null;
        ListView listView = view.findViewById(R.id.Listview_Nutrition);
        ArrayList<String> nutritionArray = new ArrayList<>();


        String apiKey = "zisKPMsgub32SLwXhjdLre2zp68TIxKghrRleZyh";



// Constructed the url with all the necessary components
        String url = "https://api.nal.usda.gov/fdc/v1/foods/search?query="+text+"&api_key=" + apiKey;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                // use the "GET" property to get access to
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {

                        // Handle the JSON response here
                        // The 'response' variable contains the JSON data returned by the API

                        try {
                            // Check if the "foods" key is present in the JSON response
                            if (response.has("foods")) {
                                JSONArray foodsArray = response.getJSONArray("foods");

                                // Assuming we want to process the first item in the array
                                if (foodsArray.length() > 0) {


                                    for(int i = 0; i < foodsArray.length(); i++) {
                                        JSONObject food = foodsArray.getJSONObject(i);
                                        String foodDescription = food.getString("description");
                                        nutritionArray.add(foodDescription);
                                    if(listView != null) {
                                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, nutritionArray);
                                        listView.setAdapter(arrayAdapter);
                                    }

                                    }

                                    foodData(view,foodsArray);






                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error here (e.g., network error or API response error)
                    }
                });


// Add the request to the RequestQueue to execute the API call
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(jsonObjectRequest);



    }

    public void foodData (View view, JSONArray response){

        ListView listView = view.findViewById(R.id.Listview_Nutrition);
        JSONObject jsonObject = null;
        Fragment nutrients = new Nutrients();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                try {
                    JSONObject food = response.getJSONObject(position);

                    String Food_Item = food.getString("description");

                    Bundle food_bundle = new Bundle();
                    food_bundle.putString("Name",Food_Item);
                    nutrients.setArguments(food_bundle);
                    getParentFragmentManager().setFragmentResult("itemName",food_bundle);


                    JSONArray foodNutrients = food.getJSONArray("foodNutrients");
                    for(int i = 0; i < foodNutrients.length(); i++) {
                        JSONObject nutrient = foodNutrients.getJSONObject(i);

                        String nutrient_name = nutrient.getString("nutrientName");

                        if (nutrient_name.equals("Energy")) {

                            String calorieValue = nutrient.getString("value");

                            Bundle energy_bundle = new Bundle();
                            energy_bundle.putString("Calorie",calorieValue);
                            nutrients.setArguments(energy_bundle);
                            getParentFragmentManager().setFragmentResult("energy",energy_bundle);

                        } else if(nutrient_name.equals("Protein")){
                            String proteinValue = nutrient.getString("value");
                            Bundle  protein_bundle = new Bundle();
                            protein_bundle.putString("Protein",proteinValue);
                            nutrients.setArguments(protein_bundle);
                            getParentFragmentManager().setFragmentResult("proteinEnergy",protein_bundle);
                        } else if (nutrient_name.equals("Carbohydrate, by difference")) {

                            String carbohydrateValue = nutrient.getString("value");
                            Bundle carb_bundle = new Bundle();
                            carb_bundle.putString("Carbs",carbohydrateValue);
                            nutrients.setArguments(carb_bundle);
                            getParentFragmentManager().setFragmentResult("carbohydrateEnergy",carb_bundle);
                            
                        }else if(nutrient_name.equals("Total lipid (fat)")){
                            String fatValue = nutrient.getString("value");
                            Bundle fat_bundle = new Bundle();
                            fat_bundle.putString("Fats",fatValue);
                            nutrients.setArguments(fat_bundle);
                            getParentFragmentManager().setFragmentResult("fatEnergy",fat_bundle);
                        }

                    }



                   // Log.d("JsonObject",nutrient);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }



               FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerView,nutrients).commit();


            }
        });



    }


}

/**
 *   // Check if the "foodNutrients" key is present under the "food" object
 *                                     if (food.has("foodNutrients")) {
 *
 *                                         JSONArray foodNutrients = food.getJSONArray("foodNutrients");
 *                                         for (int i = 0; i < foodNutrients.length(); i++) {
 *                                             JSONObject nutrient = foodNutrients.getJSONObject(i);
 *                                             String nutrientName = nutrient.getString("nutrientName");
 *                                             if (nutrientName.toLowerCase().equals("energy")) {
 *                                                 String calorieValue = nutrient.getString("value");
 *                                                 String calorieUnit = nutrient.getString("unitName");
 *                                                 Log.d("Hello", calorieValue);
 *                                                 // Do something with the calorie information (e.g., display it to the user)
 *                                                 // The calorieValue will contain the calorie value (e.g., "50")
 *                                                 // The calorieUnit will contain the unit of measurement (e.g., "kcal")
 *                                                 break; // Stop iterating once we find the "Energy" nutrient
 *                                             }
 *                                         }
 *
 *                                     } else {
 *                                         Log.d("FoodNutrients", "It does no exsist");
 *                                         // Handle the case when "foodNutrients" key is not present in the "food" object
 *                                         // (e.g., show an error message or handle missing nutrient data)
 *                                     }
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */