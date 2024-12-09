package com.java.tutorial.project.service;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.calculator.EasyScoreCalculator;
import com.java.tutorial.project.domain.Lesson;
import com.java.tutorial.project.domain.Timetable;

import java.util.List;

public class TimetableEasyScoreCalculator implements EasyScoreCalculator<Timetable, HardSoftScore> {

    @Override
    public HardSoftScore calculateScore(Timetable timetable) {
        List<Lesson> lessons = timetable.getLessons();
        int hardScore = 0;
        for (Lesson a : lessons) {
            for (Lesson b : lessons) {
                if (a.getTimeslot() != null && a.getTimeslot().equals(b.getTimeslot()) && Integer.parseInt(
                    a.getId()) < Integer.parseInt(b.getId())) {
                    // A room can accommodate at most one lesson at the same time.
                    if (a.getRoom() != null && a.getRoom().equals(b.getRoom())) {
                        hardScore--;
                    }
                    // A teacher can teach at most one lesson at the same time.
                    if (a.getTeacher().equals(b.getTeacher())) {
                        hardScore--;
                    }
                    // A student can attend at most one lesson at the same time.
                    if (a.getStudentGroup().equals(b.getStudentGroup())) {
                        hardScore--;
                    }
                }
            }
        }
        int softScore = 0;
        // Soft constraints are only implemented in the timefold-quickstarts code
        return HardSoftScore.of(hardScore, softScore);
    }

}