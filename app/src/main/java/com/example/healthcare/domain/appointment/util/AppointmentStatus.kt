package com.example.healthcare.domain.appointment.util

enum class AppointmentStatus(val value: String) {
    REQUESTED("Requested"),// by user
    ACCEPTED("Accepted"),// by doctor
    CANCELLED("Cancelled"), // by user and doctor and admin
    COMPLETED("Completed"),// by system
    INPROGRESS("In Progress"),// by system
    REJECTED("Rejected"),//by doctor
    UNKNOWN("Unknown");
}