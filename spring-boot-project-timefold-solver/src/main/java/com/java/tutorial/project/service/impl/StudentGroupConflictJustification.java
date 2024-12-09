package com.java.tutorial.project.service.impl;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;
import com.java.tutorial.project.domain.Lesson;
import lombok.Data;

@Data
public class StudentGroupConflictJustification implements ConstraintJustification {

    private String studentGroup;
    private Lesson lesson1;
    private Lesson lesson2;
    private String description;

    public StudentGroupConflictJustification(String studentGroup, Lesson lesson1, Lesson lesson2) {
        String msg = "Student group '%s' has lesson '%s' and lesson '%s' at '%s %s'";

        new StudentGroupConflictJustification(studentGroup, lesson1, lesson2,

            String.format(msg, studentGroup, lesson1.getSubject(), lesson2.getSubject(),
                lesson1.getTimeslot().getDayOfWeek(), lesson1.getTimeslot().getStartTime()));
    }

    public StudentGroupConflictJustification(String studentGroup, Lesson lesson1, Lesson lesson2, String description) {
        this.studentGroup = studentGroup;
        this.lesson1 = lesson1;
        this.lesson2 = lesson2;
        this.description = description;
    }
}
