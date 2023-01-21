package devracom.ananke.ananke.Ticket.repositories;

import devracom.ananke.ananke.Ticket.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
}
