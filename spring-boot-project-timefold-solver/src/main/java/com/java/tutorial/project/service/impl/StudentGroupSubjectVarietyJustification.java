package com.java.tutorial.project.service.impl;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;
import com.java.tutorial.project.domain.Lesson;
import lombok.Data;

@Data
public class StudentGroupSubjectVarietyJustification implements ConstraintJustification {

    private String studentGroup;
    private Lesson lesson1;
    private Lesson lesson2;
    private String description;

    public StudentGroupSubjectVarietyJustification(String studentGroup, Lesson lesson1, Lesson lesson2) {
        String msg = "Student Group '%s' has two consecutive lessons on '%s' at '%s %s' and at '%s %s'";
        new StudentGroupSubjectVarietyJustification(studentGroup, lesson1, lesson2,
            String.format(msg, studentGroup, lesson1.getSubject(), lesson1.getTimeslot().getDayOfWeek(),
                lesson1.getTimeslot().getStartTime(), lesson2.getTimeslot().getDayOfWeek(),
                lesson2.getTimeslot().getStartTime()));
    }

    public StudentGroupSubjectVarietyJustification(String studentGroup, Lesson lesson1, Lesson lesson2,
        String description) {
        this.studentGroup = studentGroup;
        this.lesson1 = lesson1;
        this.lesson2 = lesson2;
        this.description = description;
    }
}