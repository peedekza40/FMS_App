package com.example.fms_app.Service

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.Volley
import java.io.File

abstract class Conifg_service(
    val parent_ctx: Context,
    val parent_chDir: File) {
    protected var requestQueue: RequestQueue? = null
    protected var ip: String = "http://10.106.1.176:80"
    protected var url: String = ip

    init {
        val cache = DiskBasedCache(parent_chDir, 1024 * 1024) // 1MB cap
        val network = BasicNetwork(HurlStack())
        requestQueue = RequestQueue(cache, network).apply {
            start()
        }
        requestQueue = Volley.newRequestQueue(parent_ctx)
    }
}