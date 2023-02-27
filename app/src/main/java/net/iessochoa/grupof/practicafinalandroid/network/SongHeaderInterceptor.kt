package net.iessochoa.grupof.practicafinalandroid.network

import okhttp3.Interceptor
import okhttp3.Response

class SongHeaderInterceptor: Interceptor {
    var apiToken: String = "BQDnj7-GRFlu_MhHLAth2i6ijyK2AkPomfHnnH5gvIX9VrikJdxTAQNcDq0rhD0jBF" +
            "iLbd8LEVRaA5DmtjDM04vUvu9qWmQ7ZqLxCVLcAQTmBJgm2EClwkftFhkjM-dfg3l-iGfrXTccZQ9qtN2Jn4GBK8xo" +
            "EaemHm4ACXidmi_fnKhOyHAKKBz1d231-GYf5045"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Accept:", "application/json")
            .addHeader("Authorization:", "Bearer " + apiToken)
            .build()

        return chain.proceed(request)
    }
}