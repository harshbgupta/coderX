package com.kritsn.ivs.tada

import java.time.Instant
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit

/**
* 📄 Problem Statement: TADA Ride Reservation & Driver Assignment System
*
* You're building the backend for a ride-sharing platform like Uber/Ola. The backend must support:
*
* 1. Trip reservations with overlap check
* 2. Driver assignment based on fairness (rating, cancellations, idle time)
* 3. Cancellation policies
* 4. Rating update logic
* 5. Use of Instant & ISO8601 for timestamps
 */

class Assignment {

    // ------------------------
    // 🧠 Algorithm Approach & Solution Explanation
    // ------------------------
    /*
    1. For reservation creation:
       - Ensure start and end times are in the future
       - Check against existing reservations of the same driver to detect overlaps
       - Overlap condition: newStart < existingEnd && newEnd > existingStart

    2. For driver assignment:
       - Calculate a score per driver based on:
         - Rating (weight +0.4)
         - Cancellation Rate (weight -0.2)
         - Idle Time (weight +0.2)
         - ETA (optional, weight -0.2)
       - Select the driver with the highest score

    3. For rating update:
       - Apply formula:
         newAvg = (currentAvg * rideCount + newRating) / (rideCount + 1)
       - Ensure null-safety and handle first-time ratings

    4. For cancellation:
       - Allow only if start_time is > 1 hour from now
       - Only allow if trip status is SCHEDULED

    5. Use Instant and ChronoUnit to compare timestamps
    */
    enum class TripStatus {
        SCHEDULED, COMPLETED, CANCELLED
    }

    data class Reservation(
        val driverId: Long?,
        val startTime: Instant?,
        val endTime: Instant?,
        val status: TripStatus?
    )

    data class Driver(
        val id: Long?,
        var rating: Double? = 0.0,
        var ratingCount: Int? = 0,
        var cancellations: Int? = 0,
        var lastCompletedTripEndTime: Instant? = null
    )

    object ReservationService {

        /**
         * Safely parses a string to Instant, returns null if format is invalid.
         */
        fun parseInstantSafe(isoString: String?): Instant? {
            return try {
                if (isoString.isNullOrBlank()) null else Instant.parse(isoString)
            } catch (e: DateTimeParseException) {
                null
            }
        }

        /**
         * Checks whether two reservations overlap in time.
         * Returns true if they overlap, false if any param is null or invalid.
         */
        fun isReservationOverlapping(
            newStart: Instant?,
            newEnd: Instant?,
            existingStart: Instant?,
            existingEnd: Instant?
        ): Boolean {
            if (newStart == null || newEnd == null || existingStart == null || existingEnd == null) return false
            return newStart < existingEnd && newEnd > existingStart
        }

        /**
         * Validates if a reservation can be cancelled (only if it's SCHEDULED and more than 1 hour away).
         */
        fun isCancellationAllowed(reservation: Reservation?, currentTime: Instant?): Boolean {
            if (reservation == null || currentTime == null) return false
            return reservation.status == TripStatus.SCHEDULED &&
                    reservation.startTime != null &&
                    currentTime.isBefore(reservation.startTime.minus(1, ChronoUnit.HOURS))
        }

        /**
         * Updates the average rating for a driver based on the new incoming rating.
         * Handles nulls and first-time rating scenario.
         */
        fun updateDriverRating(driver: Driver?, newRating: Int?) {
            if (driver == null || newRating == null || newRating !in 1..5) return

            val currentRating = driver.rating ?: 0.0
            val currentCount = driver.ratingCount ?: 0

            driver.rating = if (currentCount == 0) {
                newRating.toDouble()
            } else {
                ((currentRating * currentCount) + newRating) / (currentCount + 1)
            }
            driver.ratingCount = currentCount + 1
        }

        /**
         * Calculates fairness score of a driver based on rating, cancellation rate, and idle time.
         */
        fun calculateDriverFairnessScore(driver: Driver?, currentTime: Instant?): Double {
            if (driver == null || currentTime == null) return 0.0

            val rating = driver.rating ?: 0.0
            val ratingCount = driver.ratingCount ?: 0
            val cancellations = driver.cancellations ?: 0

            val cancelRate = if (ratingCount == 0) 0.0 else cancellations.toDouble() / ratingCount
            val idleTimeMinutes = driver.lastCompletedTripEndTime?.let {
                ChronoUnit.MINUTES.between(it, currentTime).toDouble()
            } ?: 9999.0

            return (rating * 0.4) - (cancelRate * 0.2) + (idleTimeMinutes * 0.2)
        }
    }

    // ------------------------
// ✅ Main function to test core features
// ------------------------
    companion object {
        /*
        ⏱ Time & Space Complexity:
        - isReservationOverlapping(): O(1)
        - isCancellationAllowed(): O(1)
        - updateDriverRating(): O(1)
        - calculateDriverFairnessScore(): O(1)
        - parseInstantSafe(): O(1)
        */
        @JvmStatic
        fun main(args: Array<String>) {
            val now = ReservationService.parseInstantSafe("2025-07-27T10:00:00Z")

            val existingTripStart = ReservationService.parseInstantSafe("2025-07-27T10:00:00Z")
            val existingTripEnd = ReservationService.parseInstantSafe("2025-07-27T10:30:00Z")

            println("✅ Reservation Overlap Tests")

            val newStart1 = ReservationService.parseInstantSafe("2025-07-27T10:30:00Z")
            val newEnd1 = ReservationService.parseInstantSafe("2025-07-27T11:00:00Z")
            println(
                "Case 1 (Expected: false): ${
                    ReservationService.isReservationOverlapping(
                        newStart1,
                        newEnd1,
                        existingTripStart,
                        existingTripEnd
                    )
                }"
            )

            val newStart2 = ReservationService.parseInstantSafe("2025-07-27T09:00:00Z")
            val newEnd2 = ReservationService.parseInstantSafe("2025-07-27T09:45:00Z")
            println(
                "Case 2 (Expected: false): ${
                    ReservationService.isReservationOverlapping(
                        newStart2,
                        newEnd2,
                        existingTripStart,
                        existingTripEnd
                    )
                }"
            )

            val newStart3 = ReservationService.parseInstantSafe("2025-07-27T10:10:00Z")
            val newEnd3 = ReservationService.parseInstantSafe("2025-07-27T10:40:00Z")
            println(
                "Case 3 (Expected: true): ${
                    ReservationService.isReservationOverlapping(
                        newStart3,
                        newEnd3,
                        existingTripStart,
                        existingTripEnd
                    )
                }"
            )

            val newStart4 = ReservationService.parseInstantSafe("2025-07-27T10:00:00Z")
            val newEnd4 = ReservationService.parseInstantSafe("2025-07-27T10:30:00Z")
            println(
                "Case 4 (Expected: true): ${
                    ReservationService.isReservationOverlapping(
                        newStart4,
                        newEnd4,
                        existingTripStart,
                        existingTripEnd
                    )
                }"
            )

            println("\n✅ Cancellation Validation Test")
            val reservation = Reservation(
                driverId = 1,
                startTime = ReservationService.parseInstantSafe("2025-07-27T11:30:00Z"),
                endTime = ReservationService.parseInstantSafe("2025-07-27T12:00:00Z"),
                status = TripStatus.SCHEDULED
            )
            println("Can Cancel (Expected: true): ${ReservationService.isCancellationAllowed(reservation, now)}")

            println("\n✅ Driver Rating Update Test")
            val driver = Driver(
                id = 101,
                rating = 4.5,
                ratingCount = 2,
                cancellations = 1,
                lastCompletedTripEndTime = ReservationService.parseInstantSafe("2025-07-27T08:00:00Z")
            )
            ReservationService.updateDriverRating(driver, newRating = 5)
            println("Updated Avg Rating: ${driver.rating}, Rating Count: ${driver.ratingCount} (Expected ~4.66, 3)")

            println("\n✅ Driver Scoring Test")
            val score = ReservationService.calculateDriverFairnessScore(driver, now)
            println("Driver Score: $score (Higher is better)")

            println("\n✅ Invalid Time Parsing Test")
            val invalidTime = ReservationService.parseInstantSafe("invalid-time-format")
            println("Parsed invalid time (Expected: null): $invalidTime")
        }
    }

}