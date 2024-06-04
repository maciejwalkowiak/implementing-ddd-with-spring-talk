package library.lending.application;

import library.UseCase;
import library.lending.domain.Loan;
import library.lending.domain.LoanId;
import library.lending.domain.LoanRepository;

@UseCase
public class ReturnBookUseCase {


    private final LoanRepository loanRepository;

    public ReturnBookUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void execute(LoanId loanId) {
        Loan loan = loanRepository.findByIdOrThrow(loanId);
        loan.returned();
    }
}
