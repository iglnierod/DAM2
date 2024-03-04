package com.example.todoapp.addtask.ui


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.addtask.domain.AddTaskUseCase
import com.example.todoapp.addtask.domain.DeleteTaskUseCase
import com.example.todoapp.addtask.domain.GetTaskUseCase
import com.example.todoapp.addtask.domain.UpdateTaskUseCase
import com.example.todoapp.addtask.ui.TaskUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    getTaskUseCase: GetTaskUseCase

    ): ViewModel(){

     val uiState: StateFlow<TaskUiState> = getTaskUseCase().map(::Success)
         .catch { TaskUiState.Error(it)}
         .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TaskUiState.Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _task = mutableStateListOf<TaskModel>()
    val task: List<TaskModel> = _task

    fun onDialogClose(){
        _showDialog.value = false
    }

    fun onTasksCreated(task: String){
        //_task.add(TaskModel(task = task))
        viewModelScope.launch { addTaskUseCase(TaskModel(task = task)) }
        _showDialog.value = false
    }

    fun onShowDialogClick(){
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel){
        viewModelScope.launch {
            updateTaskUseCase(taskModel.copy(selected = !taskModel.selected))
        }
    }

    fun onItemRemoved(taskModel: TaskModel){
        viewModelScope.launch {
            deleteTaskUseCase(taskModel)
        }
    }

}