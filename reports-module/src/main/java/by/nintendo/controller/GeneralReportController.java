package by.nintendo.controller;

import by.nintendo.entity.DepartmentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestTemplate;
import by.nintendo.model.*;
import by.nintendo.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import by.nintendo.mapper.DepartmentMapper;

@Slf4j
@RestControllerAdvice
@RequestMapping(path = "/report")
public class GeneralReportController {
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping
    public void getAll() {
        String url = "http://localhost:8080/department";
        Response<?> response = restTemplate.getForObject(url, Response.class);

    }
}
