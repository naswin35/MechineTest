package com.example.codingtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.codingtest.realm.entity. CategoryEntity
import com.example.codingtest.realm.entity.CategoryEntityInner
import dagger.Lazy

import javax.inject.Inject


/**
 * Study repository for magazine related API and DB
 */
class StudyViewModel

@Inject constructor(var repo: Lazy<StudyRepository>) : ViewModel() {

    fun getProductFromApi() = repo.get().fetcNewMagazineFromAPI()

    fun getproductfromDB(): LiveData<List<CategoryEntityInner>> {
        return repo.get().getProductMenu()
    }

    fun getProductByIdFromDB(magazineId: String) = repo.get().getProductByIdFromDB(magazineId)

}


