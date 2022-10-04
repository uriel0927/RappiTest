package com.example.emovie.domain

import com.example.emovie.data.model.Configurations
import com.example.emovie.domain.model.ResultUseCase

interface GetConfigurationUseCase {

    suspend fun execute() : ResultUseCase<Configurations>

}