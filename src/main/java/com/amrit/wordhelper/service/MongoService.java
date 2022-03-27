package com.amrit.wordhelper.service;

import java.util.List;
import java.util.Set;

public interface MongoService {
    List<String> saveAll(Set<String> words);
}
