package com.example.opencc.service;

import com.github.houbb.opencc4j.util.ZhConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversionService {
    
    @Autowired
    private DictionaryService dictionaryService;
    
    public String convertText(String text, String conversionType) {
        // 先使用自定義詞典進行轉換
        boolean toTraditional = conversionType.equals("s2t");
        String result = dictionaryService.convertWithDictionary(text, toTraditional);
        
        // 再使用OpenCC進行剩餘文本的轉換
        if (toTraditional) {
            return ZhConverterUtil.toTraditional(result);
        } else if (conversionType.equals("t2s")) {
            return ZhConverterUtil.toSimple(result);
        }
        
        throw new IllegalArgumentException("Invalid conversion type. Use 's2t' for Simplified to Traditional or 't2s' for Traditional to Simplified");
    }
} 