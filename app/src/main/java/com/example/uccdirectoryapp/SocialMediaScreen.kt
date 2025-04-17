package com.example.uccdirectoryapp

import android.content.Context
import android.content.Intent
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun SocialMediaScreen() {
    val facebookUrl = "https://www.facebook.com/uccjamaica"
    val twitterUrl = "https://twitter.com/UCCjamaica"
    val instagramUrl = "https://www.instagram.com/uccjamaica"

    var selectedUrl by remember { mutableStateOf(facebookUrl) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00205B))
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Facebook" to facebookUrl, "Twitter" to twitterUrl, "Instagram" to instagramUrl).forEach { (name, url) ->
                Button(
                    onClick = { selectedUrl = url },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC72C))
                ) {
                    Text(name, fontSize = 14.sp, color = Color.Black)
                }
            }
        }

        AndroidView(
            factory = { context -> createConfiguredWebView(context, selectedUrl) },
            update = { it.loadUrl(selectedUrl) },
            modifier = Modifier.fillMaxSize()
        )
    }
}

private fun createConfiguredWebView(context: Context, url: String): WebView {
    return WebView(context).apply {
        @Suppress("SetJavaScriptEnabled")
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.loadsImagesAutomatically = true
        settings.allowContentAccess = true
        settings.allowFileAccess = true
        settings.setSupportZoom(true)
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.userAgentString =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"

        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val targetUrl = request?.url.toString()
                return when {
                    targetUrl.startsWith("http://") || targetUrl.startsWith("https://") -> false
                    targetUrl.startsWith("intent://") || targetUrl.startsWith("fb://") -> {
                        try {
                            val intent = Intent.parseUri(targetUrl, Intent.URI_INTENT_SCHEME)
                            if (intent.resolveActivity(context.packageManager) != null) {
                                context.startActivity(intent)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        true
                    }
                    else -> true
                }
            }
        }

        loadUrl(url)
    }
}
