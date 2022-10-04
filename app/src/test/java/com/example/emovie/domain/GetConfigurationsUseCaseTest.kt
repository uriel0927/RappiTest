package com.example.emovie.domain

import com.example.emovie.data.local.bd.entities.CountriesEntity
import com.example.emovie.data.local.bd.entities.LanguagesEntity
import com.example.emovie.data.local.bd.model.LanguageWithCountries
import com.example.emovie.data.model.Configurations
import com.example.emovie.data.model.Translations
import com.example.emovie.data.repository.HomeRepository
import com.example.emovie.domain.model.ResultUseCase
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class GetConfigurationsUseCaseTest {

    @Mock
    private lateinit var homeRepository: HomeRepository

    private lateinit var getConfigurationUseCase: GetConfigurationUseCaseImpl

    @Before
    fun setUp() {
        getConfigurationUseCase = GetConfigurationUseCaseImpl(homeRepository)
    }


    @Test
    fun `getConfiguration of API movies, get primary transalation and years of release`() {

        runBlocking {

            Mockito.`when`(homeRepository.getTranslations()).thenReturn(
                listOf(
                    LanguageWithCountries(
                        LanguagesEntity(
                            isoLanguage = "en",
                            englishName = "English",
                            name = "English"
                        ), countries = listOf(
                            CountriesEntity("ca", "Canada", "Canada"),
                            CountriesEntity("us", "United States of America", "United States")
                        )
                    ),
                    LanguageWithCountries(
                        LanguagesEntity(
                            isoLanguage = "es",
                            englishName = "Spanish",
                            name = "Español"
                        ), countries = listOf(
                            CountriesEntity("mx", "Mexico", "Mexico"),
                            CountriesEntity("es", "Spain", "Spain")
                        )
                    )
                )
            )

            val years = listOf("2002,2008")
            Mockito.`when`(homeRepository.getYearsRelease()).thenReturn(years)

            val result = getConfigurationUseCase.execute()

            val resultExpected = ResultUseCase.Success<Configurations>(
                data = Configurations(
                    listOf(
                        Translations("en", "ca", "Canada"),
                        Translations("en", "us", "United States"),
                        Translations("es", "mx", "Mexico"),
                        Translations("es", "es", "Spain")
                    )
                , yearsRelease = years)
            )

            verify(homeRepository, times(1)).downloadLanguageConfiguration()
            verify(homeRepository, times(1)).downloadCountriesConfiguration()

            verify(homeRepository, times(1)).getYearsRelease()

            Truth.assertThat(resultExpected == result).isTrue()

        }


    }
}