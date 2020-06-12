package com.rasfar.dagger.app.support

import okhttp3.mockwebserver.MockResponse

class MockServerRobot constructor(rule: MockWebServerRule) {

    private val mockWebServer = rule.getMockServer()
    private val rxIdlerController = RxIdlerController()
    private val requestHandler = RecordedRequestHandler()

    fun enqueueResponse(path: String): MockServerRobot {
        val response = requestHandler.readJsonFile(path)
        mockWebServer.enqueue(requestHandler.getResponseWithBody(200, response))
        return this
    }

    fun enqueueErrorResponse(): MockServerRobot {
        mockWebServer.enqueue(MockResponse().setResponseCode(500))
        return this
    }

    fun performUiAction(action: UiAction): MockServerRobot {
        rxIdlerController.unregisterAll()

        try {
            action.perform()
        } catch (e: Exception) {
            throw e
        }

        rxIdlerController.reRegisterAll()
        return this
    }
}