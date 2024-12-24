package com.example.opencc.service;

import com.example.opencc.model.DictionaryEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryServiceTest {
    
    private DictionaryService dictionaryService;

    @BeforeEach
    void setUp() {
        dictionaryService = new DictionaryService();
        dictionaryService.init();
    }

    @Test
    void testAddAndGetEntry() {
        // Given
        DictionaryEntry entry = new DictionaryEntry("手提電腦", "笔记本电脑", "手提電腦", "電子產品");

        // When
        dictionaryService.addEntry(entry);
        DictionaryEntry retrieved = dictionaryService.getEntry("手提電腦");

        // Then
        assertNotNull(retrieved);
        assertEquals("笔记本电脑", retrieved.getSimplified());
        assertEquals("手提電腦", retrieved.getTraditional());
        assertEquals("電子產品", retrieved.getDescription());
    }

    @Test
    void testRemoveEntry() {
        // Given
        DictionaryEntry entry = new DictionaryEntry("測試", "测试", "測試", "其他");
        dictionaryService.addEntry(entry);

        // When
        dictionaryService.removeEntry("測試");

        // Then
        assertNull(dictionaryService.getEntry("測試"));
    }

    @Test
    void testGetAllEntries() {
        // Given
        dictionaryService.addEntry(new DictionaryEntry("詞1", "词1", "詞1", "測試"));
        dictionaryService.addEntry(new DictionaryEntry("詞2", "词2", "詞2", "測試"));

        // When
        List<DictionaryEntry> entries = dictionaryService.getAllEntries();

        // Then
        assertFalse(entries.isEmpty());
        assertTrue(entries.size() >= 2);
    }

    @Test
    void testSearchEntries() {
        // Given
        DictionaryEntry entry1 = new DictionaryEntry("手機", "手机", "手機", "電子產品");
        DictionaryEntry entry2 = new DictionaryEntry("智能手機", "智能手机", "智能手機", "電子產品");
        dictionaryService.addEntry(entry1);
        dictionaryService.addEntry(entry2);

        // When
        List<DictionaryEntry> results = dictionaryService.searchEntries("手機");

        // Then
        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(e -> e.getOriginal().equals("手機")));
        assertTrue(results.stream().anyMatch(e -> e.getOriginal().equals("智能手機")));
    }

    @Test
    void testConvertWithDictionaryToTraditional() {
        // Given
        DictionaryEntry entry = new DictionaryEntry("手提電腦", "笔记本电脑", "手提電腦", "電子產品");
        dictionaryService.addEntry(entry);

        // When
        String result = dictionaryService.convertWithDictionary("这是一台笔记本电脑", true);

        // Then
        assertTrue(result.contains("手提電腦"));
        assertFalse(result.contains("笔记本电脑"));
    }

    @Test
    void testConvertWithDictionaryToSimplified() {
        // Given
        DictionaryEntry entry = new DictionaryEntry("手提電腦", "笔记本电脑", "手提電腦", "電子產品");
        dictionaryService.addEntry(entry);

        // When
        String result = dictionaryService.convertWithDictionary("這是一台手提電腦", false);

        // Then
        assertTrue(result.contains("笔记本电脑"));
        assertFalse(result.contains("手提電腦"));
    }

    @Test
    void testConvertWithDictionaryEmptyInput() {
        // When
        String result = dictionaryService.convertWithDictionary("", true);

        // Then
        assertEquals("", result);
        
        // When
        result = dictionaryService.convertWithDictionary(null, true);

        // Then
        assertNull(result);
    }

    @Test
    void testSearchEntriesWithEmptyKeyword() {
        // Given
        DictionaryEntry entry = new DictionaryEntry("測試", "测试", "測試", "其他");
        dictionaryService.addEntry(entry);

        // When
        List<DictionaryEntry> results = dictionaryService.searchEntries("");

        // Then
        assertFalse(results.isEmpty());
        assertEquals(dictionaryService.getAllEntries().size(), results.size());
    }
} 