package com.java.tutorial.project.domain;

import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * 课程Timeslot代表授课的时间间隔，例如Monday 10:30 - 11:30或Tuesday 13:30 - 14:30。为简单起见，所有时间段的持续时间相同，午餐或其他休息期间没有时间段。
 * @author meta 
 */
@JsonIdentityInfo(scope = Timeslot.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Data
public class Timeslot {

    @PlanningId
    private String id;

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public Timeslot() {
    }

    public Timeslot(String id, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Timeslot(String id, DayOfWeek dayOfWeek, LocalTime startTime) {
        this(id, dayOfWeek, startTime, startTime.plusMinutes(50));
    }

}

