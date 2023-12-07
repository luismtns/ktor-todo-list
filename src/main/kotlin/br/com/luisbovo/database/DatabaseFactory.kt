package br.com.luisbovo.database

import br.com.luisbovo.models.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.jetbrains.exposed.sql.transactions.experimental.*

object DatabaseFactory {
    fun init(
         driverClassName: String = "org.h2.Driver",
         jdbcURL: String = "jdbc:h2:file:./build/db") {
        val database = Database.connect(jdbcURL, driverClassName)
        transaction(database) {
            SchemaUtils.create(Tasks)
        }
    }
}