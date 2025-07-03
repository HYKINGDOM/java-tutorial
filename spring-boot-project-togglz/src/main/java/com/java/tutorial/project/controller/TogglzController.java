package com.java.tutorial.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;

import static com.java.tutorial.project.constant.TogglzFeatures.ALMOST_READY;
import static com.java.tutorial.project.constant.TogglzFeatures.AWESOME;


/**
 * // http://localhost:8080/actuator/togglz
 * // http://localhost:8080/console
 * @author meta 
 */
@RestController
public class TogglzController {

    private FeatureManager featureManager;

    public TogglzController(FeatureManager featureManager) {
        this.featureManager = featureManager;
    }

    @GetMapping("feature1")
    public String feature1() {
        return "Standard old stuff";
    }

    @GetMapping("awesome")
    public String awesome() {
        if (featureManager.isActive(AWESOME)) {
            return "Awesome feature";
        } else {
            return "";
        }
    }

    @GetMapping("almostready")
    public String almostready() {
        if (featureManager.isActive(ALMOST_READY)) {
            return "Almost ready feature";
        } else {
            return "";
        }
    }
}
