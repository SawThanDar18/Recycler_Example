package myapp.com.recycler_example

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.search_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.channels.spi.AbstractSelectionKey


class SearchActivity : AppCompatActivity() {

    private lateinit var recyclerAdapter : RecyclerAdapter
    private lateinit var mealAPI : MealAPI
    private lateinit var edittext : EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        initRetrofit()

        edittext = findViewById(R.id.edittext)

        search.setOnClickListener {
            getSearchMeals()
        }

    }

    private fun getSearchMeals() {

        mealAPI.getSearchMeals(edittext.text.toString()).enqueue(object : Callback<LatestMealResponse> {
            override fun onResponse(call: Call<LatestMealResponse>?, response: Response<LatestMealResponse>?) {
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

        recyclerAdapter = RecyclerAdapter(body.meals!!, this, object : ItemClickListener{
            override fun onItemClicked(id: String) {

                val intent = Intent(applicationContext, DetailActivity::class.java)
                intent.putExtra("id",id)
                startActivity(intent)

                Toast.makeText(applicationContext, id, Toast.LENGTH_SHORT).show()
            }

        })
        recycler_view2.adapter = recyclerAdapter
        var layoutManager = GridLayoutManager(this, 2, GridLayout.VERTICAL, false)
        recycler_view2.setLayoutManager(layoutManager)
    }

    private fun initRetrofit() {

        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        mealAPI = retrofit.create(MealAPI::class.java)
    }

}