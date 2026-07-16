import com.google.gson.Gson
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.util.*

/**
 * Convert any object to Json String
 */
fun toJsonString(obj: Any?): String {
    return if (obj != null) Gson().toJson(obj) else ""
}

/**
 * To convert json string to object
 */
inline fun <reified T> fromJsonString(
    jsonElement: JsonElement?
): T? {
    return try {
        Gson().fromJson<T>(jsonElement.toString(), T::class.java as Type)
    } catch (e: Throwable) {
        null
    }
}

fun logger(vararg params: String) {
    var logger = "logger-> "
    params.forEach { param ->
        logger += "$param, "
    }
    println(logger)
}


fun main() {
    while (true) {
        val myObj = Scanner(System.`in`) // Create a Scanner object
        println()
        println("-------------------------------")
        println("Enter year")
        try {
            val year = myObj.nextLine()
            isLeapYear(year.toInt())
        } catch (e: Exception) {
            println("Please enter a valid year")
        }
    }
}

fun isLeapYear(year: Int) {
    if (year % 100 == 0) {
        if (year % 400 == 0) {
            println("$year is a leap year")
        } else {
            println("$year is not a leap year")
        }
    } else if (year % 4 == 0) {
        println("$year is a leap year")
    } else {
        println("$year is not a leap year")
    }
}

// Helper function to reverse a portion of the array in-place
fun reverse(start: Int, end: Int, nums: IntArray) {
    var left = start
    var right = end
    while (left < right) {
        // Swap elements at left and right indices
        val temp = nums[left]
        nums[left] = nums[right]
        nums[right] = temp
        left++
        right--
    }
}