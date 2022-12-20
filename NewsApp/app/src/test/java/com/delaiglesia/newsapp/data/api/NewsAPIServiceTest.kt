package com.delaiglesia.newsapp.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.internal.http.RequestLine.requestPath
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIServiceTest {
    private lateinit var newsAPIService: NewsApiService
    private lateinit var server: MockWebServer

    @Before
    fun setup() {
        server = MockWebServer()
        //no need to start the server, it will be started automatically
        //server.start()
        newsAPIService = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun getTopHeadlines_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("newsResponse.json")
            val responseBody = newsAPIService.getTopHeadlines("us", 1).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/top-headlines?country=us&apiKey=2e214cb6e6bc416591d340710e60ce45&page=1    ")
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctPageSize() {
        runBlocking {
            enqueueMockResponse("newsResponse.json")
            val responseBody = newsAPIService.getTopHeadlines("us", 1).body()
            assertThat(responseBody?.articles?.size).isEqualTo(20)
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("newsResponse.json")
            val responseBody = newsAPIService.getTopHeadlines("us", 1).body()
            val article = responseBody?.articles?.get(0)
            assertThat(article?.author).isEqualTo("Elliot Smith")
            assertThat(article?.content).isEqualTo("The Bank of Japan on Tuesday shocked global markets by widening the target range for its 10-year government bond yield.\r\nGlobal markets were jolted overnight after the Bank of Japan unexpectedly wide… [+4671 chars]")
            assertThat(article?.description).isEqualTo("Global markets were jolted overnight after Japan unexpectedly raised the cap on 10-year Japanese government bond yields, sparking a sell-off in bonds and stocks.")
            assertThat(article?.publishedAt).isEqualTo("2022-12-20T09:23:24Z")
            assertThat(article?.source?.name).isEqualTo("CNBC")
            assertThat(article?.title).isEqualTo("Bank of Japan shocks global markets with bond yield shift - CNBC")
            assertThat(article?.url).isEqualTo("https://www.cnbc.com/2022/12/20/bank-of-japan-shocks-global-markets-with-bond-yield-shift.html")
            assertThat(article?.urlToImage).isEqualTo("https://image.cnbcfm.com/api/v1/image/107122648-1663837928677-gettyimages-1243415026-AFP_32JV66N.jpeg?v=1671528204&w=1920&h=1080")
        }
    }

    private fun enqueueMockResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }
}