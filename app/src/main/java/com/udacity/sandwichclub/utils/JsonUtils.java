package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.Constants;
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
        String image = "";
        String description = "";
        String placeOfOrigin = "";
        String mainName = "";

        try {
            JSONObject sandwichObject = new JSONObject(json);
            if (sandwichObject.has(Constants.KEY_NAME)) {
                JSONObject nameObject = sandwichObject.getJSONObject(Constants.KEY_NAME);
                if (nameObject.has(Constants.KEY_ALSO_KNOWN_AS)) {
                    JSONArray alsoKnownAsArray = nameObject.getJSONArray(Constants.KEY_ALSO_KNOWN_AS);

                    if (alsoKnownAsArray != null && alsoKnownAsArray.length() > 0) {
                        for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                            alsoKnownAsList.add(alsoKnownAsArray.getString(i));
                        }
                    }
                }
                if (nameObject.has(Constants.KEY_MAIN_NAME)) {
                    mainName = nameObject.getString(Constants.KEY_MAIN_NAME);
                }

            }
            if (sandwichObject.has(Constants.KEY_PLACE_OF_ORIGIN)) {
                placeOfOrigin = sandwichObject.getString(Constants.KEY_PLACE_OF_ORIGIN);
            }
            if (sandwichObject.has(Constants.KEY_DESCRIPTION)) {
                description = sandwichObject.getString(Constants.KEY_DESCRIPTION);
            }
            if (sandwichObject.has(Constants.KEY_IMAGE)) {
                image = sandwichObject.getString(Constants.KEY_IMAGE);
            }
            if (sandwichObject.has(Constants.KEY_INGREDIENTS)) {
                JSONArray ingredientsArray = sandwichObject.getJSONArray(Constants.KEY_INGREDIENTS);
                if (ingredientsArray != null && ingredientsArray.length() > 0) {
                    for (int i = 0; i < ingredientsArray.length(); i++) {
                        ingredientsList.add(ingredientsArray.getString(i));
                    }
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
