package com.lemonfactory.crf7505.mails.action75

import com.lemonfactory.crf7505.domain.model.CrfMail
import com.lemonfactory.crf7505.domain.model.mission.Mission
import com.lemonfactory.crf7505.mails.interfaces.MailPreparator


data class ActionMailParameters(
        val mission: List<Mission>,
        val subject: String,
        val header: String,
        val footer: String
)


class ActionMailPreparator: MailPreparator<ActionMailParameters> {


    override fun generateMail(mailParameters: ActionMailParameters): CrfMail {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
