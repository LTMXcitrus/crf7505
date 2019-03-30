package com.lemonfactory.pegass.client

import com.lemonfactory.pegass.client.adapter.PegassActivityAdapter
import com.lemonfactory.pegass.client.adapter.PegassTrainingAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class PegassModule {

    @Bean
    open fun pegassTrainingService(): PegassTrainingService {
        return PegassTrainingService(pegassClient(), pegassTrainingAdapter())
    }

    @Bean
    open fun pegassClient(): PegassClient {
        return PegassClient()
    }

    @Bean
    open fun pegassTrainingAdapter(): PegassTrainingAdapter {
        return PegassTrainingAdapter()
    }

    @Bean
    open fun pegassMissionService(): PegassMissionService {
        return PegassMissionService(pegassClient(), pegassActivityAdapter())
    }

    @Bean
    open fun pegassActivityAdapter(): PegassActivityAdapter {
        return PegassActivityAdapter()
    }

}