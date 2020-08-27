package com.example.codingtest

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codingtest.realm.entity.CategoryEntityInner
import com.example.codingtest.utils.inflate


class MainProductAdapter(var itemList: ArrayList<CategoryEntityInner>, var mContext:Context) :
    RecyclerView.Adapter<MainProductAdapter.ViewHolder>(){
    private var tempList = ArrayList<CategoryEntityInner>()

    private var itemSelectedListener: ChatItemSelectedListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_rv_main_menu))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val item = itemList[position]

        if(item.item_status.equals("closed"))
        {
            holder.rvSub.visibility=View.GONE
        }
        else
        {
            holder.rvSub.visibility=View.VISIBLE
        }

        holder.tvTitle.text = item.title





        holder.rvSub.layoutManager = LinearLayoutManager(holder.itemView.context)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener {
        var views = view
        private lateinit var SubItemAdapter: SubProductAdapter
        private lateinit var SubAdapter: SubProductAdapter
        override fun onClick(viewses: View?) {

            if(itemList[adapterPosition].item_status.equals("closed"))
            {
                rvSub.visibility=View.VISIBLE
                itemList[adapterPosition].item_status="open"
            }
            else
            {
                rvSub.visibility=View.GONE
                itemList[adapterPosition].item_status="closed"
            }
            SubItemAdapter = SubProductAdapter(itemList[adapterPosition].products, mContext as MainActivity)
            rvSub.layoutManager = GridLayoutManager(itemView.context,2)
            rvSub.setHasFixedSize(true)
            rvSub.adapter = SubItemAdapter


           // EmployeeMenuItemClickListener.medicineMenuItemClicked(itemList[adapterPosition].products,viewses)
        }

        init {
            view.setOnClickListener(this)
        }

        val ivIcon = view.findViewById<ImageView>(R.id.ivIcon)
        val vwGreyPart = view.findViewById<View>(R.id.vwGreyPart)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val ivArrowRight = view.findViewById<ImageView>(R.id.ivArrowRight)
        val rvSub = view.findViewById<RecyclerView>(R.id.rvSub)

    }







}
