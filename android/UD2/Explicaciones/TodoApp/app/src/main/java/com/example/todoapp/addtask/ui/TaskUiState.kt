package com.example.todoapp.addtask.ui

sealed interface TaskUiState{

    object Loading: TaskUiState

    data class Error(val throwable: Throwable): TaskUiState

    data class Success(val tasks: List<TaskModel>): TaskUiState
}