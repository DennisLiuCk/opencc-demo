# OpenCC Demo

一個基於 Spring Boot + OpenCC4j 的中文繁簡轉換工具，支持自定義詞典。

## Features

- 支持繁體中文轉簡體中文
- 支持簡體中文轉繁體中文
- 自定義詞典管理
- 網頁界面操作
- RESTful API 支持

## Technologies

- Backend: Java, Spring Boot
- Conversion Library: OpenCC4j
- Frontend: Thymeleaf, Bootstrap

## Requirements

- Java 11 or higher
- Maven 3.6 or higher
- Spring Boot 2.x

## Quick Start

1. Clone repository:
```bash
git clone https://github.com/DennisLiuCk/opencc-demo.git
cd opencc-demo
```

2. Build and run:
```bash
mvn spring-boot:run
```

3. Access web interface:
```
http://localhost:8080
```

## Custom Dictionary

詞典文件位於 `src/main/resources/dictionary.properties`，格式如下：

```properties
dict.entry.[number]=[traditional]|[simplified]|[traditional_alt]|[category]
```

例如：
```properties
dict.entry.1=手提電腦|笔记本电脑|手提電腦|電子產品
```

參數說明：
- `[number]`: 詞條編號（僅用於確保唯一性，不影響載入順序）
- `[traditional]`: 繁體中文詞條
- `[simplified]`: 簡體中文詞條
- `[traditional_alt]`: 繁體中文替代詞條
- `[category]`: 詞條分類

## API Reference

### 1. Text Conversion

- **Endpoint**: `/convert`
- **Method**: POST
- **Request Body**:
```json
{
    "text": "要轉換的文字",
    "conversionType": "s2t"  // "s2t" or "t2s"
}
```

### 2. Dictionary Management

#### Get All Entries
- **Endpoint**: `/api/dictionary`
- **Method**: GET

#### Search Entries
- **Endpoint**: `/api/dictionary/search?keyword=關鍵字`
- **Method**: GET

#### Add Entry
- **Endpoint**: `/api/dictionary`
- **Method**: POST
- **Request Body**:
```json
{
    "original": "原始詞",
    "simplified": "簡體詞",
    "traditional": "繁體詞",
    "description": "描述"
}
```

#### Delete Entry
- **Endpoint**: `/api/dictionary/{original}`
- **Method**: DELETE

## Development Guide

### Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── opencc/
│   │               ├── controller/
│   │               ├── model/
│   │               └── service/
│   └── resources/
│       ├── templates/
│       ├── static/
│       ├── application.properties
│       └── dictionary.properties
```

### Core Components

- `DictionaryService`: 詞典管理核心服務
- `ConversionService`: 文字轉換服務
- `DictionaryController`: REST API 控制器

## Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details 