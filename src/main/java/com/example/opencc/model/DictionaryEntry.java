package com.example.opencc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryEntry {
    private String original;
    private String simplified;
    private String traditional;
    private String description;
} 