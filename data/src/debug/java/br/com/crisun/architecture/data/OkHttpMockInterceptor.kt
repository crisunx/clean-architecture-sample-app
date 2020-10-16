package br.com.crisun.architecture.data

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody

class OkHttpMockInterceptor: Interceptor {
    private val activateRegex by lazy("^/message$"::toRegex)

    private val randomId get() = (1..100).random()
    private val randomCode get() = listOf(200, 400)[(0..1).random()]
    private val randomMessage get() = ('A'..'Z').map { it }.shuffled().subList(0, 10).joinToString("")

    override fun intercept(chain: Interceptor.Chain): Response {
        val json: String
        var resp = 200
        val method = chain.request().method
        val query = chain.request().url.query?.let { "?$it" } ?: ""
        val path = "${chain.request().url.encodedPath}$query"


        when {
            activateRegex.matches(path) -> {
                resp = randomCode
                json = """{"id":$randomId,"text":"$randomMessage","date":"2020-10-20T12:50:10Z"}"""
            }
            else -> {
                json = ""
            }
        }

        return Response.Builder()
            .code(resp)
            .message(json)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(json.toResponseBody("application/json".toMediaType()))
            .addHeader("content-type", "application/json")
            .build()
    }

}
