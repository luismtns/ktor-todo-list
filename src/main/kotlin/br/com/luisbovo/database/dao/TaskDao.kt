package br.com.luisbovo.database.dao

import br.com.luisbovo.models.Task
import br.com.luisbovo.models.Tasks
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class TaskDao {

    suspend fun findAll(): List<Task> = dbQuery {
        Tasks.selectAll().map{
            Task(
                id= it[Tasks.id],
                title= it[Tasks.title],
                description= it[Tasks.description],
                isDone= it[Tasks.isDone],
            )
        }
    }
    suspend fun save(task:Task) = dbQuery {
        val insertStatement = Tasks.insert {
            it[id] = task.id
             it[title] = task.title
            it[description] = task.description
             it[isDone] = task.isDone
        }
        insertStatement.resultedValues?.singleOrNull()?.let{
            Task(
                id= it[Tasks.id],
                title= it[Tasks.title],
                description= it[Tasks.description],
                isDone= it[Tasks.isDone],
            )
        }
    }
}

suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction (Dispatchers.IO) { block() }