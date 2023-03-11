package com.tutorials.petersonsproject.base

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.tutorials.petersonsproject.ComponentFactory
import com.tutorials.petersonsproject.R
import com.tutorials.petersonsproject.di.activity.ActivityComponent
import com.tutorials.petersonsproject.network.ConnectivityCheck
import com.tutorials.petersonsproject.network.ConnectivityCheckImpl
import javax.inject.Inject


abstract class BaseActivity : AppCompatActivity() {
    private var activityComponent: ActivityComponent? = null


    @Inject
    lateinit var connectivityCheck: ConnectivityCheck


    private fun getTemplateApplication() = TemplateApplication.from(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(getActivityComponent())

        checkInternet(this)
    }

    fun getActivityComponent(): ActivityComponent {
        if (activityComponent == null) {
            activityComponent = ComponentFactory.createActivityComponent(
                this,
                getTemplateApplication().getApplicationComponent()
            )
        }

        return activityComponent as ActivityComponent
    }

    private fun checkInternet(context:Context) {

//        ConnectivityCheckImpl(context).observe(this) {
//            if (it == false)
//                showAlert()
//
//        }
    }

    // CustomDialog
//    fun showAlert() {
//        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
//            .create()
//        val view = layoutInflater.inflate(R.layout.dialog_error, null)
//        val buttonCancel = view.findViewById<Button>(R.id.buttonDismiss)
//        builder.setView(view)
//        buttonCancel.setOnClickListener {
//            builder.dismiss()
//        }
//        builder.setCanceledOnTouchOutside(false)
//        builder.show()
//    }

    protected abstract fun inject(activityComponent: ActivityComponent)

}