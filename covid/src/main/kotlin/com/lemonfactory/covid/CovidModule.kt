package com.lemonfactory.covid

import com.lemonfactory.crf7505.config.Config
import com.lemonfactory.crf7505.repository.ObjectifyDAO
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class CovidModule {

    @Bean
    open fun dispoCovidService(): DispoCovidService {
        return DispoCovidService(DispoImporter(Transformer(), Config(ObjectifyDAO())))
    }

}