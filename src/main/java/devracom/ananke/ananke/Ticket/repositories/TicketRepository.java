package devracom.ananke.ananke.Ticket.repositories;

import devracom.ananke.ananke.Ticket.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
