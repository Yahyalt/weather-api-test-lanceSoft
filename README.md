# Weather API Test Suite (Katalon Studio)

A Katalon Studio project for testing OpenWeatherMap APIs. This repository includes REST requests imported from Postman, custom keywords for reusable logic, organized test cases & suites, and generates detailed HTML & JUnit reports.

---

## 📁 Repository Structure

```
weather-api-test-lanceSoft/          ← root project folder
│
├── .gitignore                      # exclude Katalon temp files, Reports, etc.
├── README.md                       # this file
├── console.properties              # Katalon runtime settings
│
├── Profiles/                       # environment-specific variables
│   └── default/
│       └── environment.properties  # define apiKey, lat_jaksel, lon_jaksel
│
├── Object Repository/              # HTTP requests (imported from Postman)
│   └── Postman/
│       ├── current_air_pollution_in_jakarta
│       └── 5_day_weather_jakarta
│
├── Keywords/                       # Groovy helper methods
│   └── keywords/
│       └── WeatherKeywords.groovy  # custom @Keyword wrappers for endpoints
│
├── Test Cases/                     # high-level scripts invoking keywords
│   ├── TC_GetCurrentAirPollution    # verifies pollution endpoint (200, 400, 401)
│   └── TC_5day_weather_jakarta      # verifies 5‑day forecast endpoint
│
├── Test Suites/                    # logical groupings of Test Cases
│   ├── TS_CurrentAirPollution
│   └── TS_FiveDayForecast
│
├── Data Files/                     # (optional) external CSV/Excel data
│
└── Reports/                        # generated after each run
    └── <timestamp>/                # contains HTML report & JUnit XML
```

---

## 🚀 Prerequisites

- [Katalon Studio](https://www.katalon.com) 10.2.0 or later
- Valid OpenWeatherMap API key (set in `Profiles/default/environment.properties`)
- Git (for cloning the repo)

---

## ▶️ Running Tests

### 1. In the Katalon Studio UI

1. **Clone** this repo locally:
   ```bash
   git clone https://github.com/your-org/weather-api-test-lanceSoft.git
   ```
2. **Open** Katalon Studio → **File → Open Project** → select the repo folder
3. In **Profiles → default**, open `environment.properties` and set:
   ```properties
   apiKey=<YOUR_API_KEY>
   lat_jaksel=-6.266
   lon_jaksel=106.8135
   ```
4. In **Test Explorer**:
   - Right-click `TS_CurrentAirPollution` → **Run** → **Web Service Request**
   - Repeat for `TS_FiveDayForecast`
5. After execution, open `Reports/<timestamp>/Execution Report.html` in your browser.


### 2. From the Command Line (Headless)

Use the Katalon CLI to run suites headlessly (CI integration):

```bash
# Linux/macOS
/Applications/Katalon\ Studio.app/Contents/MacOS/katalon \
  -noSplash \
  -runMode=console \
  -projectPath="$(pwd)" \
  -reportFolder="Reports" \
  -reportFileName="CI-Run" \
  -retry=0 \
  -testSuitePath="Test Suites/TS_FiveDayForecast" \
  -executionProfile="default" \
  -browserType="Chrome"
```

```bat
:: Windows
katalon -noSplash ^
  -runMode=console ^
  -projectPath="%CD%" ^
  -reportFolder="Reports" ^
  -reportFileName="CI-Run" ^
  -retry=0 ^
  -testSuitePath="Test Suites/TS_FiveDayForecast" ^
  -executionProfile="default" ^
  -browserType="Chrome"
```

After completion, you’ll find:
- `Reports/CI-Run/Execution Report.html`
- `Reports/CI-Run/JUnit-style-results.xml`

---

## 🛠️ Adding New Endpoints

1. **Import** or **Record** the new request in Katalon: **Object Repository → New → Web Service Request**.
2. Place it under `Object Repository/Postman/…`.
3. Add a new method in `Keywords/keywords/WeatherKeywords.groovy`:
   ```groovy
   @Keyword
def static void getNewEndpoint(String lat, String lon, String apiKey, int expectedStatus) {
    ResponseObject resp = WS.sendRequest(findTestObject('Postman/new_endpoint', [ ... ]))
    WS.verifyResponseStatusCode(resp, expectedStatus)
    // add JSON assertions...
   }
   ```
4. Create a corresponding Test Case in `Test Cases/…`, call your new keyword.
5. Include the Test Case in a Test Suite under `Test Suites/…`.

---

## 📄 Reports & CI Integration

- **HTML Reports**: human-friendly, clickable flows & snapshots.
- **JUnit XML**: CI tools (Jenkins, GitHub Actions) can parse and display pass/fail.
- **GitHub Actions**: you can set up a workflow to run CLI on push and upload artifacts/reports.

---

*Happy testing!*

