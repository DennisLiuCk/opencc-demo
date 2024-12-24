package com.example.opencc.controller;

import com.example.opencc.model.DictionaryEntry;
import com.example.opencc.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping
    public List<DictionaryEntry> getAllEntries() {
        return dictionaryService.getAllEntries();
    }

    @GetMapping("/search")
    public List<DictionaryEntry> searchEntries(@RequestParam String keyword) {
        return dictionaryService.searchEntries(keyword);
    }

    @PostMapping
    public ResponseEntity<DictionaryEntry> addEntry(@RequestBody DictionaryEntry entry) {
        dictionaryService.addEntry(entry);
        return ResponseEntity.ok(entry);
    }

    @DeleteMapping("/{original}")
    public ResponseEntity<Void> removeEntry(@PathVariable String original) {
        dictionaryService.removeEntry(original);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{original}")
    public ResponseEntity<DictionaryEntry> getEntry(@PathVariable String original) {
        DictionaryEntry entry = dictionaryService.getEntry(original);
        if (entry != null) {
            return ResponseEntity.ok(entry);
        }
        return ResponseEntity.notFound().build();
    }
} 