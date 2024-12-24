package com.example.opencc.service;

import com.github.houbb.opencc4j.util.ZhConverterUtil;
import org.springframework.stereotype.Service;

@Service
public class ConversionService {
    
    public String convertText(String text, String conversionType) {
        if (conversionType.equals("s2t")) {
            return ZhConverterUtil.toTraditional(text);
        } else if (conversionType.equals("t2s")) {
            return ZhConverterUtil.toSimple(text);
        }
        throw new IllegalArgumentException("Invalid conversion type. Use 's2t' for Simplified to Traditional or 't2s' for Traditional to Simplified");
    }
} 