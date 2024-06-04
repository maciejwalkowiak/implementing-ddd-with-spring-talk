package library.catalog.infrastructure;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BookEntityRepository extends CrudRepository<BookEntity, UUID> {
}
