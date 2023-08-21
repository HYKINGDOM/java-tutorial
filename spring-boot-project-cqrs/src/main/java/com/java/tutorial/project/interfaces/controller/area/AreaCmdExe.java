package com.java.tutorial.project.interfaces.controller.area;

import com.java.tutorial.project.application.service.area.AreaCmdSvc;
import com.java.tutorial.project.domain.businessobject.area.AreaCreateBO;
import com.java.tutorial.project.domain.businessobject.area.AreaModifyBO;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * 行政区划表
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/cmd/area")
public class AreaCmdExe {

    private final AreaCmdSvc service;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody AreaCreateBO bo) {
        service.create(bo);
    }

    @PostMapping("/modify")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modify(@Valid @RequestBody AreaModifyBO bo) {
        service.modify(bo);
    }

}
