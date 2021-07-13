package by.nintendo.service;

import by.nintendo.entity.WorkedHoursEntity;
import by.nintendo.entity.WorkerEntity;
import by.nintendo.repository.WorkersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class WorkerHoursServiceTest {

    @Autowired
    private WorkerHoursService workerHoursService;
    @Autowired
    private WorkersRepository workersRepository;

    @Test
    void createOrUpdate() {
        WorkerEntity workerEntity=new WorkerEntity(4L,"Oleg","Pushkin","Java",null,null);

        assertNotNull(workerHoursService.getAll());
        assertEquals(workerHoursService.getAll().size(), 4);
        WorkedHoursEntity workedHoursEntity=new WorkedHoursEntity();
        workedHoursEntity.setWorker(workerEntity);
        workerHoursService.createOrUpdate(workedHoursEntity);
        assertNotNull(workerHoursService.getAll());
        assertEquals(workerHoursService.getAll().size(), 5);
    }

    @Test
    void getAll() {
        WorkerEntity workerEntity=new WorkerEntity(1L,"Oleg","Pushkin","Java",null,null);
        assertNotNull(workerHoursService.getAll());
        assertEquals(workerHoursService.getAll().size(), 7);
        workersRepository.getById(1L);
        workerHoursService.createOrUpdate(new WorkedHoursEntity(null,null,null,workerEntity));
        assertEquals(workerHoursService.getAll().size(), 8);

    }

    @Test
    void getById() {
        assertNotNull(workerHoursService.getAll());
        assertEquals(workerHoursService.getById(1L).size(), 4);
        workerHoursService.deleteById(1L);
        assertEquals(workerHoursService.getById(1L).size(),0);
    }

    @Test
    void deleteById() {
        assertNotNull(workerHoursService.getAll());
        assertEquals(workerHoursService.getById(4L).size(), 2);
        workerHoursService.deleteById(4L);
        assertEquals(workerHoursService.getById(1L).size(),0);
    }
}