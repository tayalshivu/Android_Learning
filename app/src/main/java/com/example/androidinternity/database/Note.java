package com.example.androidinternity.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String email;

    private int priority;

    public Note(String name, String email, int priority) {
        this.name = name;
        this.email = email;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
