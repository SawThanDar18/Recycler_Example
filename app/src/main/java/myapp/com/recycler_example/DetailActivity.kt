package myapp.com.recycler_example

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detail_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by User on 5/20/2019.
 */
class DetailActivity : AppCompatActivity() {

    private lateinit var recyclerAdapter2 : RecyclerAdapter2
    private lateinit var mealAPI : MealAPI
    private lateinit var text_detail : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        val bundle : Bundle? = intent.extras
        val value = bundle!!.getString("id")
        val id : String = intent.getStringExtra("id")
        Toast.makeText(applicationContext, "ID  {$value}", Toast.LENGTH_SHORT).show()

        text_detail = findViewById(R.id.text_detail)
        text_detail.setText(value)

        initRetrofit()
        getDetails()
    }

    private fun getDetails() {
        mealAPI.getDetails(text_detail.text.toString()).enqueue(object : Callback<LatestMealResponse>{
            override fun onResponse(call: Call<LatestMealResponse>?, response: Response<LatestMealResponse>?) {
                if(response != null){
                    bindData(response.body()!!)
                }
            }

            override fun onFailure(call: Call<LatestMealResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }


    private fun initRetrofit() {

        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        mealAPI = retrofit.create(MealAPI::class.java)
    }

    private fun bindData(body: LatestMealResponse) {

        recyclerAdapter2 = RecyclerAdapter2(body.meals!!, this)
        recycler_view3.adapter = recyclerAdapter2
        var layoutManager = GridLayoutManager(this, 1, GridLayout.VERTICAL, false)
        recycler_view3.setLayoutManager(layoutManager)
    }
}