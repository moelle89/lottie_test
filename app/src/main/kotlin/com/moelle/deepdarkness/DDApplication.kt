package com.moelle.deepdarkness

import android.app.Application
import android.content.Context
import com.danikula.videocache.HttpProxyCacheServer


class DDApplication: Application() {

    private var proxy: HttpProxyCacheServer? = null

    fun getProxy(context: Context): HttpProxyCacheServer {
        val app = context.applicationContext as DDApplication
        if (app.proxy == null) {
            app.proxy = app.newProxy()
        }
        return app.proxy as HttpProxyCacheServer
    }

    private fun newProxy(): HttpProxyCacheServer {
        return HttpProxyCacheServer(this)
    }

}