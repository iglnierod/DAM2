package com.example.todoapp.addtask.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle


@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun TasksScreen(taskViewModel: TaskViewModel){
    val showDialog: Boolean by taskViewModel.showDialog.observeAsState(initial = false)


    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uiState by produceState<TaskUiState>(
        initialValue = TaskUiState.Loading ,
        key1 = lifecycle ,
        key2 = taskViewModel
    ){
       lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED){
           taskViewModel.uiState.collect{ value = it}
       }
    }

    when(uiState){
        //ERROR
        is TaskUiState.Error -> { }

        //LOADING
        TaskUiState.Loading -> { CircularProgressIndicator() }

        //CORRECTO
        is TaskUiState.Success -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ){
                AddTasksDialog(
                    showDialog,
                    onDismiss = { taskViewModel.onDialogClose() },
                    onTaskAdded = { taskViewModel.onTasksCreated(it)}
                )
                FabDialog(taskViewModel, Modifier.align(Alignment.BottomEnd))
                TaskList((uiState as TaskUiState.Success).tasks,taskViewModel)
            }
        }
    }
}

@Composable
fun TaskList(tasks: List<TaskModel>, taskViewModel: TaskViewModel){

    LazyColumn(Modifier.fillMaxWidth()){
        items(tasks , key = {it.id} ){ task ->
            ItemTask(task, taskViewModel)
        }
    }
}

@Composable
fun ItemTask(taskModel: TaskModel, taskViewModel: TaskViewModel ){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 9.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { taskViewModel.onItemRemoved(taskModel) }
                )
            }
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = taskModel.task)
            Checkbox(checked = taskModel.selected, onCheckedChange = {taskViewModel.onCheckBoxSelected(taskModel)} )
        }
    }
}

@Composable
fun FabDialog(taskViewModel: TaskViewModel, modifier: Modifier){
    FloatingActionButton(
        onClick = {
            taskViewModel.onShowDialogClick()
        }, modifier = modifier
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription ="Añadir Tarea" )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTasksDialog(show: Boolean, onDismiss:() -> Unit, onTaskAdded:(String)-> Unit){

    var myTask by remember{  mutableStateOf("") }

    if(show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(18.dp)
            ) {
                Text(
                    text = "Añadir Tarea",
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(Modifier.size(16.dp))
                TextField(value = myTask, onValueChange = { myTask = it}, singleLine = true, maxLines = 1 )
                Spacer(Modifier.size(16.dp))
                Button(onClick = {
                        onTaskAdded(myTask)
                        myTask = "" }, Modifier.fillMaxWidth()
                ) {
                    Text(text = "Añadir Tarea")
                }
            }
        }
    }
}

