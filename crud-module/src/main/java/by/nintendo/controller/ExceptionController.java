package by.nintendo.controller;

import by.nintendo.exception.DepartmentAlreadyExistException;
import by.nintendo.exception.DepartmentNotFoundException;
import by.nintendo.exception.IncorrectDataException;
import by.nintendo.exception.WorkerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(WorkerNotFoundException.class)
    public String workerNotFound(RuntimeException ex) {
        log.error("WorkerNotFoundException" + ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public String departmentNotFound(RuntimeException ex) {
        log.error("DepartmentNotFoundException" + ex.getMessage());
        return ex.getMessage();
    }
    @ExceptionHandler(DepartmentAlreadyExistException.class)
    public String departmentAlreadyExist(DepartmentAlreadyExistException ex) {
        log.error("DepartmentAlreadyException" + ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(IncorrectDataException.class)
    public String incorrectedData(IncorrectDataException ex) {
        log.error("IncorrectDataException" + ex.getMessage());
        return ex.getMessage();
    }


}