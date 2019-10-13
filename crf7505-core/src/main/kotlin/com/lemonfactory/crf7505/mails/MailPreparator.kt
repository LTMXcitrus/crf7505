package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.CrfMail
import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.domain.model.mission.MissionsDay

class MailPreparator(private val bodyTemplate: BodyTemplate,
                     private val headerTemplate: HeaderTemplate,
                     private val footerTemplate: FooterTemplate,
                     private val sender: String) {

    fun generateMail(volunteer: Volunteer, missions: List<MissionsDay>): CrfMail {
        return CrfMail(
                sender,
                volunteer.emailAddress,
                "subject",
                headerTemplate.generateHeader(volunteer) + "\n"+
                        bodyTemplate.generateBody(missions) +
                        footerTemplate.generateFooter()

        )
    }


}