package it.gdgpt.services;

import it.gdgpt.dto.LoanResponse;
import it.gdgpt.dto.Person;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.drools.model.codegen.ExecutableModelProject;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static it.gdgpt.dto.LoanResponse.Status.FAILED;
import static it.gdgpt.dto.LoanResponse.Status.SUCCESS;

@ApplicationScoped
public class RuleEngine {

    public static final String RULE = "loan.drl";
    private KieBase kieBase;

    public LoanResponse grantLoan(Person person){
        KieSession kieSession = kieBase.newKieSession();
        List<String> answers = new ArrayList<>();
        kieSession.setGlobal("answers", answers);
        kieSession.insert(person);
        kieSession.fireAllRules();

        if (answers.isEmpty()) {
            return new LoanResponse(SUCCESS, "Loan can be granted!");
        }
        return new LoanResponse(FAILED, "Not granted because " + answers);
    }


    private String getResourcePath(String resourceName) {
        return Thread.currentThread().getContextClassLoader().getResource(resourceName).getPath();
    }

    @PostConstruct
    public void init() {
        KieHelper kieHelper = new KieHelper();
        kieHelper.addResource(ResourceFactory.newFileResource(new File(getResourcePath(RULE))));
        kieBase = kieHelper.build(ExecutableModelProject.class);
    }
}
