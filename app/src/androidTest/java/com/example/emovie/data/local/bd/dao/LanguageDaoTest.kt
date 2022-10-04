package com.example.emovie.data.local.bd.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.emovie.data.local.bd.MoviesDatabase
import com.example.emovie.data.local.bd.entities.CountriesEntity
import com.example.emovie.data.local.bd.entities.LanguagesEntity
import com.example.emovie.data.local.bd.model.LanguageWithCountries
import com.example.emovie.data.local.bd.relations.LanguagesCountriesCrossRef
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LanguageDaoTest {

    private lateinit var db: MoviesDatabase
    private lateinit var languageDao: LanguagesDao
    private lateinit var countriesDao: CountriesDao
    private lateinit var languangeCountriesDao: LanguageCountriesDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, MoviesDatabase::class.java).build()
        languageDao = db.getLanguageDao()
        countriesDao = db.getCountriesDao()
        languangeCountriesDao = db.getLanguagesCountriesDao()
    }

    @After
    fun closeDB() {
        db.close()
    }

    @Test
    fun insertAndSelectSpecificLanguageTest() {

        val language = LanguagesEntity("en", "English", "English")
        languageDao.insertLanguage(language)

        val languageSelected = languageDao.getLanguageByIsoLanguage("en")

        assertThat(languageSelected == language).isTrue()

    }


    @Test
    fun insertAndSelectAllLanguagesTest() {

        val languages = listOf(
            LanguagesEntity("en", "English", "English"),
            LanguagesEntity("de", "German", "Deutsch"),
            LanguagesEntity("es", "Spanish", "Español"),
            LanguagesEntity("pt", "Portuguese", "Português")
        )
        languageDao.insertLanguages(languages)

        val languagesSelected = languageDao.getLanguages()

        assertThat(languagesSelected.containsAll(languages)).isTrue()

    }

    @Test
    fun deleteAllLanguageTest() {
        val languages = listOf(
            LanguagesEntity("en", "English", "English"),
            LanguagesEntity("de", "German", "Deutsch"),
            LanguagesEntity("es", "Spanish", "Español"),
            LanguagesEntity("pt", "Portuguese", "Português")
        )
        languageDao.insertLanguages(languages)
        languageDao.deleteAllLanguages()

        val languagesSelected = languageDao.getLanguages()

        assertThat(languagesSelected.isEmpty()).isTrue()

    }

    @Test
    fun getLanguageWithCountries() {

        val languages = listOf(
            LanguagesEntity("en", "English", "English"),
            LanguagesEntity("es", "Spanish", "Spanish")
        )

        val countries = listOf(
            CountriesEntity("US", "United States of America", "United States"),
            CountriesEntity("CA", "Canada", "Canada"),
            CountriesEntity("ES", "Spain", "Spain"),
            CountriesEntity("MX", "Mexico", "Mexico")
        )

        languageDao.insertLanguages(languages)
        countriesDao.insert(countries)

        languangeCountriesDao.insertLanguagesCountries(
            listOf(
                LanguagesCountriesCrossRef("en", "US"),
                LanguagesCountriesCrossRef("en", "CA"),
                LanguagesCountriesCrossRef("es", "ES"),
                LanguagesCountriesCrossRef("es", "MX"),
            )
        )

        val transaltionExpected = listOf(
            LanguageWithCountries(
                LanguagesEntity("en", "English", "English"),
                listOf(
                    CountriesEntity("CA", "Canada", "Canada"),
                    CountriesEntity("US", "United States of America", "United States")
                )
            ),
            LanguageWithCountries(
                LanguagesEntity("es", "Spanish", "Spanish"),
                listOf(
                    CountriesEntity("ES", "Spain", "Spain"),
                    CountriesEntity("MX", "Mexico", "Mexico")
                )
            )
        )

        val translationBD = languageDao.getLanguagesWithCountries()

        assertThat(transaltionExpected.containsAll(translationBD)).isTrue()

    }

}