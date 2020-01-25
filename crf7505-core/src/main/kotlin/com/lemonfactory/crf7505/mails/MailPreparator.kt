package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.config.Config
import com.lemonfactory.crf7505.config.ConfigKeys
import com.lemonfactory.crf7505.model.Activities
import com.lemonfactory.crf7505.model.CrfMail
import com.lemonfactory.crf7505.model.Volunteer
import com.lemonfactory.crf7505.model.mission.Mission

class MailPreparator(private val bodyTemplate: BodyTemplate,
                     private val headerTemplate: HeaderTemplate,
                     private val footerTemplate: FooterTemplate,
                     private val config: Config) {

    fun generateMail(volunteer: Volunteer,
                     activities: Activities,
                     subject: String,
                     header: String,
                     footer: String,
                     respMission: String): CrfMail {
        return CrfMail(
                config.getEnvRequired(ConfigKeys.RECAP_SENDER),
                volunteer.emailAddress,
                subject,
                headerTemplate.generateHeader(volunteer, header, respMission) + "\n" +
                        bodyTemplate.generateBody(activities) +
                        footerTemplate.generateFooter(footer)
        )
    }

}
