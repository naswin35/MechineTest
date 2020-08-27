package com.example.codingtest.utils

import androidx.lifecycle.LiveData
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmModel
import io.realm.RealmResults
import java.lang.Exception

open class LiveRealmData<T : RealmModel>(private val results: RealmResults<T>) : LiveData<List<T>>() {

    private val listener =
        RealmChangeListener<RealmResults<T>>(function = { results: RealmResults<T>? ->
            value = Realm.getDefaultInstance().copyFromRealm(results!!)
        })

    override fun onActive() {
       try{ results.addChangeListener(listener)}catch (ex: Exception){}
    }

    override fun onInactive() {
       try{ results.removeChangeListener(listener)}catch (ex: Exception){}
    }
}
