package library.lending.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Version;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Loan extends AbstractAggregateRoot<Loan> {
    @EmbeddedId
    private LoanId loanId;
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "copy_id"))
    private CopyId copyId;
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id"))
    private UserId userId;
    private LocalDateTime createdAt;
    private LocalDate expectedReturnDate;
    private LocalDateTime returnedAt;

    @Version
    private Long version;

    Loan() {
    }

    public Loan(CopyId copyId, UserId userId) {
        Assert.notNull(copyId, "copyId must not be null");
        Assert.notNull(userId, "userId must not be null");
        this.loanId = new LoanId();
        this.copyId = copyId;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
        this.expectedReturnDate = LocalDate.now().plusDays(30);
        this.registerEvent(new LoanCreated(this.copyId));
    }

    public void returned() {
        this.returnedAt = LocalDateTime.now();
        if (this.returnedAt.isAfter(expectedReturnDate.atStartOfDay())) {
            // calculate fee
        }
        this.registerEvent(new LoanClosed(this.copyId));
    }
}
