package com.example.semester.di

import android.app.Application
import com.example.semester.di.modules.AppBindsModule
import com.example.semester.di.modules.DatabaseModule
import com.example.semester.di.modules.NetworkModule
import com.example.semester.di.modules.ViewModelModule
import com.example.semester.presentation.fragments.DishCardFragment
import com.example.semester.presentation.fragments.DishCartFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(fragment: DishCardFragment)
    fun inject(fragment: DishCartFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}

@Module(
    includes = [
        ViewModelModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        AppBindsModule::class
    ]
)
class AppModule