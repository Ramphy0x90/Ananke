package devracom.ananke.ananke.Ticket;

import devracom.ananke.ananke.Ticket.exceptions.TicketNotFoundException;
import devracom.ananke.ananke.Ticket.models.Category;
import devracom.ananke.ananke.Ticket.models.Priority;
import devracom.ananke.ananke.Ticket.models.Status;
import devracom.ananke.ananke.Ticket.models.Ticket;
import devracom.ananke.ananke.Ticket.repositories.CategoryRepository;
import devracom.ananke.ananke.Ticket.repositories.PriorityRepository;
import devracom.ananke.ananke.Ticket.repositories.StatusRepository;
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
    private final StatusRepository statusRepository;

    public TicketService(TicketRepository ticketRepository,
                         CategoryRepository categoryRepository,
                         PriorityRepository priorityRepository,
                         StatusRepository statusRepository) {
        this.ticketRepository = ticketRepository;
        this.categoryRepository = categoryRepository;
        this.priorityRepository = priorityRepository;
        this.statusRepository = statusRepository;
    }

    /**
     * Get all tickets
     * @return List<Ticket>
     */
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    /**
     * Get ticket by given id
     * @param id Ticket id
     * @return Ticket
     */
    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id).orElseThrow(
                () -> new TicketNotFoundException(String.format("Ticket with ID: %s not found", id))
        );
    }

    /**
     * Create a new ticket with given object
     * and return the new ticket
     * @param ticket ticket data
     * @return Ticket
     */
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
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

    /**
     * Get all ticket states
     * @return List<Status>
     */
    public List<Status> getStates() { return statusRepository.findAll(); }
}
