package com.example.codingtest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import androidx.lifecycle.Observer
import com.example.codingtest.base.BaseActivity
import kotlinx.android.synthetic.main.activity_product_details.*

private const val USER_ID = "user_id"
class ProductDetails : BaseActivity<StudyViewModel>() {
    companion object {
        private var user_id: String = ""
        fun newIntent(
            context: Context, user_id: String
        ): Intent {
            return Intent(context, ProductDetails::class.java).apply {
                putExtra(USER_ID, user_id)

                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)

            }
        }

    }

    override fun provideLayout(): Int = R.layout.activity_product_details
    override fun provideViewModelClass(): Class<StudyViewModel> = StudyViewModel::class.java
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_product_details)



        user_id = intent.getStringExtra(USER_ID)
        getViewModel().let {


            it.getProductByIdFromDB(user_id).observe(this, Observer { mEntity ->

                Name.text = mEntity.title
                tvPrice.text = "\u20B9"+mEntity.price.toString()
                description.text = mEntity.description

                Glide.with(this)
                    .load(mEntity.imageUrl)

                    .into(ivIcon)




            })

        }

    }



}