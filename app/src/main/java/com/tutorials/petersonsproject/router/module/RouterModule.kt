package com.tutorials.petersonsproject.router.module

import androidx.fragment.app.FragmentManager

import com.tutorials.petersonsproject.router.Router
import com.tutorials.petersonsproject.router.RouterImpl
import com.tutorials.petersonsproject.base.BaseActivity
import dagger.Module
import dagger.Provides


@Module
class RouterModule(private val baseActivity: BaseActivity) {

    @Provides
    internal fun provideRouter(fragmentManager: FragmentManager): Router {
        return RouterImpl(fragmentManager)
    }


    interface Exposes {
        fun router(): Router
    }
}