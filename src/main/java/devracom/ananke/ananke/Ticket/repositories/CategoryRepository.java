package devracom.ananke.ananke.Ticket.repositories;

import devracom.ananke.ananke.Ticket.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
