package org.student.unit.task.controller;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.student.unit.task.model.HealthCheckResponse;
import org.student.unit.task.model.HealthStatus;
import org.student.unit.task.service.health.HealthCheckService;

@Controller
@RequestMapping("/health")
public class HealthCheckController
{
    @Resource(name = "healthCheckServiceImpl")
    HealthCheckService healthCheckService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<HealthCheckResponse> checkSystemStatus() {
        HealthCheckResponse response = healthCheckService.checkSystemsAvailability();
        if (HealthStatus.UNHEALTHY.getStatus().equals(response.getOverAllStatus()))
        {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

