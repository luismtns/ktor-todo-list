package br.com.luisbovo.repositories

import br.com.luisbovo.database.dao.TaskDao
import br.com.luisbovo.models.Task

class TasksRepository (
    private val dao: TaskDao = TaskDao()
){

    suspend fun tasks() = dao.findAll()

    suspend fun save(task: Task): Task? {
        return dao.save(task)
    }

}