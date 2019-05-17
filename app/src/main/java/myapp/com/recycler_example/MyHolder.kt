package myapp.com.recycler_example

import android.content.Context
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


/**
 * Created by User on 5/17/2019.
 */
class MyHolder(itemView: View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {

    private val meal_image : ImageView
    private val meal_title : TextView

    init {
        meal_image = itemView.findViewById<View>(R.id.meal_image) as ImageView
        meal_title = itemView.findViewById<View>(R.id.meal_title) as TextView
    }

    fun index(meal : Meal){
        Glide.with(mContext).load(meal.image).into(meal_image)
        meal_title.text = meal.mealTitle
    }
}