package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.CrfMail
import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.domain.model.mission.Mission
import com.lemonfactory.crf7505.infrastructure.VolunteerRepository

class MailHandler(private val mailPreparator: MailPreparator,
                  private val volunteerRepositoryImpl: VolunteerRepository,
                  private val missionFilter: MissionFilter) {

    fun genMails(subject: String, header: String, missions: List<Mission>, footer: String): List<CrfMail> {
        val volunteers = volunteerRepositoryImpl.retrieveAllVolunteers()
        return volunteers.map { generateMail(it, missions, subject, header, footer) }
    }

    private fun generateMail(volunteer: Volunteer, missionsDays: List<Mission>, subject: String, header: String, footer: String): CrfMail {
        val missionsForVolunteer = missionFilter.filter(missionsDays, volunteer)
        return mailPreparator.generateMail(
                volunteer,
                missionsForVolunteer,
                subject,
                header,
                footer
        )
    }

}
