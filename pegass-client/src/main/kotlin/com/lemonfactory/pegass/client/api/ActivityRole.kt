package com.lemonfactory.pegass.client.api

data class ActivityRole(val id: String?,
                        val code: String,
                        val role: String,
                        val actif: Boolean,
                        val effectif: Int,
                        val type: String?,
                        val idKeyQualification: Int?)
