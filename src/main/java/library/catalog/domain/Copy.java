package library.catalog.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import org.springframework.util.Assert;

@Entity
public class Copy {
    @EmbeddedId
    private CopyId id;
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "book_id"))
    private BookId bookId;
    @Embedded
    private BarCode barCode;
    private boolean available;

    Copy() {
    }

    public Copy(BookId bookId, BarCode barCode) {
        Assert.notNull(bookId, "bookId must not be null");
        Assert.notNull(barCode, "barCode must not be null");
        this.id = new CopyId();
        this.bookId = bookId;
        this.barCode = barCode;
        this.available = true;
    }

    public void makeUnavailable() {
        this.available = false;
    }

    public void makeAvailable() {
        this.available = true;
    }
}
