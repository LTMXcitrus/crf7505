package com.lemonfactory.crf7505.domain.model.mission

import java.time.LocalDateTime

fun aMission(beginDate: LocalDateTime, endDate: LocalDateTime): Mission =
        Mission(
                beginDate,
                endDate,
                "name",
                "ul",
                emptyList(),
                emptyList()
        )

