package com.example.todoapp.addtask.data

import com.example.todoapp.addtask.ui.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao){

    //QUERY
    val tasks: Flow<List<TaskModel>> = taskDao.getTasks().map { items-> items.map{ TaskModel(it.id,it.task,it.selected) }}

    //AÑADIR
    suspend fun add(taskModel: TaskModel){
        taskDao.addTask(taskModel.toData())
    }

    //UPDATE
    suspend fun update(taskModel: TaskModel){
        taskDao.updateTask(taskModel.toData())
    }

    //ELIMINAR
    suspend fun delete(taskModel: TaskModel){
        taskDao.deleteTask(taskModel.toData())
    }

    //FUNCION AÑADIDA CHISTERA
    fun TaskModel.toData(): TaskEntity{
        return TaskEntity(this.id, this.task, this.selected)
    }

}