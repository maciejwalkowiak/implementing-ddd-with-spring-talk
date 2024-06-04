package library.catalog.application;

import library.catalog.domain.Copy;
import library.catalog.domain.CopyId;
import library.catalog.domain.CopyRepository;
import library.lending.domain.LoanClosed;
import library.lending.domain.LoanCreated;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class DomainEventListener {

    private final CopyRepository copyRepository;

    public DomainEventListener(CopyRepository copyRepository) {
        this.copyRepository = copyRepository;
    }

    @ApplicationModuleListener
    public void handle(LoanCreated event) {
        Copy copy = copyRepository.findById(new CopyId(event.copyId().id())).orElseThrow();
        copy.makeUnavailable();
        copyRepository.save(copy);
    }

    @ApplicationModuleListener
    public void handle(LoanClosed event) {
        Copy copy = copyRepository.findById(new CopyId(event.copyId().id())).orElseThrow();
        copy.makeAvailable();
        copyRepository.save(copy);
    }
}
