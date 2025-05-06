package kz.step.jsonplaceholder

import android.app.Application
import kz.step.jsonplaceholder.di.networkModule
import kz.step.jsonplaceholder.di.repositoryModule
import kz.step.jsonplaceholder.di.useCaseModule
import kz.step.jsonplaceholder.di.viewModelModule
import org.koin.core.context.startKoin
import timber.log.Timber

class TutorialApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        startKoin {
            modules(networkModule, viewModelModule, repositoryModule, useCaseModule)
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

}