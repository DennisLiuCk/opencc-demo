package com.example.opencc.service;

import com.example.opencc.model.DictionaryEntry;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class DictionaryService {
    private final Map<String, DictionaryEntry> dictionary = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        // 載入預設詞典
        loadDefaultDictionary();
    }

    private void loadDefaultDictionary() {
        // 網路用語
        addEntry(new DictionaryEntry("笑死", "笑死", "笑死", "網路常用語"));
        addEntry(new DictionaryEntry("666", "666", "666", "形容厲害"));
        addEntry(new DictionaryEntry("gg", "gg", "gg", "遊戲用語，表示結束"));
        
        // 流行用語
        addEntry(new DictionaryEntry("打工人", "打工人", "打工人", "現代職場用語"));
        addEntry(new DictionaryEntry("內卷", "内卷", "內卷", "形容競爭激烈"));
        addEntry(new DictionaryEntry("躺平", "躺平", "躺平", "形容消極態度"));
        
        // 網路縮寫
        addEntry(new DictionaryEntry("稍等", "稍等", "稍等", "等一下的縮寫"));
        addEntry(new DictionaryEntry("私訊", "私信", "私訊", "私人訊息"));
        addEntry(new DictionaryEntry("已讀不回", "已读不回", "已讀不回", "看過訊息但不回覆"));
        
        // 地方用語
        addEntry(new DictionaryEntry("歐嗨唷", "欧嗨哟", "歐嗨唷", "台灣用語"));
        addEntry(new DictionaryEntry("好棒棒", "好棒棒", "好棒棒", "台灣用語"));
        addEntry(new DictionaryEntry("夭壽", "夭寿", "夭壽", "台灣用語"));
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
        String result = text;
        for (DictionaryEntry entry : dictionary.values()) {
            if (toTraditional) {
                result = result.replace(entry.getSimplified(), entry.getTraditional());
            } else {
                result = result.replace(entry.getTraditional(), entry.getSimplified());
            }
        }
        return result;
    }

    public List<DictionaryEntry> searchEntries(String keyword) {
        return dictionary.values().stream()
                .filter(entry -> entry.getOriginal().contains(keyword) ||
                        entry.getSimplified().contains(keyword) ||
                        entry.getTraditional().contains(keyword))
                .collect(Collectors.toList());
    }
} 