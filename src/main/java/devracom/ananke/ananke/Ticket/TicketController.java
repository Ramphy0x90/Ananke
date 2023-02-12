package devracom.ananke.ananke.Ticket;

import devracom.ananke.ananke.Ticket.models.Category;
import devracom.ananke.ananke.Ticket.models.Priority;
import devracom.ananke.ananke.Ticket.models.Status;
import devracom.ananke.ananke.Ticket.models.Ticket;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Returns all tickets
     * @return List<Ticket>
     */
    @Operation(summary = "Get all tickets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Authorization denied", content = @Content),
    })
    @GetMapping(path = "/all")
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    /**
     * Returns all tickets for a given user
     * @return List<Ticket>
     */
    @Operation(summary = "Get all tickets for user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Authorization denied", content = @Content),
    })
    @GetMapping(path = "/all/{id}")
    public List<Ticket> getTicketsByUser(@PathVariable("id") Long id) {
        return ticketService.getTicketsByUser(id);
    }

    /**
     * Returns a ticket by given id
     * @param id ticket id
     * @return Ticket
     */
    @Operation(summary = "Get ticket by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Authorization denied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ticketService.getTicket(id), HttpStatus.OK);
    }

    /**
     * Create a new ticket and returns the ticket created
     * @param ticket ticket data
     * @return Ticket
     */
    @Operation(summary = "Create new ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created new ticket"),
            @ApiResponse(responseCode = "401", description = "Authorization denied", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    })
    @PostMapping(path = "/create")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        return new ResponseEntity<>(ticketService.createTicket(ticket), HttpStatus.CREATED);
    }

    /**
     * Update a ticket by given ticket id and data to update
     * @param ticket ticket data
     * @return Ticket
     */
    @Operation(summary = "Update ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created new ticket"),
            @ApiResponse(responseCode = "401", description = "Authorization denied", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    })
    @PutMapping(path = "/update")
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket) {
        return new ResponseEntity<>(ticketService.updateTicket(ticket), HttpStatus.OK);
    }

    /**
     * Returns all categories
     * @return List<Category>
     */
    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Authorization denied", content = @Content),
    })
    @GetMapping(path = "/category/all")
    public List<Category> getCategories() {
        return ticketService.getCategories();
    }

    /**
     * Returns all priorities
     * @return List<Priority>
     */
    @Operation(summary = "Get all priorities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Authorization denied", content = @Content),
    })
    @GetMapping(path = "/priority/all")
    public List<Priority> getPriorities() {
        return ticketService.getPriorities();
    }

    /**
     * Returns all states
     * @return List<Status>
     */
    @Operation(summary = "Get all states")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Authorization denied", content = @Content),
    })
    @GetMapping(path = "/status/all")
    public List<Status> getStates() { return ticketService.getStates(); }
}
