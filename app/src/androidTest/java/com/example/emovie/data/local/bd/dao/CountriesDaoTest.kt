package com.example.emovie.data.local.bd.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.emovie.data.local.bd.MoviesDatabase

import com.example.emovie.data.local.bd.entities.CountriesEntity
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CountriesDaoTest {

    private lateinit var db: MoviesDatabase
    private lateinit var countriesDao: CountriesDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, MoviesDatabase::class.java).build()
        countriesDao = db.getCountriesDao()
    }

    @After
    fun closeDB() {
        db.close()
    }

    @Test
    fun insertCountryTest() {
        val country = CountriesEntity("AD", "Andorra", "Andorra")
        countriesDao.insert(country)

        val countries = countriesDao.getCountries()
        assertThat(countries.contains(country)).isTrue()

    }

    @Test
    fun insertAllCountriesTest() {

        val countriesToInsert = listOf(
            CountriesEntity("AD", "Andorra", "Andorra"),
            CountriesEntity("AE", "United Arab Emirates", "United Arab Emirates")
        )

        countriesDao.insert(countriesToInsert)

        val countries = countriesDao.getCountries()
        assertThat(countries.containsAll(countriesToInsert)).isTrue()

    }

    @Test
    fun getCountryByIsoTest(){
        val country = CountriesEntity("AD", "Andorra", "Andorra")
        countriesDao.insert(country)

        val countyBD = countriesDao.getCountries("AD")
        assertThat(country == countyBD).isTrue()
    }

    @Test
    fun deleAllCountriesTest() {

        val countriesToInsert = listOf(
            CountriesEntity("AD", "Andorra", "Andorra"),
            CountriesEntity("AE", "United Arab Emirates", "United Arab Emirates")
        )

        countriesDao.insert(countriesToInsert)
        countriesDao.deleteAll()

        val countries = countriesDao.getCountries()
        assertThat(countries.containsAll(countriesToInsert)).isFalse()

    }

}