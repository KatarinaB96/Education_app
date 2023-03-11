package com.tutorials.petersonsproject.di.activity


import com.tutorials.petersonsproject.di.activity.module.ActivityModule
import com.tutorials.petersonsproject.di.application.ApplicationComponentExposes
import com.tutorials.petersonsproject.router.module.RouterModule


interface ActivityComponentExposes : ActivityModule.Exposes, ApplicationComponentExposes,
    RouterModule.Exposes
