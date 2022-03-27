package com.amrit.wordhelper.service.impl;

import com.amrit.wordhelper.config.WebDriverUtility;
import com.amrit.wordhelper.domain.WordWithMeaning;
import com.amrit.wordhelper.repository.WordRepository;
import com.amrit.wordhelper.service.WebScrappingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebScrappingServiceImpl implements WebScrappingService {

    private final WordRepository wordRepository;

    private String baseUrl = "https://dictionary.cambridge.org/dictionary/english/";

    private WebDriver webDriver;

    @Override
    public void getTheMeaning() {
        List<WordWithMeaning> wordsToProcess = wordRepository.findAllByMeaningTextNull();
        if (webDriver == null) {
            webDriver = WebDriverUtility.operaWebDriver();
        }
        for (WordWithMeaning word : wordsToProcess) {
            getMeaning(word);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.error(e.getLocalizedMessage());
            }
        }

        log.info("Words being processed :: {} ", wordsToProcess);
    }

    private void getMeaning(WordWithMeaning word) {
        String url = baseUrl + word.getId();
        webDriver.get(url);
        log.info("Getting the meaning of :: {}", word);
        StringBuilder stringBuilder = new StringBuilder();
        List<WebElement> elements = webDriver.findElements(By.className("sense-body"));

        if (elements.size() == 0) {
            return;
        }
        // WebElement element = elements.get(0);
        //String first = element.getText();

        //int index = first.indexOf("Thesaurus: synonyms, antonyms, and examples");

        for (WebElement webElement : elements) {
            stringBuilder.append(StringUtils.capitalize(webElement.getText()));
            log.info(webElement.getText());
            stringBuilder.append("\n\n");
        }
        word.setMeaningText(stringBuilder.toString());
        wordRepository.save(word);
    }
}
