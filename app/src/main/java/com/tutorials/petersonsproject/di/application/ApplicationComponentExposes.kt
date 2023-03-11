package com.tutorials.petersonsproject.di.application

import com.tutorials.petersonsproject.di.application.module.ApplicationModule
import com.tutorials.petersonsproject.di.application.module.DataModule

import com.tutorials.petersonsproject.di.application.module.MapperModule

interface ApplicationComponentExposes : ApplicationModule.Exposes,
    DataModule.Exposes
//    , MapperModule.Exposes

