package com.lemonfactory.pegass.client

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
        return PegassClient(pegassConnector())
    }

    @Bean
    open fun pegassConnector(): PegassConnector {
        return PegassConnector()
    }

    @Bean
    open fun pegassTrainingAdapter(): PegassTrainingAdapter {
        return PegassTrainingAdapter()
    }

}