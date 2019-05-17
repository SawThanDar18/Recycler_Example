package myapp.com.recycler_example

import com.google.gson.annotations.SerializedName

/**
 * Created by User on 5/17/2019.
 */
class LatestMealResponse {

    @SerializedName("meals")
    val meals : ArrayList<Meal>? = null
}