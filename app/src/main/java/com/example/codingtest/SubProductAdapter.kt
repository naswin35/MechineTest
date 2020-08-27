package com.example.codingtest

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.codingtest.realm.entity.CategoryEntity
import com.example.codingtest.realm.entity.ProductEntity

import com.example.codingtest.utils.inflate
import io.realm.RealmList


class SubProductAdapter(var itemList: RealmList<ProductEntity>, val SubMenuItemClickListener: ProductSubItemClickListener) :
    RecyclerView.Adapter<SubProductAdapter.ViewHolder>() {
    private var tempList = ArrayList<CategoryEntity>()
    private var itemSelectedListener: ChatItemSelectedListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_rv_sub_menu))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val item = itemList[position]


        holder.tvTitle.text = item!!.title
        holder.tvPrice.text =  "\u20B9"+item!!.price.toString()


            Glide.with(holder.views.context)
                .load(item!!.imageUrl)
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                        return false
                    }
                })
                .into(holder.ivIcon)




    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener {
        var views = view
        override fun onClick(viewses: View?) {


            SubMenuItemClickListener.subMenuItemClicked(itemList[adapterPosition]!!.title.toString(),viewses)
        }

        init {
            view.setOnClickListener(this)
        }

        val ivIcon = view.findViewById<ImageView>(R.id.ivIcon)

        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvPrice = view.findViewById<TextView>(R.id.tvPrice)


    }




//    fun filterList(filteredList: java.util.ArrayList<EmployeeEntity>) {
//        itemList = filteredList
//        notifyDataSetChanged()
//    }
//
//    fun setChatSearchItemListner(mchatItemSelectedListener: ChatItemSelectedListener){
//        itemSelectedListener=mchatItemSelectedListener
//    }


}
