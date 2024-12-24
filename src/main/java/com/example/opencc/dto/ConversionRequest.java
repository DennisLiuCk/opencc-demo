package com.example.opencc.dto;

import lombok.Data;

@Data
public class ConversionRequest {
    private String text;
    private String conversionType; // s2t: 簡體到繁體, t2s: 繁體到簡體
} 