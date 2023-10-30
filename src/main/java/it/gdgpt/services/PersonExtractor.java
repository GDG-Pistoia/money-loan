package it.gdgpt.services;

import dev.langchain4j.service.UserMessage;
import it.gdgpt.dto.Person;

public interface PersonExtractor {
    @UserMessage("Extract information about a person from {{it}}. When income is null, it is set to 0.")
    Person extractPersonFrom(String text);
}

