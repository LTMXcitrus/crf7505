package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.config.Config
import com.lemonfactory.crf7505.config.ConfigKeys
import com.lemonfactory.crf7505.domain.model.CrfMail
import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.domain.model.mission.Mission

class MailPreparator(private val bodyTemplate: BodyTemplate,
                     private val headerTemplate: HeaderTemplate,
                     private val footerTemplate: FooterTemplate,
                     private val config: Config) {

    fun generateMail(volunteer: Volunteer, missions: List<Mission>, subject: String, header: String, footer: String): CrfMail {
        return CrfMail(
                config.getEnvRequired(ConfigKeys.RECAP_SENDER),
                volunteer.emailAddress,
                subject,
                headerTemplate.generateHeader(volunteer, header) + "\n"+
                        bodyTemplate.generateBody(missions) +
                        footerTemplate.generateFooter(footer)

        )
    }


}
