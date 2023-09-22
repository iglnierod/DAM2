/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Task;
import model.TaskModel;
import view.TaskView;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class TaskManagerController {

    private final TaskModel model;
    private final TaskView view;

    public TaskManagerController(TaskModel model, TaskView view) {
        this.model = model;
        this.view = view;
        view.addButtonActionListener(addTaskListener());
    }

    private ActionListener addTaskListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description = view.getTaskDescription();
                if (!description.isEmpty()) {
                    Task task = new Task(description);
                    model.addTask(task);
                    List<Task> tasks = model.getAllTasks();
                    view.updateTaskList(tasks);
                }
            }
        };
        return al;
    }

    public void startApp() {
        this.view.setVisible(true);
    }
}
