package com.lemonfactory.crf7505

import com.lemonfactory.crf7505.infrastructure.MissionService
import com.lemonfactory.crf7505.repository.MissionRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class CrfModule {

    @Bean
    open fun missionRepository(missionService: MissionService): MissionRepositoryImpl {
        return MissionRepositoryImpl(missionService)
    }

}