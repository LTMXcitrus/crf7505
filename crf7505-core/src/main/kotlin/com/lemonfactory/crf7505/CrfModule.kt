package com.lemonfactory.crf7505

import com.lemonfactory.crf7505.format.FormatService
import com.lemonfactory.crf7505.infrastructure.TrainingService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class CrfModule {

    @Bean
    open fun formatService(trainingService: TrainingService): FormatService {
        return FormatService(trainingService)
    }

}