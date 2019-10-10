package com.lemonfactory.crf7505

import com.lemonfactory.crf7505.config.Config
import com.lemonfactory.crf7505.mails.MailPreparator
import com.lemonfactory.crf7505.infrastructure.MissionService
import com.lemonfactory.crf7505.mails.BodyTemplate
import com.lemonfactory.crf7505.mails.FooterTemplate
import com.lemonfactory.crf7505.mails.HeaderTemplate
import com.lemonfactory.crf7505.repository.MissionRepositoryImpl
import com.lemonfactory.crf7505.repository.ObjectifyDAO
import com.lemonfactory.crf7505.repository.VolunteerRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


private const val RECAP_SENDER = "RECAP_SENDER"

@Configuration
open class CrfModule {

    @Bean
    open fun missionRepository(missionService: MissionService): MissionRepositoryImpl {
        return MissionRepositoryImpl(missionService)
    }

    @Bean
    open fun volunteerRepository(): VolunteerRepositoryImpl {
        return VolunteerRepositoryImpl(ObjectifyDAO())
    }

    @Bean
    open fun mailPrepator(): MailPreparator {
        return MailPreparator(
                BodyTemplate(),
                HeaderTemplate(),
                FooterTemplate(),
                Config.getEnvRequired(RECAP_SENDER)
        )
    }

}