# Weather API Test Suite (Katalon Studio)

[![Katalon Studio](https://img.shields.io/badge/Katalon%20Studio-10.2.0%2B-blue)](https://www.katalon.com)
[![OpenWeatherMap API](https://img.shields.io/badge/OpenWeatherMap-API-orange)](https://openweathermap.org/api)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

A comprehensive Katalon Studio project for testing OpenWeatherMap APIs. This repository includes REST requests imported from Postman, custom keywords for reusable logic, organized test cases & suites, and generates detailed HTML & JUnit reports.

---

## 🚀 Quick Start

Get up and running in 5 minutes:

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-org/weather-api-test-lanceSoft.git
   cd weather-api-test-lanceSoft
   ```

2. **Get your API key**
   - Sign up at [OpenWeatherMap](https://openweathermap.org/api)
   - Copy your API key from the dashboard

3. **Configure the project**
   - Open `Profiles/default/environment.properties`
   - Replace `<YOUR_API_KEY>` with your actual API key

4. **Run tests**
   - Open in Katalon Studio
   - Right-click `Test Suites/TS_FiveDayForecast` → Run

---

## 📁 Project Structure

```
weather-api-test-lanceSoft/
├── 📄 README.md                       # Project documentation
├── 📄 console.properties              # Katalon runtime settings
├── 📄 .gitignore                      # Git ignore rules
│
├── 📁 Profiles/                       # Environment configurations
│   └── default/
│       └── environment.properties    # API keys & test data
│
├── 📁 Object Repository/              # API request definitions
│   └── Postman/
│       ├── current_air_pollution_in_jakarta
│       └── 5_day_weather_jakarta
│
├── 📁 Keywords/                       # Reusable test logic
│   └── keywords/
│       └── WeatherKeywords.groovy    # Custom API test methods
│
├── 📁 Test Cases/                     # Individual test scenarios
│   ├── TC_GetCurrentAirPollution     # Air pollution API tests
│   └── TC_5day_weather_jakarta       # Weather forecast tests
│
├── 📁 Test Suites/                    # Test collections
│   ├── TS_CurrentAirPollution        # Air pollution test suite
│   └── TS_FiveDayForecast           # Weather forecast test suite
│
├── 📁 Data Files/                     # External test data (CSV/Excel)
│
└── 📁 Reports/                        # Generated test reports
    └── <timestamp>/
        ├── Execution Report.html     # Human-readable results
        └── JUnit-style-results.xml   # CI/CD integration format
```

---

## 🛠️ Prerequisites

### Required Software
- **Katalon Studio**: 10.2.0 or later ([Download](https://www.katalon.com/download/))
- **Java**: JDK 11 or later
- **Git**: For version control

### API Requirements
- **OpenWeatherMap API Key**: Free tier available at [openweathermap.org](https://openweathermap.org/api)
- **Internet Connection**: Required for API calls

---

## 📋 Installation & Setup

### Step 1: Clone the Repository
```bash
git clone https://github.com/your-org/weather-api-test-lanceSoft.git
cd weather-api-test-lanceSoft
```

### Step 2: Install Katalon Studio
1. Download from [katalon.com](https://www.katalon.com/download/)
2. Install following the platform-specific instructions
3. Launch Katalon Studio

### Step 3: Configure API Keys
1. Navigate to `Profiles/default/environment.properties`
2. Update the configuration:
   ```properties
   # OpenWeatherMap API Configuration
   apiKey=YOUR_ACTUAL_API_KEY_HERE
   
   # Jakarta coordinates for testing
   lat_jaksel=-6.266
   lon_jaksel=106.8135
   
   # Base URL (don't change unless using different service)
   baseUrl=https://api.openweathermap.org/data/2.5
   ```

### Step 4: Open Project in Katalon Studio
1. Launch Katalon Studio
2. **File** → **Open Project**
3. Select the `weather-api-test-lanceSoft` folder
4. Wait for project to load

---

## ▶️ Running Tests

### Method 1: Katalon Studio UI (Recommended for Development)

#### Running Individual Test Cases
1. **Navigate** to Test Cases folder
2. **Right-click** on desired test case (e.g., `TC_5day_weather_jakarta`)
3. **Select** → **Run** → **Web Service Request**
4. **Monitor** execution in the Console tab

#### Running Test Suites
1. **Navigate** to Test Suites folder
2. **Right-click** on desired suite (e.g., `TS_FiveDayForecast`)
3. **Select** → **Run** → **Web Service Request**
4. **View** results in the Report tab

#### Viewing Results
- **Real-time**: Console and Report tabs in Katalon Studio
- **Detailed Report**: Navigate to `Reports/<timestamp>/Execution Report.html`

### Method 2: Command Line (CI/CD Integration)

#### Windows
```batch
@echo off
set KATALON_PATH="C:\Program Files\Katalon Studio\katalon.exe"
set PROJECT_PATH=%CD%
set REPORT_FOLDER=Reports
set REPORT_NAME=CLI-Execution

%KATALON_PATH% -noSplash ^
  -runMode=console ^
  -projectPath="%PROJECT_PATH%" ^
  -reportFolder="%REPORT_FOLDER%" ^
  -reportFileName="%REPORT_NAME%" ^
  -retry=0 ^
  -testSuitePath="Test Suites/TS_FiveDayForecast" ^
  -executionProfile="default"
```

#### Linux/macOS
```bash
#!/bin/bash
KATALON_PATH="/Applications/Katalon Studio.app/Contents/MacOS/katalon"
PROJECT_PATH="$(pwd)"
REPORT_FOLDER="Reports"
REPORT_NAME="CLI-Execution"

"$KATALON_PATH" -noSplash \
  -runMode=console \
  -projectPath="$PROJECT_PATH" \
  -reportFolder="$REPORT_FOLDER" \
  -reportFileName="$REPORT_NAME" \
  -retry=0 \
  -testSuitePath="Test Suites/TS_FiveDayForecast" \
  -executionProfile="default"
```

#### Docker (Optional)
```bash
docker run -v "$(pwd)":/katalon/katalon/source \
  katalonstudio/katalon:latest \
  -projectPath=/katalon/katalon/source \
  -testSuitePath="Test Suites/TS_FiveDayForecast" \
  -executionProfile="default"
```

---

## 🧪 Test Cases Overview

### Current Air Pollution Tests (`TC_GetCurrentAirPollution`)
- **Purpose**: Tests OpenWeatherMap Air Pollution API
- **Scenarios**:
  - ✅ Valid coordinates (200 OK)
  - ❌ Invalid coordinates (400 Bad Request)
  - ❌ Missing API key (401 Unauthorized)
- **Location**: Jakarta Selatan coordinates

### 5-Day Weather Forecast Tests (`TC_5day_weather_jakarta`)
- **Purpose**: Tests OpenWeatherMap 5-day forecast API
- **Scenarios**:
  - ✅ Valid request with proper response structure
  - ✅ Response contains 40 forecast entries (5 days × 8 times/day)
  - ✅ Each forecast has required fields (temp, humidity, etc.)
- **Location**: Jakarta coordinates

---

## 🔧 Customization & Extension

### Adding New Test Endpoints

#### 1. Create Object Repository Entry
1. **Right-click** `Object Repository/Postman`
2. **New** → **Web Service Request**
3. **Configure** the request:
   ```
   Method: GET
   URL: https://api.openweathermap.org/data/2.5/weather
   Parameters: lat, lon, appid
   ```

#### 2. Add Custom Keyword
Edit `Keywords/keywords/WeatherKeywords.groovy`:
```groovy
@Keyword
static void getCurrentWeather(String lat, String lon, String apiKey, int expectedStatus) {
    // Define request parameters
    def params = [
        ('lat'): lat,
        ('lon'): lon,
        ('appid'): apiKey
    ]
    
    // Send request
    ResponseObject response = WS.sendRequest(
        findTestObject('Postman/current_weather', params)
    )
    
    // Verify status code
    WS.verifyResponseStatusCode(response, expectedStatus)
    
    // Parse and verify response
    if (expectedStatus == 200) {
        def jsonResponse = WS.getResponseText(response)
        def parsedJson = new JsonSlurper().parseText(jsonResponse)
        
        // Add your assertions here
        assert parsedJson.weather != null
        assert parsedJson.main.temp != null
        
        KeywordUtil.logInfo("Weather data retrieved successfully for coordinates: ${lat}, ${lon}")
    }
}
```

#### 3. Create Test Case
1. **Right-click** `Test Cases`
2. **New** → **Test Case**
3. **Add** test steps:
   ```groovy
   import keywords.WeatherKeywords
   
   // Test valid request
   WeatherKeywords.getCurrentWeather(
       GlobalVariable.lat_jaksel,
       GlobalVariable.lon_jaksel,
       GlobalVariable.apiKey,
       200
   )
   
   // Test invalid coordinates
   WeatherKeywords.getCurrentWeather(
       "invalid",
       "invalid",
       GlobalVariable.apiKey,
       400
   )
   ```

#### 4. Add to Test Suite
1. **Right-click** `Test Suites`
2. **New** → **Test Suite**
3. **Add** your test case to the suite

### Modifying Test Data
Edit `Profiles/default/environment.properties`:
```properties
# Add new test locations
lat_bandung=-6.9175
lon_bandung=107.6191

# Add different API endpoints
weatherEndpoint=/weather
forecastEndpoint=/forecast
airPollutionEndpoint=/air_pollution
```

---

## 📊 Reports & Analysis

### HTML Reports
- **Location**: `Reports/<timestamp>/Execution Report.html`
- **Features**:
  - Interactive test results
  - Request/response details
  - Screenshots and logs
  - Execution timeline

### JUnit XML Reports
- **Location**: `Reports/<timestamp>/JUnit-style-results.xml`
- **Usage**: CI/CD integration
- **Compatible with**: Jenkins, GitHub Actions, Azure DevOps

### Custom Reporting
Add to your test cases:
```groovy
// Add custom log entries
KeywordUtil.logInfo("Custom info message")
KeywordUtil.markPassed("Test passed with custom message")
KeywordUtil.markFailed("Test failed with custom message")

// Add screenshots (for UI tests)
WebUI.takeScreenshot('Screenshots/test_result.png')
```

---

## 🚀 CI/CD Integration

### GitHub Actions Example
Create `.github/workflows/api-tests.yml`:
```yaml
name: API Tests

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]

jobs:
  test:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up JDK 11
      uses: actions/setup-java@v3
      with:
        java-version: '11'
        distribution: 'temurin'
        
    - name: Download Katalon Studio
      run: |
        wget -O katalon.tar.gz https://download.katalon.com/kat...
        tar -xzf katalon.tar.gz
        
    - name: Run API Tests
      env:
        API_KEY: ${{ secrets.OPENWEATHER_API_KEY }}
      run: |
        ./katalon/katalon -noSplash \
          -runMode=console \
          -projectPath="$(pwd)" \
          -testSuitePath="Test Suites/TS_FiveDayForecast" \
          -executionProfile="default"
          
    - name: Upload Test Results
      uses: actions/upload-artifact@v3
      if: always()
      with:
        name: test-results
        path: Reports/
```

### Jenkins Pipeline Example
```groovy
pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Run Tests') {
            steps {
                script {
                    sh '''
                        katalon -noSplash \
                          -runMode=console \
                          -projectPath="$(pwd)" \
                          -testSuitePath="Test Suites/TS_FiveDayForecast" \
                          -executionProfile="default"
                    '''
                }
            }
        }
        
        stage('Publish Results') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'Reports',
                    reportFiles: '**/Execution Report.html',
                    reportName: 'API Test Report'
                ])
            }
        }
    }
}
```

---

## 🔍 Troubleshooting

### Common Issues

#### 1. "API Key Invalid" Error
**Symptoms**: 401 Unauthorized responses
**Solutions**:
- Verify API key in `environment.properties`
- Check API key is active on OpenWeatherMap dashboard
- Ensure no extra spaces in the key

#### 2. "Test Object Not Found" Error
**Symptoms**: Katalon can't find Object Repository items
**Solutions**:
- Check object path in Test Cases
- Verify object exists in Object Repository
- Re-import Postman collection if needed

#### 3. Connection Timeouts
**Symptoms**: Tests fail with timeout errors
**Solutions**:
- Check internet connection
- Verify OpenWeatherMap API is accessible
- Increase timeout in Project Settings

#### 4. Report Generation Fails
**Symptoms**: No HTML reports generated
**Solutions**:
- Check Reports folder permissions
- Verify sufficient disk space
- Run Katalon as administrator (Windows)

### Debug Mode
Enable detailed logging in `console.properties`:
```properties
# Enable debug logging
katalon.log.level=DEBUG
katalon.report.console=true
```

### API Testing Best Practices
1. **Always validate response schema**
2. **Test both success and failure scenarios**
3. **Use environment variables for sensitive data**
4. **Implement proper error handling**
5. **Add meaningful assertions**

---

## 🤝 Contributing

We welcome contributions! Please follow these guidelines:

1. **Fork** the repository
2. **Create** a feature branch: `git checkout -b feature/new-endpoint`
3. **Add** your changes with tests
4. **Commit** with descriptive messages
5. **Push** to your fork: `git push origin feature/new-endpoint`
6. **Create** a Pull Request

### Development Setup
```bash
# Clone your fork
git clone https://github.com/your-username/weather-api-test-lanceSoft.git

# Create feature branch
git checkout -b feature/new-feature

# Make changes and test
# ...

# Commit and push
git commit -m "Add new weather endpoint tests"
git push origin feature/new-feature
```

---

## 📞 Support

- **Issues**: [GitHub Issues](https://github.com/your-org/weather-api-test-lanceSoft/issues)
- **Discussions**: [GitHub Discussions](https://github.com/your-org/weather-api-test-lanceSoft/discussions)

---

## 📚 Additional Resources

- [Katalon Studio Documentation](https://docs.katalon.com/)
- [OpenWeatherMap API Documentation](https://openweathermap.org/api)
- [Groovy Language Documentation](https://groovy-lang.org/documentation.html)
- [REST API Testing Best Practices](https://restfulapi.net/rest-api-testing/)

---

*Happy Testing! 🌤️*

