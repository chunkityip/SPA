package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    // One project to Many tasks
    /**
     * When you load a Project, you can access all its Tasks via the tasks collection
     * When you delete a Project, all its Tasks are automatically deleted (due to cascade = CascadeType.ALL)
     * If you remove a Task from the Project's task list, that Task is deleted from the database (due to orphanRemoval = true)
     */
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public void addTask(Task task) {
        tasks.add(task);
        task.setProject(this);
    }

    public void removeTask(Task task){
        tasks.remove(task);
        task.setProject(this);
    }


}
