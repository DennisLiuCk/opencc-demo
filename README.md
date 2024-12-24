# OpenCC 繁簡轉換工具

這是一個基於Spring Boot和OpenCC4j的中文繁簡轉換Web應用程式，提供簡單直觀的界面，支持中文文本在簡體和繁體之間的快速轉換。

## Features

- 🔄 支持簡體到繁體和繁體到簡體的雙向轉換
- 💻 現代化的Web界面
- ⚡ 快速且準確的轉換結果
- 📋 一鍵複製轉換結果
- 🎨 優雅的視覺設計和交互體驗

## Technical stack

- **後端框架**: Spring Boot 2.7.0
- **轉換引擎**: OpenCC4j 1.7.2
- **前端技術**: 
  - HTML5
  - CSS3
  - JavaScript
  - Bootstrap 5.1.3
- **字體**: Noto Sans TC

## Requirements

- JDK 11 or higher
- Maven 3.6 or higher

## Quick Start

### 1. clone project

```bash
git clone [your-repository-url]
cd opencc-demo
```

### 2. build and run

```bash
mvn spring-boot:run
```

### 3. open the app

open your browser：`http://localhost:8080`

## Instructions 

1. 在文本輸入框中輸入要轉換的中文文本
2. 選擇簡體到繁體或繁體到簡體
3. 點擊「開始轉換」按鈕
4. 查看轉換結果，可以使用「複製結果」按鈕複製轉換後的文本

## API Instructions 

### convert API

- **Endpoint**: `/convert`
- **Method**: POST
- **RequestBody**:
  ```json
  {
    "text": "要轉換的文本",
    "conversionType": "s2t或t2s"
  }
  ```
- **ResponseBody**:
  ```json
  {
    "convertedText": "轉換後的文本"
  }
  ```

## Structure

```
opencc-demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── opencc/
│   │   │               ├── controller/
│   │   │               ├── service/
│   │   │               └── dto/
│   │   └── resources/
│   │       └── templates/
│   │           └── index.html
└── pom.xml
```

## Special Thanks

- [OpenCC4j](https://github.com/houbb/opencc4j) - 提供中文轉換功能
- [Spring Boot](https://spring.io/projects/spring-boot) - 後端框架
- [Bootstrap](https://getbootstrap.com/) - 前端框架
- [Google Fonts](https://fonts.google.com/) - Noto Sans TC 字體 