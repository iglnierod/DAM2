package com.example.todoapp.addtask.domain

import com.example.todoapp.addtask.data.TaskRepository
import com.example.todoapp.addtask.ui.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(private val taskRepository: TaskRepository){

   operator fun invoke(): Flow<List<TaskModel>> = taskRepository.tasks
}