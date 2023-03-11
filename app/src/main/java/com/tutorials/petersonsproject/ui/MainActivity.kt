package com.tutorials.petersonsproject.ui


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewpager2.widget.ViewPager2
import com.tutorials.petersonsproject.IntroAdapter
import com.tutorials.petersonsproject.R
import com.tutorials.petersonsproject.base.BaseActivity
import com.tutorials.petersonsproject.databinding.ActivityMainBinding
import com.tutorials.petersonsproject.di.activity.ActivityComponent
import com.tutorials.petersonsproject.sharedpreferences.DeviceSharedPreferences
import net.openid.appauth.*
import javax.inject.Inject


const val FIRST_PAGE_POSITION = 0
const val SECOND_PAGE_POSITION = 1
const val MY_CLIENT_ID = "92faee33-8f17-49da-8901-5bf2d5bd1c2d"
val MY_REDIRECT_URI: Uri = Uri.parse("com.petersons://oauth2redirect")

class MainActivity : BaseActivity() {


    private lateinit var binding: ActivityMainBinding
    private val RC_AUTH = 100
    private lateinit var service: AuthorizationService


    @Inject
    lateinit var sharedPreferences: DeviceSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        service = AuthorizationService(this)

        setContentView(binding.root)


        val pager = binding.viewPager
        pager.adapter = IntroAdapter(supportFragmentManager, lifecycle)

        slidersClickListener(pager)
        pageScrolledOrStateChanged(pager)

        binding.tryForFreeButton.setOnClickListener {
            val authorizeUri = Uri.parse("https://www.petersons.com/oauth/authorize?signup=1")
            auth(authorizeUri)
        }

        binding.login.setOnClickListener {
            val authorizeUri = Uri.parse("https://www.petersons.com/oauth/authorize")
            auth(authorizeUri)
        }

    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val resp = it.data?.let { it1 -> AuthorizationResponse.fromIntent(it1) }
                val ex = AuthorizationException.fromIntent(it.data)

                if (ex != null) {
                    Log.e("auth", "$ex")
                } else {

                    resp?.createTokenExchangeRequest()?.let { it1 ->
                        service.performTokenRequest(it1) { token, exception ->
                            Log.e("token", "${token?.accessToken}")
                            sharedPreferences.setAccessToken(token?.accessToken?: "")
                        }
                    }

                    Log.e("resp", "${resp?.accessToken}")

                    val intent = Intent(this, OnboardingActivity::class.java)
                    startActivity(intent)
                    finish()

                }
            }
        }

    private fun auth(authorizeUri: Uri) {
        // val authorizeUri = Uri.parse("https://www.petersons.com/oauth/authorize?signup=1")
        val tokenUri = Uri.parse("https://www.petersons.com/oauth/token")

        val config = AuthorizationServiceConfiguration(authorizeUri, tokenUri)

        val authRequestBuilder = AuthorizationRequest.Builder(
            config,  // the authorization service configuration
            MY_CLIENT_ID,  // the client ID, typically pre-registered and static
            ResponseTypeValues.CODE,  // the response_type value: we want a code
            MY_REDIRECT_URI
        ).build()


        val intent = service.getAuthorizationRequestIntent(authRequestBuilder)
        launcher.launch(intent)
    }


    private fun pageScrolledOrStateChanged(pager: ViewPager2) {
        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                changeImage()
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                changeImage()
            }
        })
    }

    private fun slidersClickListener(pager: ViewPager2) {
        binding.sliders.setOnClickListener {
            when (pager.currentItem) {
                FIRST_PAGE_POSITION -> pager.currentItem = SECOND_PAGE_POSITION
                SECOND_PAGE_POSITION -> pager.currentItem = FIRST_PAGE_POSITION
            }
        }
    }

    private fun changeImage() {
        when (binding.viewPager.currentItem) {

            FIRST_PAGE_POSITION -> binding.sliders.setImageResource(R.drawable.ic_slider)

            SECOND_PAGE_POSITION -> binding.sliders.setImageResource(R.drawable.ic_slider2)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        service.dispose()
    }

}

