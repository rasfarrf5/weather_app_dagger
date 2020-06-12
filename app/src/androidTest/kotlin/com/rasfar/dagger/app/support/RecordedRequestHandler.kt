package com.rasfar.dagger.app.support

import okhttp3.Headers.Companion.toHeaders
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.util.*

class RecordedRequestHandler {

    fun getResponseWithBody(code: Int, body: String): MockResponse {
        val mockResponse = MockResponse().setResponseCode(code)

        if (body.isNotEmpty()) {
            mockResponse.setBody(body)
        }

        return mockResponse
    }

    fun getResponseWithoutBody(code: Int): MockResponse {
        return MockResponse().setResponseCode(code)
    }

    fun getResponseWithBodyAndHeaders(
        code: Int, body: String,
        headersMap: Map<String, String>
    ): MockResponse {
        val mockResponse = getResponseWithBody(code, body)
        if (headersMap.isNotEmpty()) {
            val headers = headersMap.toHeaders()
            mockResponse.headers = headers
        }
        return mockResponse
    }

    fun readJsonFile(filename: String): String {
        if (filename.isEmpty()) {
            return ""
        }

        val inputStream = this.javaClass.classLoader?.getResourceAsStream(filename)
        val s = Scanner(inputStream).useDelimiter("\\A")
        return if (s.hasNext()) s.next() else ""
    }

    fun getRequestBody(request: RecordedRequest): String {
        return request.body.readUtf8()
    }
}
