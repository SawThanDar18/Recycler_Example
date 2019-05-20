package myapp.com.recycler_example

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by User on 5/17/2019.
 */

class RecyclerAdapter2(private val meals:ArrayList<Meal>, private val mContext : Context) : RecyclerView.Adapter<MyHolder2>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder2 {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_detail, parent, false)
        return MyHolder2(view, mContext)
    }

    override fun onBindViewHolder(holder: MyHolder2, position: Int) {

        holder?.index(meals[position])
    }

    override fun getItemCount(): Int {
        return meals.size
    }
}
