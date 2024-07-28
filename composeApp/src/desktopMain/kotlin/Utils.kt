fun checkIPAddress(input: String): Boolean {
    val regex = Regex("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")
    return regex.matches(input)
}