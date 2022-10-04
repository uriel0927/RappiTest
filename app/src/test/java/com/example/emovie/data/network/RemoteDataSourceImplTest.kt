package com.example.emovie.data.network

import com.example.emovie.data.network.api.MoviesBDApiClient
import com.example.emovie.resources.Constants
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceImplTest {

    private lateinit var remoteDataSource: RemoteDataSourceImpl

    @Mock
    private lateinit var service: MoviesBDApiClient

    @Before
    fun setUp() {
        remoteDataSource = RemoteDataSourceImpl(apiClient = service)
    }

    @Test
    fun `Verify that datasource call to Configuration Api Call`() {
        runBlocking {
            remoteDataSource.getConfigurationApi()
            verify(service).getConfigurationApi(Constants.API_KEY)
        }

    }

    @Test
    fun `Verify that datasource call to Primary-Transalations Api Call`() {
        runBlocking {
            remoteDataSource.getPrimaryTranslations()
            verify(service).getPrimaryTranslations(Constants.API_KEY)
        }
    }


    @Test
    fun `Verify that datasource call to Languages Configuration Api Call`() {
        runBlocking {
            remoteDataSource.getLanguages()
            verify(service).getLanguages(Constants.API_KEY)
        }
    }

    @Test
    fun `Verify that datasource call to Countries Configration Api Call`() {
        runBlocking {
            remoteDataSource.getCountries()
            verify(service).getCountries(Constants.API_KEY)
        }
    }

    @Test
    fun `Verify that datasource call to Upcoming movies Api Call`() {
        runBlocking {
            val language = "en-US"
            remoteDataSource.getUpcomingMovies(language)
            verify(service).getUpcomingMovies(Constants.API_KEY,language)
        }
    }

    @Test
    fun `Verify that datasource call to top rated movies Api Call`() {
        runBlocking {
            val language = "en-US"
            remoteDataSource.getTopRatedMovies(language)
            verify(service).getTopRatedMovies(Constants.API_KEY,language)
        }
    }

    @Test
    fun `Verify that datasource call to genres list movies movies Api Call`() {
        runBlocking {
            val language = "en-US"
            remoteDataSource.getGenresMovies(language)
            verify(service).getGenresOfMovies(Constants.API_KEY,language)
        }
    }



}