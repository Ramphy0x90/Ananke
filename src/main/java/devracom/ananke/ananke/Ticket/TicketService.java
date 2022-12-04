package devracom.ananke.ananke.Ticket;

import devracom.ananke.ananke.Ticket.models.Category;
import devracom.ananke.ananke.Ticket.models.Priority;
import devracom.ananke.ananke.Ticket.models.Ticket;
import devracom.ananke.ananke.Ticket.repositories.CategoryRepository;
import devracom.ananke.ananke.Ticket.repositories.PriorityRepository;
import devracom.ananke.ananke.Ticket.repositories.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TicketService {
    private final TicketRepository ticketRepository;
    private final CategoryRepository categoryRepository;
    private final PriorityRepository priorityRepository;

    public TicketService(TicketRepository ticketRepository,
                         CategoryRepository categoryRepository,
                         PriorityRepository priorityRepository) {
        this.ticketRepository = ticketRepository;
        this.categoryRepository = categoryRepository;
        this.priorityRepository = priorityRepository;
    }

    /**
     * Get all tickets
     * @return List<Ticket>
     */
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    /**
     * Get all ticket priorities
     * @return List<Priority>
     */
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Get all ticket priorities
     * @return List<Priority>
     */
    public List<Priority> getPriorities() {
        return priorityRepository.findAll();
    }
}
