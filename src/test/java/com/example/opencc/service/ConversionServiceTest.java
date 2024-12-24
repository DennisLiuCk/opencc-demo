package com.example.opencc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConversionServiceTest {

    @Mock
    private DictionaryService dictionaryService;

    @InjectMocks
    private ConversionService conversionService;

    @Test
    void testConvertTextToTraditional() {
        // Given
        when(dictionaryService.convertWithDictionary(anyString(), eq(true)))
                .thenReturn("笔记本电脑");  // Return the input text unchanged

        // When
        String result = conversionService.convertText("笔记本电脑", "s2t");

        // Then
        verify(dictionaryService).convertWithDictionary(anyString(), eq(true));
        assertNotNull(result);
        assertTrue(result.contains("筆記本電腦"));
    }

    @Test
    void testConvertTextToSimplified() {
        // Given
        when(dictionaryService.convertWithDictionary(anyString(), eq(false)))
                .thenReturn("筆記本電腦");  // Return the input text unchanged

        // When
        String result = conversionService.convertText("筆記本電腦", "t2s");

        // Then
        verify(dictionaryService).convertWithDictionary(anyString(), eq(false));
        assertNotNull(result);
        assertTrue(result.contains("笔记本电脑"));
    }

    @Test
    void testConvertTextWithInvalidType() {
        // Then
        assertThrows(IllegalArgumentException.class, () -> 
            conversionService.convertText("test", "invalid"));
    }

    @Test
    void testConvertTextWithEmptyInput() {
        // Given
        when(dictionaryService.convertWithDictionary(eq(""), eq(true)))
                .thenReturn("");

        // When
        String result = conversionService.convertText("", "s2t");

        // Then
        verify(dictionaryService).convertWithDictionary(eq(""), eq(true));
        assertEquals("", result);
    }

    @Test
    void testConvertTextWithNullInput() {
        // When
        String result = conversionService.convertText(null, "s2t");

        // Then
        assertNull(result);
    }
} 