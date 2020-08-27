package com.example.codingtest

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.codingtest.base.BaseActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codingtest.realm.entity.CategoryEntityInner
import com.example.codingtest.realm.entity.ProductEntity

import io.realm.RealmList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<StudyViewModel>() , ProductSubItemClickListener {
    private var tempList = ArrayList<CategoryEntityInner>()
    private var Called = false

    private lateinit var ProductAdapter: MainProductAdapter
    override fun provideLayout(): Int = R.layout.activity_main
    override fun provideViewModelClass(): Class<StudyViewModel> = StudyViewModel::class.java
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObservable()


        ProductAdapter = MainProductAdapter(ArrayList<CategoryEntityInner>(), this@MainActivity)
        rvSettings.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvSettings.setHasFixedSize(true)
        rvSettings.adapter = ProductAdapter



    }


    private fun setObservable() {


        with(getViewModel()) {
            getproductfromDB().observe(this@MainActivity, Observer {

                Log.v("Sho", it.toString())
                if (it.size > 0) {

                    tempList.clear()
                    tempList = it as ArrayList<CategoryEntityInner>
                    ProductAdapter = MainProductAdapter(it, this@MainActivity)
                    rvSettings.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                    rvSettings.setHasFixedSize(true)
                    rvSettings.adapter = ProductAdapter

                } else {
                    with(getViewModel()) {
                        getProductFromApi().observe(this@MainActivity, Observer {

                            Log.v("Showwwwww", it.toString())
                            tempList.clear()
                            tempList = it
                            ProductAdapter = MainProductAdapter(it, this@MainActivity)
                            rvSettings.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                            rvSettings.setHasFixedSize(true)
                            rvSettings.adapter = ProductAdapter


                        })
                    }
                }


            })
        }


    }







    override fun subMenuItemClicked(type: String, p0: View?) {
        startActivityForResult(ProductDetails.newIntent(this@MainActivity,type),101)
    }
}