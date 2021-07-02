package by.nintendo.repository;

import by.nintendo.entity.WorkedHoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerHoursRepository extends JpaRepository<WorkedHoursEntity,Long> {
}
