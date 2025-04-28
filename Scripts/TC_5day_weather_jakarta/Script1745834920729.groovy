import keywords.WeatherKeywords as WK
import internal.GlobalVariable as GV

// happy path → 200 OK
WK.getFiveDayForecast(GV.lat_jaksel.toString(), GV.lon_jaksel.toString(), GV.apiKey, 200)

// bad-latitude → 400 Bad Request
WK.getFiveDayForecast('abc', GV.lon_jaksel.toString(), GV.apiKey, 400)

// bad-longitude → 400 Bad Request
WK.getFiveDayForecast(GV.lat_jaksel.toString(), 'xyz', GV.apiKey, 400)

// bad-key → 401 Unauthorized
WK.getFiveDayForecast(GV.lat_jaksel.toString(), GV.lon_jaksel.toString(), 'bad_key', 401)
