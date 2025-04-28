import keywords.WeatherKeywords as WK
import internal.GlobalVariable as GlobalVariable


// for a happy‐path check:
WK.getCurrentAirPollution(-6.266, 106.8135, GlobalVariable.apiKey, 200)

// for an “unauthorized” scenario:
WK.getCurrentAirPollution(-6.266, 106.8135, "bad_key", 401)
