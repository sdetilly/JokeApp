package com.example.jokeapp.android.base

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.jokeapp.ViewModel
import com.example.jokeapp.android.App
import com.example.jokeapp.android.ViewModelFactory

abstract class BaseActivity<VM> : AppCompatActivity() {
    abstract val viewModelFromServiceLocator: ViewModel
    protected val serviceLocator = App.instance.serviceLocator

    protected val viewModel: VM by viewModels { ViewModelFactory { viewModelFromServiceLocator } }
}
