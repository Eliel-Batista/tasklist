package com.example.TaskList.controller;

import com.example.TaskList.model.Task;
import com.example.TaskList.services.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.save(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.update(id, task);
    }

    @PutMapping("/{taskId}/assign-employee/{employeeId}")
    public ResponseEntity<String> assignTaskToEmployee(
            @PathVariable Long taskId,
            @PathVariable Long employeeId) {

        try {
            taskService.assignTaskToEmployee(taskId, employeeId);
            return ResponseEntity.ok("Tarefa vinculada com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao vincular tarefa.");
        }
    }
}