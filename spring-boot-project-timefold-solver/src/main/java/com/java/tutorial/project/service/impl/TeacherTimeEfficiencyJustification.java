package com.java.tutorial.project.service.impl;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;
import com.java.tutorial.project.domain.Lesson;
import lombok.Data;

@Data
public class TeacherTimeEfficiencyJustification implements ConstraintJustification {

    private String teacher;
    private Lesson lesson1;
    private Lesson lesson2;
    private String description;

    public TeacherTimeEfficiencyJustification(String teacher, Lesson lesson1, Lesson lesson2) {

        String msg =
            "Teacher '%s' has 2 consecutive lessons: lesson '%s' for student group '%s' at '%s %s' and lesson '%s' for student group '%s' at '%s %s' (gap)";

        new TeacherTimeEfficiencyJustification(teacher, lesson1, lesson2,
            String.format(msg, teacher, lesson1.getSubject(), lesson1.getStudentGroup(),
                lesson1.getTimeslot().getDayOfWeek(), lesson1.getTimeslot().getStartTime(), lesson2.getSubject(),
                lesson2.getStudentGroup(), lesson2.getTimeslot().getDayOfWeek(), lesson2.getTimeslot().getStartTime()));
    }

    public TeacherTimeEfficiencyJustification(String teacher, Lesson lesson1, Lesson lesson2, String description) {
        this.teacher = teacher;
        this.lesson1 = lesson1;
        this.lesson2 = lesson2;
        this.description = description;
    }
}
