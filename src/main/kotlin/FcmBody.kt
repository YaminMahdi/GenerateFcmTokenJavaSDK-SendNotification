data class FcmBody(
    val message: Message = Message()
) {
    data class Message(
        val data: Data = Data(),
//        val notification: Notification = Notification(),
        val topic: String = "test", // your_topic_name
//        val token: String = "bk3RNwTe3H0:CI2k_HHwgIpoDKCIZvvDMExUdFQ3P1..." // your_token
    ) {
        data class Data(
            val title: String = "Hello From Mahdi", // your_title_here
            val body: String = "Nothing to say", // your_body_here
            val id: String = "", // your_assign_order_id
            val image: String = "", // your_picture_url
            val latitude: String = "", // your_latitude
            val longitude: String = "" // your_longitude
        )
        data class Notification(
            val title: String = "", // your_title_here
            val body: String = "" // your_body_here
        )
    }
}