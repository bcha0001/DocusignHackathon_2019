package example.bryan.com.docusignhackathon_forestapp

import android.util.Log
import example.bryan.com.docusignhackathon_forestapp.Model.Petition
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*
import kotlin.collections.ArrayList

object MockService {
    fun getAllPetitions(): Observable<ArrayList<Petition>> {
        val petitionSubject = ReplaySubject.create<ArrayList<Petition>>()
        val testArray: ArrayList<Petition> = ArrayList()
        val description = "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum "
        for (i in 0..100) {
            testArray.add(Petition(i, description, false))
        }
        petitionSubject.onNext(testArray)
        return petitionSubject.hide()
    }

    fun getCampReservationDocusign(
            name: String,
            email: String,
            park: String,
            numGuests: Int,
            startDate: Date,
            endDate: Date
    ): Observable<String> {
        val campReservationSubject = ReplaySubject.create<String>()
        campReservationSubject.onNext("https://www.facebook.com")
        return campReservationSubject
    }

    fun getDonationDocusign (
            name: String,
            email: String,
            park: String,
            donationAmount: Float
    ): Observable<String> {
        val campReservationSubject = ReplaySubject.create<String>()
        campReservationSubject.onNext("https://www.facebook.com")
        return campReservationSubject
    }

    fun getPetitionDocusign (
            name: String,
            email: String,
            petitionID: Int
    ): Observable<String> {
        val petitionSubject = ReplaySubject.create<String>()
        petitionSubject.onNext("https://www.facebook.com")
        return petitionSubject
    }

    fun create() {

    }

    fun logPetition(petitionList: ArrayList<Petition>) {
        for(x in 0 .. petitionList.size) {
            Log.d("Petition", petitionList[x].toString())
        }
    }

    fun logCampReservation(name: String, email: String, park: String, numGuests: Int, startDate: Date, endDate: Date) {
        Log.d("Name", name)
        Log.d("Email", email)
        Log.d("Park", park)
        Log.d("NumGuests", numGuests.toString())
        Log.d("StartDate", startDate.toString())
        Log.d("EndDate", endDate.toString())
    }

}