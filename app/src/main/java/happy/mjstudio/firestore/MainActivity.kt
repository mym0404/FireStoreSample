package happy.mjstudio.firestore

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.Source

class MainActivity : AppCompatActivity() {

    private val TAG =  MainActivity::class.java.simpleName

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()

        db.collection("Room").document("A").get(Source.DEFAULT)
            .addOnSuccessListener {
                debugE(it.data)
                val room = it.toObject(Room::class.java)
                debugE(room)
            }.addOnFailureListener {
                debugE(TAG,it)
            }

        db.collection("Room").get()
            .addOnSuccessListener {

            }.addOnFailureListener {
                debugE(TAG,it)
            }
    }
}


data class Room(
    val name : String = "",
    @PropertyName("Users")
    val users : List<String> = listOf()
)

fun debugE(tag: String, message: Any?) {
    if (BuildConfig.DEBUG)
        Log.e(tag, "\uD83C\uDF40" + message.toString())
}

fun debugE(message: Any?) {

    debugE("DEBUG", message)
}