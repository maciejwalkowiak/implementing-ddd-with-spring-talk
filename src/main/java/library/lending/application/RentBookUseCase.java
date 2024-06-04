package library.lending.application;

import library.UseCase;
import library.lending.domain.CopyId;
import library.lending.domain.Loan;
import library.lending.domain.LoanRepository;
import library.lending.domain.UserId;

@UseCase
public class RentBookUseCase {
    private final LoanRepository loanRepository;

    public RentBookUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void execute(CopyId copyId, UserId userId) {
        // TODO: ensure rented copy is not rented again
        loanRepository.save(new Loan(copyId, userId));
    }
}
