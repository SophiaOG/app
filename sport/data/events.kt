package data

data class events(
    val event_name: String = "",
    val date_start: String = "",
    val date_end: String = "",
    val event_description: String = "",
    val location: String = "",
    val participants_count: Int = 0,
    val sostav: String = "",
    val sport: String = "",
    val number: Long = 0

) {
    val day: String
        get() = date_start.split(".").getOrNull(0) ?: ""
    val month: String
        get() = date_start.split(".").getOrNull(1) ?: ""
}
