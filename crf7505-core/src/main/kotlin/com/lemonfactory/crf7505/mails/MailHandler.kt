package com.lemonfactory.crf7505.mails

import com.lemonfactory.crf7505.domain.model.Activities
import com.lemonfactory.crf7505.domain.model.CrfMail
import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.infrastructure.VolunteerRepository
import com.lemonfactory.crf7505.mails.action75.ActionMailPreparator
import com.lemonfactory.crf7505.mails.recap.RecapMailParameters
import com.lemonfactory.crf7505.mails.recap.RecapMailPreparator

class MailHandler(private val mailPreparator: RecapMailPreparator,
                  private val actionMailPreparator: ActionMailPreparator,
                  private val volunteerRepositoryImpl: VolunteerRepository,
                  private val missionFilter: MissionFilter) {

    fun genMails(subject: String, header: String, activities: Activities, footer: String, respMission: String): List<CrfMail> {
        val volunteers = volunteerRepositoryImpl.retrieveAllVolunteers()
        return volunteers.map { generateMail(it, activities, subject, header, footer, respMission) }
    }

    private fun generateMail(volunteer: Volunteer, activities: Activities, subject: String, header: String, footer: String, respMission: String): CrfMail {
        val externalMissionsForVolunteer = missionFilter.filter(activities.externalActivities, volunteer, activities.localStructure)
        val localMissionsForVolunteer = missionFilter.filter(activities.localActivities, volunteer, activities.localStructure)
        val activitiesForVolunteer = Activities(localMissionsForVolunteer, externalMissionsForVolunteer, activities.localStructure)
        return mailPreparator.generateMail(
                RecapMailParameters(volunteer,
                        activitiesForVolunteer,
                        subject,
                        header,
                        footer,
                        respMission
                )
        )
    }

}
