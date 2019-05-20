package myapp.com.recycler_example

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.widget.GridLayout.VERTICAL
import android.widget.Toast
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.*
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



class MainActivity : AppCompatActivity(), ItemClickListener {


    private lateinit var recyclerAdapter : RecyclerAdapter
    private lateinit var mealAPI : MealAPI

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRetrofit()
        getLatestMeals()

        search_button.setOnClickListener() {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
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

        recyclerAdapter = RecyclerAdapter(body.meals!!, this, this)
        recycler_view.adapter = recyclerAdapter
        var layoutManager = GridLayoutManager(this, 2, VERTICAL, false)
        recycler_view.setLayoutManager(layoutManager)
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)

        Toast.makeText(applicationContext, id, Toast.LENGTH_SHORT).show()
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        val searchItem = menu!!.findItem(R.id.search_item)
        if(searchItem != null){
            searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText!!.isNotEmpty()){

                    }else{

                    }
                    return true
                }

            })
        }
        return true
    }
*/
    private fun initRetrofit() {

        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        mealAPI = retrofit.create(MealAPI::class.java)
    }

}
