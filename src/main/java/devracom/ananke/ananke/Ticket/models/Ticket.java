package devracom.ananke.ananke.Ticket.models;

import devracom.ananke.ananke.User.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;
    @ManyToOne
    @JoinColumn(name = "provisional_assignee_id")
    private User provisionalAssignee;
    @ManyToOne
    @JoinColumn(name = "priority_id")
    private Priority priority;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @CreationTimestamp
    private Date creationDate;
    @UpdateTimestamp
    private Date lastEditDate;
    private Date closeDate;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() { return status; }

    public User getAssignee() {
        return assignee;
    }

    public User getProvisionalAssignee() {
        return provisionalAssignee;
    }

    public Priority getPriority() {
        return priority;
    }

    public Category getCategory() {
        return category;
    }

    public User getAuthor() {
        return author;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastEditDate() {
        return lastEditDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) { this.status = status; }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public void setProvisionalAssignee(User provisionalAssignee) {
        this.provisionalAssignee = provisionalAssignee;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
}
