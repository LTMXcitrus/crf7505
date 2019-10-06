package com.lemonfactory.crf7505.controllers

import com.lemonfactory.crf7505.domain.model.PegassUser
import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.infrastructure.TrainingService
import com.lemonfactory.crf7505.infrastructure.VolunteerRepository
import com.lemonfactory.crf7505.mapper
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/volunteer")
class VolunteerController(val trainingService: TrainingService,
                          val volunteerRepository: VolunteerRepository) {


    @GetMapping("/")
    fun retrieveAll(): String {
        return mapper.writeValueAsString(volunteerRepository.retrieveAllVolunteers())
    }

    @PostMapping("/")
    fun saveVolunteer(@RequestBody volunteer: Volunteer): String {
        return mapper.writeValueAsString(volunteerRepository.saveVolunteer(volunteer))
    }

    @PostMapping("/remove")
    fun removeVolunteer(@RequestBody volunteer: Volunteer): String {
        return mapper.writeValueAsString(volunteerRepository.removeVolunteer(volunteer))
    }

    @PostMapping("/trainings")
    fun volunteerTrainings(@RequestBody user: PegassUser): String {
        return mapper.writeValueAsString(trainingService.getAllVolunteerTrainings(user.username, user.password))
    }


}