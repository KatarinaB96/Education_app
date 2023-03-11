package com.tutorials.petersonsproject.di.fragment


import com.tutorials.petersonsproject.di.activity.ActivityComponentExposes
import com.tutorials.petersonsproject.di.fragment.module.FragmentMapperModule
import com.tutorials.petersonsproject.di.fragment.module.FragmentModule
import com.tutorials.petersonsproject.di.fragment.module.FragmentViewModelModule


interface FragmentComponentExposes : FragmentModule.Exposes, ActivityComponentExposes, FragmentViewModelModule.Exposes
//    , FragmentMapperModule.Exposes