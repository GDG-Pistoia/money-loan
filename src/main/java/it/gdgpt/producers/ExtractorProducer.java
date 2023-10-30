package it.gdgpt.producers;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import it.gdgpt.services.PersonExtractor;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import static java.time.Duration.ofSeconds;

@ApplicationScoped
public class ExtractorProducer {

    @ConfigProperty(name = "openai.apikey")
    String apikey;

    private ChatLanguageModel model;

    @Produces
    PersonExtractor produces(){
        return AiServices.create(PersonExtractor.class, model);
    }

    @PostConstruct
    public void initModel(){
        model = OpenAiChatModel.builder()
                .apiKey(apikey)
                .timeout(ofSeconds(60))
                .build();
    }
}
