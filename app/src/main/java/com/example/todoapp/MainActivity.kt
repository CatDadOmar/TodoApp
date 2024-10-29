package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListApp()
        }
    }
}

@Composable
fun ToDoListApp(taskViewModel: TaskViewModel = viewModel()) {
    var taskName by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Things to do list", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = taskName,
            onValueChange = { taskName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            decorationBox = { innerTextField ->
                if (taskName.isEmpty()) {
                    Text("Enter task name")
                }
                innerTextField()
            }
        )

        Button(
            onClick = {
                if (taskName.isNotBlank()) {
                    taskViewModel.addTask(taskName)
                    taskName = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TaskList(taskViewModel.tasks, taskViewModel)
    }
}

@Composable
fun TaskList(tasks: List<Task>, taskViewModel: TaskViewModel) {
    Column {
        tasks.forEach { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = { taskViewModel.toggleTaskCompletion(task) }
                )
                Text(
                    text = task.name,
                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                )
                IconButton(onClick = { taskViewModel.removeTask(task) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete Task")
                }
            }
        }
    }
}