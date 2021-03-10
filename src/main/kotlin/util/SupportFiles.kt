package util

/**
 * This method is used for explaining things in a better manner in our code.
 */
fun exampleOf(title: String, action: () -> Unit) {
    println("\n$title \n")
    action()
}