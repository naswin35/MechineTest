package com.example.codingtest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.codingtest.api.ApiInterface
import com.example.codingtest.mapper.MapCategoryToEntity

import com.example.codingtest.network.Category
import com.example.codingtest.network.json_response

import com.example.codingtest.realm.entity.CategoryEntity
import com.example.codingtest.realm.entity.CategoryEntityInner
import com.example.codingtest.realm.entity.ProductEntity

import com.example.codingtest.utils.AndroidDisposable
import com.example.codingtest.utils.AppRxSchedulers

import com.example.codingtest.utils.ProductDao
import io.realm.Realm

import javax.inject.Singleton

/**
 * StudyRepository for making API calls and making DB actions
 */
@Suppress("TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING")
@Singleton

    class StudyRepository(var api: ApiInterface, var rxSchedulers: AppRxSchedulers)
{


private var disposable = AndroidDisposable()
private var downloadStatusLiveData = MutableLiveData<String>()







    private fun saveproductTorealm(response: json_response): json_response {



        val CategoryEntityList = MapCategoryToEntity(response.categories )
        Realm.getDefaultInstance().use {
            it.ProductDao().insertOrUpdateMedicineMenu(CategoryEntityList)
        }
        return response
    }


    fun fetcNewMagazineFromAPI(): MutableLiveData<ArrayList<CategoryEntityInner>>
    {


        val response = MutableLiveData<ArrayList<CategoryEntityInner>>()
        disposable.add(
            api.getproducts()
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.androidThread())
                 .map(::saveproductTorealm)
                .subscribe(
                    {
                        it.apply {


                        }
                        response.value = MapCategoryToEntity(it.categories)
                    },
                    {
                        Log.e("ERROR", it.message)
                    }
                )
        )
        return response
    }




    fun getProductMenu(): LiveData<List<CategoryEntityInner>> {
        Realm.getDefaultInstance().use { return it.ProductDao().getMedicinMenu() }
    }


    fun getProductByIdFromDB(id: String): MutableLiveData<ProductEntity> {
        Realm.getDefaultInstance().use {
            return it.ProductDao().getSingleMagazineDataById(id)
        }
    }







}
