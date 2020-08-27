package com.example.codingtest

import android.content.Context
import android.content.SharedPreferences
import com.example.codingtest.di.component.DaggerAppComponent
import com.example.codingtest.utils.Constants.Companion.DB_NAME

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.realm.DynamicRealm
import io.realm.Realm
import io.realm.RealmConfiguration

class CommonApp : DaggerApplication() {

    init {
        instance = this
    }

    companion object {
        private var instance: CommonApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

    }



    override fun onCreate() {
       // ActivityLifecycleCallback.register(this)
        super.onCreate()
            Realm.init(this@CommonApp)
            val config =
                RealmConfiguration.Builder()
                    .name(DB_NAME)
                    .build()

            val dynRealm = DynamicRealm.getInstance(config)
            if (dynRealm.version in 2..22) {
                try {
                    dynRealm.close()
                    Realm.deleteRealm(config)
                    val prefs: SharedPreferences =
                        applicationContext()
                            .getSharedPreferences("test_pref", Context.MODE_PRIVATE)
                    prefs.edit().clear().apply()

                } catch (ex: java.lang.Exception) {
                }

            }
            if (!dynRealm.isClosed) {
                dynRealm.close()
            }

            Realm.setDefaultConfiguration(config)
            Realm.getInstance(config)




    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this@CommonApp)
            .build()
    }

    override fun onTerminate() {
        try { Realm.getDefaultInstance().close()} catch (ignore: Exception) {}
        super.onTerminate()
    }

}