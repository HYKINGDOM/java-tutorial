package com.java.tutorial.project.service.impl;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;
import com.java.tutorial.project.domain.Lesson;
import com.java.tutorial.project.domain.Room;
import lombok.Data;

@Data
public class RoomConflictJustification implements ConstraintJustification {


    private Room room;
    private Lesson lesson1;
    private Lesson lesson2;
    private String description;



    public RoomConflictJustification(Room room, Lesson lesson1, Lesson lesson2) {

        String msg = "Room '%s' is used for lesson '%s' for student group '%s' and lesson '%s' for student group '%s' at '%s %s'";

        new RoomConflictJustification(room, lesson1, lesson2,
            String.format(msg, room.getName(), lesson1.getSubject(), lesson1.getStudentGroup(), lesson2.getSubject(),
                    lesson2.getStudentGroup(), lesson1.getTimeslot().getDayOfWeek(),
                    lesson1.getTimeslot().getStartTime()));
    }

    public RoomConflictJustification(Room room, Lesson lesson1, Lesson lesson2, String description) {
        this.room = room;
        this.lesson1 = lesson1;
        this.lesson2 = lesson2;
        this.description = description;
    }
}
