package com.amrit.wordhelper.repository;

import com.amrit.wordhelper.domain.WordWithMeaning;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WordRepository extends MongoRepository<WordWithMeaning, String > {

    List<WordWithMeaning> findAllByMeaningTextNull();

    List<WordWithMeaning> findAllByMeaningTextNotNull();

}
