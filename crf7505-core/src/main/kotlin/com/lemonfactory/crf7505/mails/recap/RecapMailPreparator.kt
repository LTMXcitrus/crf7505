package com.lemonfactory.crf7505.mails.recap

import com.lemonfactory.crf7505.config.Config
import com.lemonfactory.crf7505.config.ConfigKeys
import com.lemonfactory.crf7505.domain.model.Activities
import com.lemonfactory.crf7505.domain.model.CrfMail
import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.mails.interfaces.MailPreparator

data class RecapMailParameters(
        val volunteer: Volunteer,
        val activities: Activities,
        val subject: String,
        val header: String,
        val footer: String,
        val respMission: String
)

class RecapMailPreparator(private val bodyTemplate: RecapBodyTemplate,
                          private val headerTemplate: RecapHeaderTemplate,
                          private val footerTemplate: RecapFooterTemplate,
                          private val config: Config): MailPreparator<RecapMailParameters> {

    override fun generateMail(mailParameters: RecapMailParameters): CrfMail {
        with(mailParameters) {
            return CrfMail(
                    config.getEnvRequired(ConfigKeys.RECAP_SENDER),
                    volunteer.emailAddress,
                    subject,
                    headerTemplate.generateHeader(RecapHeader(volunteer, header, respMission)) + "\n" +
                            bodyTemplate.generateBody(activities) +
                            footerTemplate.generateFooter(footer)
            )
        }
    }

}
