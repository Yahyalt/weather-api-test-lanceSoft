////import keywords.WeatherKeywords as WK
////import internal.GlobalVariable as GlobalVariable
//import keywords.WeatherKeywords as WK
//import internal.GlobalVariable as GV
////
////
//// for positive case scenario:
//WK.getCurrentAirPollution("-6.266", "106.8135", GV.apiKey, 200)
//
////// for an "unauthorized" scenario:
////WK.getCurrentAirPollution("-6.266", "106.8135", "bad_key", 401)
//
//
////// for a "bad request" for latitude scenario:
//WK.getCurrentAirPollution("abc", "106.8135", GV.apiKey, 400)
//
////// for a "bad request" for longitude scenario:
////WK.getCurrentAirPollution("-6.266", "abc", GV.apiKey, 400)
//
//
//
//
////// 1) happy path → 200 OK
////WK.getCurrentAirPollution("-6.266", "106.8135", GV.apiKey, 200)
////
////// 2) bad‐latitude path → 400 Bad Request
////WK.getCurrentAirPollution("abc",     "106.8135", GV.apiKey, 400)



import keywords.WeatherKeywords as WK
import internal.GlobalVariable as GV

// happy path → 200 OK
WK.getCurrentAirPollution(GV.lat_jaksel.toString(), GV.lon_jaksel.toString(), GV.apiKey, 200)

// bad-latitude → 400 Bad Request
WK.getCurrentAirPollution('abc', GV.lon_jaksel.toString(), GV.apiKey, 400)

// bad-longitude → 400 Bad Request
WK.getCurrentAirPollution(GV.lat_jaksel.toString(), 'xyz', GV.apiKey, 400)

// bad-key → 401 Unauthorized
WK.getCurrentAirPollution(GV.lat_jaksel.toString(), GV.lon_jaksel.toString(), 'bad_key', 401)
