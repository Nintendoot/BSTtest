package by.nintendo.repository;


import by.nintendo.entity.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkersRepository extends JpaRepository<WorkerEntity,Long> {
}
