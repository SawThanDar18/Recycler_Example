package myapp.com.recycler_example

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


internal interface MealAPI {

    @GET("api/json/v1/1/latest.php/")
    fun getLatestMeals() : Call<LatestMealResponse>

    @GET("api/json/v1/1/search.php/")
    fun getSearchMeals(@Query("s") keyword : String) : Call<LatestMealResponse>

    @GET("api/json/v1/1/lookup.php/")
    fun getDetails(@Query("i") keyword: String) : Call<LatestMealResponse>
}
