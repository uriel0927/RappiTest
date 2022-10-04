package com.example.emovie.data.local.bd.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.emovie.data.local.bd.MoviesDatabase
import com.example.emovie.data.local.bd.relations.LanguagesCountriesCrossRef
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LanguageCountriesDaoTest {

    private lateinit var db: MoviesDatabase
    private lateinit var languageCountriesDao: LanguageCountriesDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, MoviesDatabase::class.java).build()
        languageCountriesDao = db.getLanguagesCountriesDao()
    }

    @After
    fun closeDB() {
        db.close()
    }

    @Test
    fun insertAndSelectLanguageCountryTest() {

        val languageCountry = LanguagesCountriesCrossRef("en", "US")
        languageCountriesDao.insertLanguageCountry(languageCountry)

        val selectCountry = languageCountriesDao.getLanguagesCountries()
        assertThat(selectCountry.contains(languageCountry)).isTrue()
    }

    @Test
    fun selectLanguagesWithCountriesByIsoTest() {

        val languageCountry = LanguagesCountriesCrossRef("en", "US")
        languageCountriesDao.insertLanguageCountry(languageCountry)

        val selectCountry = languageCountriesDao.getLanguagesCountriesByIsoLanguage("en")
        assertThat(selectCountry.contains(languageCountry)).isTrue()

    }

    @Test
    fun insertAndSelectAllLanguageCountryTest() {

        val languageCountry = listOf(
            LanguagesCountriesCrossRef("en", "US"),
            LanguagesCountriesCrossRef("en", "CA"),
            LanguagesCountriesCrossRef("en", "EN")
        )
        languageCountriesDao.insertLanguagesCountries(languageCountry)

        val languagesCountries = languageCountriesDao.getLanguagesCountries()

        assertThat(languagesCountries.containsAll(languageCountry)).isTrue()

    }

    @Test
    fun deleteAllLanguageCountryTest() {

        val languageCountry = listOf(
            LanguagesCountriesCrossRef("en", "US"),
            LanguagesCountriesCrossRef("en", "CA"),
            LanguagesCountriesCrossRef("en", "EN")
        )
        languageCountriesDao.insertLanguagesCountries(languageCountry)

        languageCountriesDao.deleteAllLanguageCountry()
        val selectCountry = languageCountriesDao.getLanguagesCountries()

        assertThat(selectCountry.containsAll(languageCountry)).isFalse()

    }

}