package com.trawbensproject

import android.app.Application
import android.util.Config.DEBUG
import com.trawlbensproject.data.di.databaseDependency
import com.trawlbensproject.data.di.networkDependency
import com.trawlbensproject.data.di.repositoryDependency
import com.trawlbensproject.presentation.di.useCaseDependency
import com.trawlbensproject.presentation.di.viewModelDependency
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (DEBUG) Level.DEBUG else Level.NONE)
            androidContext(this@MyApp)
            modules(
                databaseDependency,
                networkDependency,
                useCaseDependency,
                repositoryDependency,
                viewModelDependency,
            )
        }
    }
}