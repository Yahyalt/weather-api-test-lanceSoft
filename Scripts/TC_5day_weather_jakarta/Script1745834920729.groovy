import keywords.WeatherKeywords as WK
import internal.GlobalVariable as GV

// happy path → 200 OK
WK.getFiveDayForecast(GV.lat_jaksel.toString(), GV.lon_jaksel.toString(), GV.apiKey, 200)
