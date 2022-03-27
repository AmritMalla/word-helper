package com.amrit.wordhelper.service.impl;

import com.amrit.wordhelper.domain.WordWithMeaning;
import com.amrit.wordhelper.repository.WordRepository;
import com.amrit.wordhelper.service.MongoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MongoServiceImpl implements MongoService {

    private final WordRepository wordRepository;

    @Override
    public List<String> saveAll(Set<String> words) {
        List<WordWithMeaning> list = words.stream()
                .map(WordWithMeaning::new).collect(Collectors.toList());
        return wordRepository.saveAll(list).stream().map(WordWithMeaning::getId).collect(Collectors.toList());
    }
}
