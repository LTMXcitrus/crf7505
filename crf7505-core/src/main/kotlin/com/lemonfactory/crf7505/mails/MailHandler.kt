package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.CrfMail
import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.domain.model.mission.MissionsDay
import com.lemonfactory.crf7505.infrastructure.VolunteerRepository

class MailHandler(private val mailPreparator: MailPreparator,
                  private val volunteerRepositoryImpl: VolunteerRepository,
                  private val missionFilter: MissionFilter) {

    fun genMails(missionsDays: List<MissionsDay>): List<CrfMail> {
        val volunteers = volunteerRepositoryImpl.retrieveAllVolunteers()
        return volunteers.map { generateMail(it, missionsDays) }
    }

    private fun generateMail(volunteer: Volunteer, missionsDays: List<MissionsDay>): CrfMail {
        val missionsForVolunteer = missionFilter.filter(missionsDays, volunteer)
        return mailPreparator.generateMails(
                volunteer,
                missionsForVolunteer
        )
    }

}
