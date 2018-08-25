package com.udacity.sandwichclub.utils;

import android.support.v4.content.res.TypedArrayUtils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        List<String> alsoKnownAsList = new ArrayList<>();
        List<String> ingredientsList = new ArrayList<>();

        try {
            JSONObject sandwichObject = new JSONObject(json);
            JSONObject nameObject = sandwichObject.getJSONObject("name");
            JSONArray alsoKnownAsArray = nameObject.getJSONArray("alsoKnownAs");
            String mainName = nameObject.getString("mainName");
            String placeOfOrigin = sandwichObject.getString("placeOfOrigin");
            String description = sandwichObject.getString("description");
            String image = sandwichObject.getString("image");
            JSONArray ingredientsArray = sandwichObject.getJSONArray("ingredients");

            if (alsoKnownAsArray != null && alsoKnownAsArray.length() > 0) {
                for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                    alsoKnownAsList.add(alsoKnownAsArray.getString(i));
                }
            }

            if (ingredientsArray != null && ingredientsArray.length() > 0) {
                for (int i = 0; i < ingredientsArray.length(); i++) {
                    ingredientsList.add(ingredientsArray.getString(i));
                }
            }

            Sandwich sandwich = new Sandwich();
            sandwich.setDescription(description);
            sandwich.setImage(image);
            sandwich.setMainName(mainName);
            sandwich.setPlaceOfOrigin(placeOfOrigin);
            sandwich.setAlsoKnownAs(alsoKnownAsList);
            sandwich.setIngredients(ingredientsList);

            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
