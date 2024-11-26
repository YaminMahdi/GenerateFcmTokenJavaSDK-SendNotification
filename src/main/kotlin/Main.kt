import com.google.auth.oauth2.GoogleCredentials
import com.google.common.io.Resources
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import java.io.File

//TIP Press <shortcut raw="SHIFT"/> twice to open the Search Everywhere dialog and type <b>show whitespaces</b>,
// then press <shortcut raw="ENTER"/>. You can now see whitespace characters in your code.
fun main() {
    sendNotification()
}

fun sendNotification(){
    runBlocking {
        val responseBody =
            HttpClient(CIO)
                .post("https://fcm.googleapis.com/v1/projects/omekitchen-6bc2f/messages:send") {
                    header(HttpHeaders.Authorization, getAccessToken())
                    setBody(Gson().toJson(FcmBody()))
                }.bodyAsText()
        println("sendNotification: $responseBody")
    }
}

fun getUnExpiredToken(): String?{
    val tokenJson = try{
        File("token.json").readText()
    }catch (e: Exception){
        File("token.json").writeText("") ; return null
    }
    val token = Gson().fromJson(tokenJson, Token::class.java) ?: return null

    return if(System.currentTimeMillis() < token.expirationTime)
         token.accessToken
    else
        null
}


fun getAccessToken(): String{
    val oldToken =  getUnExpiredToken()

    return if(oldToken == null){
        val json= Resources.getResource("fcm.json").openStream()
        val scopes = listOf("https://www.googleapis.com/auth/firebase.messaging")
        val googleCredentials  =
            GoogleCredentials
                .fromStream(json)
                .createScoped(scopes)
        googleCredentials.refreshIfExpired()
        googleCredentials.refreshAccessToken()

        val newToken = Token(googleCredentials.accessToken.tokenValue,googleCredentials.accessToken.expirationTime.time)
        val newTokenJson = Gson().toJson(newToken)
        val file = File("token.json")
        file.writeText(newTokenJson)

        println("getAccessToken suc: \n${googleCredentials.accessToken.tokenValue}")
        "Bearer ${googleCredentials.accessToken.tokenValue}"
    }
    else {
        println("getAccessToken old: \n${oldToken}")
        "Bearer $oldToken"
    }
}