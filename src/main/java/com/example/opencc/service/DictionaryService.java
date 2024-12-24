package com.example.opencc.service;

import com.example.opencc.model.DictionaryEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class DictionaryService {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryService.class);
    private final Map<String, DictionaryEntry> dictionary = new ConcurrentHashMap<>();
    
    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        loadDefaultDictionary();
    }

    private void loadDefaultDictionary() {
        try {
            int index = 1;
            while (true) {
                String entryKey = "dict.entry." + index;
                String entryValue = env.getProperty(entryKey);
                
                if (entryValue == null) {
                    break;
                }
                
                logger.debug("Loading dictionary entry {}: {}", entryKey, entryValue);
                
                String[] parts = entryValue.split("\\|");
                if (parts.length == 4) {
                    DictionaryEntry entry = new DictionaryEntry(
                        parts[0].trim(), // original
                        parts[1].trim(), // simplified
                        parts[2].trim(), // traditional
                        parts[3].trim()  // description
                    );
                    addEntry(entry);
                    logger.debug("Successfully loaded entry: {}", entry);
                } else {
                    logger.warn("Invalid dictionary entry format for key {}: {}", entryKey, entryValue);
                }
                
                index++;
            }
            logger.info("Successfully loaded {} dictionary entries", dictionary.size());
        } catch (Exception e) {
            logger.error("Error loading dictionary entries", e);
        }
    }

    public void addEntry(DictionaryEntry entry) {
        dictionary.put(entry.getOriginal(), entry);
    }

    public void removeEntry(String original) {
        dictionary.remove(original);
    }

    public List<DictionaryEntry> getAllEntries() {
        return new ArrayList<>(dictionary.values());
    }

    public DictionaryEntry getEntry(String original) {
        return dictionary.get(original);
    }

    public String convertWithDictionary(String text, boolean toTraditional) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        
        String result = text;
        for (DictionaryEntry entry : dictionary.values()) {
            if (toTraditional) {
                if (entry.getSimplified() != null && !entry.getSimplified().isEmpty()) {
                    result = result.replace(entry.getSimplified(), entry.getTraditional());
                }
            } else {
                if (entry.getTraditional() != null && !entry.getTraditional().isEmpty()) {
                    result = result.replace(entry.getTraditional(), entry.getSimplified());
                }
            }
        }
        return result;
    }

    public List<DictionaryEntry> searchEntries(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return getAllEntries();
        }
        
        return dictionary.values().stream()
                .filter(entry -> 
                    (entry.getOriginal() != null && entry.getOriginal().contains(keyword)) ||
                    (entry.getSimplified() != null && entry.getSimplified().contains(keyword)) ||
                    (entry.getTraditional() != null && entry.getTraditional().contains(keyword)))
                .collect(Collectors.toList());
    }
} 