package com.example.emovie.domain

import com.example.emovie.data.model.Configurations
import com.example.emovie.data.model.Translations
import com.example.emovie.data.repository.HomeRepository
import com.example.emovie.domain.model.ResultUseCase
import javax.inject.Inject

class GetConfigurationUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : GetConfigurationUseCase {

    override suspend fun execute(): ResultUseCase<Configurations> {

        try{
            //Download countries configuration, the repository have the responsability to insert into BD
            homeRepository.downloadCountriesConfiguration()

            //Download language configuration, the repository have the responsability to insert into BD
            homeRepository.downloadLanguageConfiguration()

            //Download the Primary transalation of API and the repository insert the references between Language and Countries
            val translations = homeRepository.getTranslations()

            var primaryTransalations = mutableListOf<Translations>()

            //Convert the result of Database.
            translations.forEach { languageWithCountries ->
                languageWithCountries.countries.forEach {
                    primaryTransalations.add(
                        Translations(
                            languageWithCountries.language.isoLanguage,
                            it.isoCountry,
                            it.nativeName
                        )
                    )
                }
            }

            //Get the release years.
            val yearsRelease = homeRepository.getYearsRelease()


            //Finally return all configuration in an object
            return ResultUseCase.Success(Configurations(translations = primaryTransalations, yearsRelease = yearsRelease))

        }catch (ex : Throwable){
            return ResultUseCase.Failure(ex.message ?: "")
        }

    }
}