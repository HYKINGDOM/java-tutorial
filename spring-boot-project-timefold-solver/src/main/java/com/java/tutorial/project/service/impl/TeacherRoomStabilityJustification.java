package com.java.tutorial.project.service.impl;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;
import com.java.tutorial.project.domain.Lesson;
import lombok.Data;

@Data
public class TeacherRoomStabilityJustification implements ConstraintJustification {

    private String teacher;
    private Lesson lesson1;
    private Lesson lesson2;
    private String description;

    public TeacherRoomStabilityJustification(String teacher, Lesson lesson1, Lesson lesson2) {

        String msg = "Teacher '%s' has two lessons in different rooms: room '%s' at '%s %s' and room '%s' at '%s %s'";

        new TeacherRoomStabilityJustification(teacher, lesson1, lesson2,
            String.format(msg, teacher, lesson1.getRoom(), lesson1.getTimeslot().getDayOfWeek(),
                lesson1.getTimeslot().getStartTime(), lesson2.getRoom(), lesson2.getTimeslot().getDayOfWeek(),
                lesson2.getTimeslot().getStartTime()));
    }

    public TeacherRoomStabilityJustification(String teacher, Lesson lesson1, Lesson lesson2, String description) {
        this.teacher = teacher;
        this.lesson1 = lesson1;
        this.lesson2 = lesson2;
        this.description = description;
    }
}
