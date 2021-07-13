package by.nintendo.service;

import by.nintendo.entity.DepartmentEntity;
import by.nintendo.entity.WorkerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;

    @Test
    void createOrUpdate() {
        List<WorkerEntity> list = new ArrayList<>();
        WorkerEntity w1 = new WorkerEntity();
        WorkerEntity w2 = new WorkerEntity();
        list.add(w1);
        list.add(w2);
        DepartmentEntity d = new DepartmentEntity();
        d.setName("TEST");
        d.setWorkers(list);
        assertEquals(departmentService.getAll().size(), 2);
        departmentService.createOrUpdate(d);
        assertNotNull(departmentService.getAll());
        assertEquals(departmentService.getAll().size(), 3);
        DepartmentEntity departmentEntity = new DepartmentEntity(1L, "Name", null);
        assertEquals(departmentService.getById(1L).getName(), "Some");
        departmentService.createOrUpdate(departmentEntity);
        assertEquals(departmentService.getAll().size(), 3);
        assertEquals(departmentService.getById(1L).getName(), "Name");
    }

    @Test
    void getAll() {
        assertNotNull(departmentService.getAll());
        assertEquals(departmentService.getAll().size(), 2);
        departmentService.createOrUpdate(new DepartmentEntity(null,"Some",null));
        assertEquals(departmentService.getAll().size(), 3);
        departmentService.deleteById(3L);
        assertEquals(departmentService.getAll().size(), 2);
    }

    @Test
    void getById() {
        assertNotNull(departmentService.getAll());
        assertEquals(departmentService.getById(1L).getName(), "Develop");
        assertEquals(departmentService.getById(1L).getName(),"Develop");
        departmentService.createOrUpdate(new DepartmentEntity(1L,"Some",null));
        assertEquals(departmentService.getById(1L).getName(),"Some");
    }

    @Test
    void deleteById() {
        assertNotNull(departmentService.getAll());
        assertEquals(departmentService.getAll().size(),3);
        departmentService.deleteById(1L);
        assertEquals(departmentService.getAll().size(),2);
    }
}