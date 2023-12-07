package br.com.luisbovo.dto

import br.com.luisbovo.models.Task
import kotlinx.serialization.Serializable

@Serializable
class TaskRequest(
    val title: String,
    val description: String,
){
    fun toTask() = Task(
        title= title,
        description = description
    )
}