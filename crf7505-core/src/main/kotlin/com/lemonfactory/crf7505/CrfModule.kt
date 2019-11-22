package com.lemonfactory.crf7505

import com.lemonfactory.crf7505.config.Config
import com.lemonfactory.crf7505.infrastructure.ConnectedUserResolver
import com.lemonfactory.crf7505.infrastructure.MissionService
import com.lemonfactory.crf7505.mails.*
import com.lemonfactory.crf7505.mails.recap.RecapBodyTemplate
import com.lemonfactory.crf7505.mails.recap.RecapFooterTemplate
import com.lemonfactory.crf7505.mails.recap.RecapHeaderTemplate
import com.lemonfactory.crf7505.mails.recap.RecapMailPreparator
import com.lemonfactory.crf7505.repository.MissionRepositoryImpl
import com.lemonfactory.crf7505.repository.ObjectifyDAO
import com.lemonfactory.crf7505.repository.VolunteerRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class CrfModule {

    @Bean
    open fun missionRepository(missionService: MissionService, connectedUserResolver: ConnectedUserResolver): MissionRepositoryImpl {
        return MissionRepositoryImpl(missionService, connectedUserResolver)
    }

    @Bean
    open fun volunteerRepository(): VolunteerRepositoryImpl {
        return VolunteerRepositoryImpl(ObjectifyDAO())
    }

    @Bean
    open fun config(): Config {
        return Config(ObjectifyDAO())
    }

    @Bean
    open fun recapMailPrepator(): RecapMailPreparator {
        return RecapMailPreparator(
                RecapBodyTemplate(),
                RecapHeaderTemplate(),
                RecapFooterTemplate(),
                config()
        )
    }

    @Bean
    open fun missionFilter(): MissionFilter {
        return MissionFilter()
    }

    @Bean
    open fun mailHandler(): MailHandler {
        return MailHandler(recapMailPrepator(),
                volunteerRepository(),
                missionFilter())
    }

}
