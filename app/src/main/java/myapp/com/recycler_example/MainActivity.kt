package myapp.com.recycler_example

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.GridLayout.VERTICAL
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



class MainActivity : AppCompatActivity() {
/*
    private val API_BASE_URL = "https://www.themealdb.com/"
    private val meals: Meal? = null
    private var listMeal: List<Meal>? = null
    private var recycler_view: RecyclerView? = null
    private var adapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null*/

    private lateinit var recyclerAdapter : RecyclerAdapter
    private lateinit var mealAPI : MealAPI

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRetrofit()
        getLatestMeals()
    }

    private fun getLatestMeals() {
        mealAPI.getLatestMeals().enqueue(object : Callback<LatestMealResponse> {
            override fun onResponse(call: Call<LatestMealResponse>?, response: retrofit2.Response<LatestMealResponse>?) {
                if(response != null){
                    bindData(response.body()!!)
                }
            }

            override fun onFailure(call: Call<LatestMealResponse>?, t: Throwable?) {

                Toast.makeText(applicationContext, t?.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun bindData(body: LatestMealResponse) {

        recyclerAdapter = RecyclerAdapter(body.meals!!, this)
        recycler_view.adapter = recyclerAdapter
        var layoutManager = GridLayoutManager(this, 2, VERTICAL, false)
        recycler_view.setLayoutManager(layoutManager)

    }

    private fun initRetrofit() {

        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        mealAPI = retrofit.create(MealAPI::class.java)
    }

}
