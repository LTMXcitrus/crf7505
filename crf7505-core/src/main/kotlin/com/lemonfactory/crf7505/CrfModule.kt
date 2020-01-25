package com.lemonfactory.crf7505

import com.lemonfactory.crf7505.config.Config
import com.lemonfactory.crf7505.infrastructure.ConnectedUserResolver
import com.lemonfactory.crf7505.infrastructure.MailService
import com.lemonfactory.crf7505.infrastructure.MissionService
import com.lemonfactory.crf7505.infrastructure.StatsService
import com.lemonfactory.crf7505.mails.*
import com.lemonfactory.crf7505.repository.MissionRepositoryImpl
import com.lemonfactory.crf7505.repository.ObjectifyDAO
import com.lemonfactory.crf7505.repository.VolunteerRepositoryImpl
import com.lemonfactory.crf7505.stats.StatsRepository
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
    open fun mailPrepator(): MailPreparator {
        return MailPreparator(
                BodyTemplate(),
                HeaderTemplate(),
                FooterTemplate(),
                config()
        )
    }

    @Bean
    open fun statsRepository(
            statsService: StatsService,
            mailService: MailService,
            connectedUserResolver: ConnectedUserResolver
    ): StatsRepository {
        return StatsRepository(
                statsService,
                ObjectifyDAO(),
                volunteerRepository(),
                mailService,
                config(),
                connectedUserResolver
        )
    }

    @Bean
    open fun missionFilter(): MissionFilter {
        return MissionFilter()
    }

    @Bean
    open fun mailHandler(): MailHandler {
        return MailHandler(mailPrepator(),
                volunteerRepository(),
                missionFilter())
    }

}