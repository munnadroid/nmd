package com.awecode.nmd.models

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by munnadroid on 5/30/17.
 */

@PaperParcel
data class Specialists(val name: String, val id: Int, val totalCount: Int) : PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelSpecialists.CREATOR
    }
}


@PaperParcel
data class Doctor(val name: String, val hospitalName: String, val email: String, val designation: String,
                  val education: String, val experience: String, val profilePic: String, val department: String,
                  val address: String)
    : PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelDoctor.CREATOR
    }
}


@PaperParcel
data class Hospital(val name: String, val address: String, val telephone: String,
                    val latitude: Double, val longitude: Double)
    : PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelHospital.CREATOR
    }
}