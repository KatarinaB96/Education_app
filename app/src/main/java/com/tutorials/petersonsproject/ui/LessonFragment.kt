package com.tutorials.petersonsproject.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import com.tutorials.petersonsproject.base.BaseFragment
import com.tutorials.petersonsproject.databinding.FragmentLessonBinding
import com.tutorials.petersonsproject.di.fragment.FragmentComponent
import com.tutorials.petersonsproject.sharedpreferences.DeviceSharedPreferences
import com.tutorials.petersonsproject.ui.viewmodels.CookieViewModel
import javax.inject.Inject


class LessonFragment : BaseFragment() {
    private lateinit var binding: FragmentLessonBinding

    companion object {
        private const val URL = "url"


        fun createInstance(
            url: String
        ) = LessonFragment().apply {
            arguments = Bundle().apply {
                putString(URL, url)
            }
        }
    }

    @Inject
    lateinit var cookieViewModel: CookieViewModel

    @Inject
    lateinit var sharedPreferences: DeviceSharedPreferences

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    private val url: String by lazy { arguments?.getString(URL) ?: "" }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLessonBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cookieViewModel.getCookies()


        cookieViewModel.cookies.observe(requireActivity()) {
//


            val myWebView = binding.webViewLesson
            myWebView.webViewClient = object: WebViewClient(){
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    if (url?.contains("sessionExpired") == true) {
//                        viewModel.refreshCookies()
                        Log.e("tag", "sessionExpired")
                    }

                }
            }

            myWebView.settings.javaScriptEnabled = true

            val cookieManager = CookieManager.getInstance()


            cookieManager.setCookie(
                it.d2lSessionVal.domain,
                "${it.d2lSessionVal.name}=${it.d2lSessionVal.value}"
            )
            cookieManager.setCookie(
                it.d2lSessionVal.domain,
                "${it.d2lSecureSessionVal.name}=${it.d2lSecureSessionVal.value}"
            )


            binding.webViewLesson.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY


            binding.webViewLesson.loadUrl(
                "https://" + it.d2lSessionVal.domain + url.replace(
                    "\n",
                    ""
                )
            )
            val e =     "https://" + it.d2lSessionVal.domain + url.replace(
                "\n",
                ""
            )
            Log.e("session", e)

        }
    }


}