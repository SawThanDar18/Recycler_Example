package myapp.com.recycler_example

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by User on 5/17/2019.
 */

class RecyclerAdapter(private val meals:ArrayList<Meal>, private val mContext : Context,
                      private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyHolder(view, mContext, itemClickListener)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        holder?.index(meals[position])
    }

    override fun getItemCount(): Int {
        return meals.size
    }
}
