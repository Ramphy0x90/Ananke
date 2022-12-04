package devracom.ananke.ananke.Ticket.repositories;

import devracom.ananke.ananke.Ticket.models.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
