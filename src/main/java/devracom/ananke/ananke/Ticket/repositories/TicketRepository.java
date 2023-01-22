package devracom.ananke.ananke.Ticket.repositories;

import devracom.ananke.ananke.Ticket.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t FROM Ticket t WHERE t.assignee.id = ?1")
    List<Ticket> findByUserId(Long id);
}
