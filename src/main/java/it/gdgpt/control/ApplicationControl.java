package it.gdgpt.control;

import it.gdgpt.dto.LoanApplication;
import it.gdgpt.dto.LoanResponse;
import it.gdgpt.dto.Person;
import it.gdgpt.services.PersonExtractor;
import it.gdgpt.services.RuleEngine;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class ApplicationControl {

    @Inject
    PersonExtractor personExtractor;

    @Inject
    RuleEngine ruleEngine;

    public Person extractPerson(LoanApplication loanApplication){
        return personExtractor.extractPersonFrom(loanApplication.getDescription());
    }

    public LoanResponse check(LoanApplication loanApplication) {
        Person person = extractPerson(loanApplication);
        return ruleEngine.grantLoan(person);
    }

}
