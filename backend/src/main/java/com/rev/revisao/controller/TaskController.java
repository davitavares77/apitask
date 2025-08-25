package com.rev.revisao.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rev.revisao.model.Task;
import com.rev.revisao.repository.TaskRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {
//iniciar a aplicaçaodoc
@SpringBootApplication
public class RevisaoApplication {
    public static void main(String[] args) {
        SpringApplication.run(RevisaoApplication.class, args);
    }
}


    private final TaskRepository taskRepository;

    // Construtor para injeção do repository
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // GET /api/tasks - Listar todas as tarefas
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // GET /api/tasks/user/{userId} - Listar tarefas por usuário
    @GetMapping("/{userId}")
    public List<Task> getTaskById(@PathVariable String userId) {
        return taskRepository.findTaskByUserId(userId);
    }

    // POST /api/tasks - Criar nova tarefa
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskRepository.save(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    // PUT /api/tasks/{id} - Atualizar tarefa
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Task task = optionalTask.get();
        // Atualize os campos conforme necessário, exemplo:
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.getCompleted());
        Task updatedTask = taskRepository.save(task);
        return ResponseEntity.ok(updatedTask);
    }

    // DELETE /api/tasks/{id} - Deletar tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // PATCH /api/tasks/{id}/toggle - Alternar status da tarefa
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<Task> toggleTaskCompleted(@PathVariable Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Task task = optionalTask.get();
        task.setCompleted(!task.getCompleted()); // Alterna o valor booleano
        Task savedTask = taskRepository.save(task);
        return ResponseEntity.ok(savedTask);
    }
}
