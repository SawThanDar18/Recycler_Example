package myapp.com.recycler_example

import com.google.gson.annotations.SerializedName

/**
 * Created by User on 5/17/2019.
 */

class Meal {

    @SerializedName("idMeal")
    val mealId : Int? = null

    @SerializedName("strMeal")
    val mealTitle : String? = null

    @SerializedName("strDrinkAlternate")
    val strDrinkAlternate : String? = null

    @SerializedName("strCategory")
    val category : String? = null

    @SerializedName("strArea")
    val area : String? = null

    @SerializedName("strMealThumb")
    val image: String? = null

    @SerializedName("strTags")
    val tag : String? = null

    @SerializedName("strInstructions")
    val instruction : String? = null
}
