package example.bryan.com.docusignhackathon_forestapp

import android.util.Log
import com.google.gson.Gson
import example.bryan.com.docusignhackathon_forestapp.Model.Petition
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*

interface AppService {
    @GET("petition")
    fun getAllPetitions(): Observable<ArrayList<Petition>>

    @GET("campreservation")
    fun getCampReservationDocusign(
            @Query("name") name: String,
            @Query("email") email: String,
            @Query("park") park: String,
            @Query("guests") numGuests: Int,
            @Query("start_date") startDate: Date,
            @Query("end_date") endDate: Date
    ): Observable<String>

    @GET("donation")
    fun getDonationDocusign (
            @Query("name") name: String,
            @Query("email") email: String,
            @Query("park") park: String,
            @Query("amount") donationAmount: Float
    ): Observable<String>

    @GET("petition_docusign")
    fun getPetitionDocusign (
            @Query("name") name: String,
            @Query("email") email: String,
            @Query("petitionID") petitionID: Int
    ): Observable<String>

    companion object Factory {
        val baseURL = ""    //TODO
        fun create(): AppService = Retrofit.Builder().apply {
                baseUrl(baseURL)
                addConverterFactory(GsonConverterFactory.create())
                addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            }.build().create(AppService::class.java)

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
}