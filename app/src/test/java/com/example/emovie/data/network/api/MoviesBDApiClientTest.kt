package com.example.emovie.data.network.api

import com.example.emovie.resources.Constants
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@RunWith(JUnit4::class)
class MoviesBDApiClientTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: MoviesBDApiClient

    private val testThread = newSingleThreadContext("TestThread")

    @Before
    fun setUp() {

        Dispatchers.setMain(testThread)

        mockWebServer = MockWebServer()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MoviesBDApiClient::class.java)

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()

        Dispatchers.resetMain()
        testThread.close()
    }

    @Test
    fun `Load configuration movies`() {

        runBlocking(Dispatchers.Main) {
            enqueueResponse("countriesResponse.json")

            val data = service.getCountries(Constants.API_KEY)
            val request = mockWebServer.takeRequest()

            Truth.assertThat(request.method == "GET")
            Truth.assertThat(request.path?.contains("configuration/countries?api_key=${Constants.API_KEY}"))
            Truth.assertThat(data.isNotEmpty())

        }

    }

    @Test
    fun `Load configure of primary transalations `() {

        runBlocking(Dispatchers.Main) {
            enqueueResponse("primary_translations.json")

            val data = service.getPrimaryTranslations(Constants.API_KEY)
            val request = mockWebServer.takeRequest()

            Truth.assertThat(request.method == "GET")
            Truth.assertThat(request.path?.contains("configuration/primary_translations?api_key=${Constants.API_KEY}"))
            Truth.assertThat(data.isNotEmpty())

        }

    }

    @Test
    fun `Load languages configuration movies`() {

        runBlocking(Dispatchers.Main) {
            enqueueResponse("languages.json")

            val data = service.getLanguages(Constants.API_KEY)
            val request = mockWebServer.takeRequest()

            Truth.assertThat(request.method == "GET")
            Truth.assertThat(request.path?.contains("configuration/languages?api_key=${Constants.API_KEY}"))
            Truth.assertThat(data.isNotEmpty())

        }

    }

    @Test
    fun `Load countries configuration movies`() {
        runBlocking {
            enqueueResponse("configurationResponse.json")

            val data = service.getConfigurationApi(Constants.API_KEY)
            val request = mockWebServer.takeRequest()

            Truth.assertThat(request.method == "GET")
            Truth.assertThat(request.path?.contains("configuration?api_key=${Constants.API_KEY}"))
            Truth.assertThat(data != null)

        }

    }

    @Test
    fun `Load UpcomingMovies`() {
        runBlocking {
            enqueueResponse("upcomingMovies.json")

            val data = service.getUpcomingMovies(Constants.API_KEY, "en-US")
            val request = mockWebServer.takeRequest()

            Truth.assertThat(request.method == "GET")
            Truth.assertThat(request.path?.contains("movie/upcoming?api_key=${Constants.API_KEY}&language=en-US"))
            Truth.assertThat(data != null)

        }
    }

    @Test
    fun `Load Top Rated Movies`() {
        runBlocking {
            enqueueResponse("upcomingMovies.json")

            val data = service.getTopRatedMovies(Constants.API_KEY, "en-US")
            val request = mockWebServer.takeRequest()

            Truth.assertThat(request.method == "GET")
            Truth.assertThat(request.path?.contains("movie/top_rated?api_key=${Constants.API_KEY}&language=en-US"))
            Truth.assertThat(data != null)

        }
    }

    @Test
    fun `Load Genres of movies`() {
        runBlocking {
            enqueueResponse("upcomingMovies.json")

            val data = service.getGenresOfMovies(Constants.API_KEY, "en-US")
            val request = mockWebServer.takeRequest()

            Truth.assertThat(request.method == "GET")
            Truth.assertThat(request.path?.contains("genre/movie/list?api_key=${Constants.API_KEY}&language=en-US"))
            Truth.assertThat(data != null)

        }
    }

    private fun enqueueResponse(fileName: String) {

        val inputStream = javaClass.classLoader
            ?.getResourceAsStream("api-response/$fileName")?.source()?.buffer()

        mockWebServer.enqueue(
            MockResponse()
                .setBody(inputStream!!.readString(Charsets.UTF_8))
        )
    }


}