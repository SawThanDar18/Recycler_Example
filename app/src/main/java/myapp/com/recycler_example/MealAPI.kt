package myapp.com.recycler_example

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


internal interface MealAPI {

    @GET("api/json/v1/1/latest.php/")
    fun getLatestMeals() : Call<LatestMealResponse>
}
