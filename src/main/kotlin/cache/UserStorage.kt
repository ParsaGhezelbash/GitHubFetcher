package cache

import model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

object UserStorage {
    private const val FILE_NAME = "src/main/resources/users.json"
    private val gson = Gson()

    private fun getRootFilePath(): String {
        return System.getProperty("user.dir") + File.separator + FILE_NAME
    }

    fun saveUsers(users: List<User>) {
        val jsonString = gson.toJson(users)
        val filePath = getRootFilePath()
        println(filePath)
        File(filePath).writeText(jsonString)
    }

    fun loadUsers(): List<User> {
        val filePath = getRootFilePath()
        val file = File(filePath)
        if (!file.exists()) return emptyList()

        val jsonString = file.readText()
        val type = object : TypeToken<List<User>>() {}.type
        return gson.fromJson(jsonString, type)
    }
}