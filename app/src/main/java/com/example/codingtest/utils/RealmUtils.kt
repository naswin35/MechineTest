package com.example.codingtest.utils

import com.example.codingtest.realm.dao.ProductDao
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults



fun Realm.ProductDao(): ProductDao = ProductDao(this)

fun <T : RealmModel> RealmResults<T>.asLiveData() = LiveRealmData<T>(this)

