package by.nintendo.controller;

import by.nintendo.Response;
import by.nintendo.model.DepartmentModel;
import by.nintendo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @DeleteMapping(path = "/{id}")
    public Response<DepartmentModel> deleteDepartmentById(@PathVariable("id") Long id){
Response<DepartmentModel> response=new Response<>();
response.setStatus("Ok");
        DepartmentModel departmentModel = departmentService.deleteById(id);
        response.setEntities(Collections.singletonList(departmentModel));
return response;


    }
}
