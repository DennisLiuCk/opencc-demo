package com.example.opencc.controller;

import com.example.opencc.dto.ConversionRequest;
import com.example.opencc.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ConversionController {

    @Autowired
    private ConversionService conversionService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/convert")
    @ResponseBody
    public Map<String, String> convert(@RequestBody ConversionRequest request) {
        String convertedText = conversionService.convertText(request.getText(), request.getConversionType());
        Map<String, String> response = new HashMap<>();
        response.put("convertedText", convertedText);
        return response;
    }
} 