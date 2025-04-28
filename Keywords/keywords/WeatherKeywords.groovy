package keywords

import com.kms.katalon.core.annotation.Keyword
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.ResponseObject
import groovy.json.JsonSlurper

class WeatherKeywords {

	/**
	 * Calls the current air-pollution endpoint and verifies status + body.
	 *
	 * @param lat            latitude of location (String or double)
	 * @param lon            longitude of location (String or double)
	 * @param apiKey         your OpenWeatherMap API key
	 * @param expectedStatus the HTTP status you expect (200, 400, 401)
	 */
	@Keyword
	def static void getCurrentAirPollution(String lat, String lon, String apiKey, int expectedStatus) {
		ResponseObject resp = WS.sendRequest(
				findTestObject('Postman/current_air_pollution_in_jakarta', [
					('lat_jaksel'): lat,
					('lon_jaksel'): lon,
					('apiKey')    : apiKey
				])
				)

		// 1) Verify HTTP status
		WS.verifyResponseStatusCode(resp, expectedStatus)

		if (expectedStatus == 200) {
			// 200 OK
			WS.verifyNotEqual(resp.getResponseBodyContent().trim(), '')
			int count = WS.getElementsCount(resp, 'list')
			WS.verifyGreaterThan(count, 0)
			WS.verifyElementPropertyValue(resp, 'list[0].main.aqi', 1)
		} else if (expectedStatus == 400) {
			// always assert the code
			WS.verifyElementPropertyValue(resp, 'cod', '400')

			// now decide which message to expect
			boolean latIsNum = (lat ==~ /^-?\d+(\.\d+)?$/)
			boolean lonIsNum = (lon ==~ /^-?\d+(\.\d+)?$/)

			if (!latIsNum) {
				// any non-numeric latitude (including empty) → wrong latitude
				WS.verifyElementPropertyValue(resp, 'message', 'wrong latitude')
			}
			else if (!lonIsNum) {
				// any non-numeric longitude (including empty) → wrong longitude
				WS.verifyElementPropertyValue(resp, 'message', 'wrong longitude')
			}
			else {
				// both numeric but still a 400 from the service
				WS.verifyElementPropertyValue(resp, 'message', 'Nothing to geocode')
			}
		} else if (expectedStatus == 401) {
			// 401 Unauthorized
			WS.verifyElementPropertyValue(resp, 'cod', 401)
			WS.verifyElementPropertyValue(resp, 'message',
					'Invalid API key. Please see https://openweathermap.org/faq#error401 for more info.')
		}
	}













	@Keyword
	def static void getFiveDayForecast(String lat, String lon, String apiKey, int expectedStatus) {
		ResponseObject resp = WS.sendRequest(
				findTestObject('Postman/5_day_weather_jakarta', [
					('lat_jaksel'): lat,
					('lon_jaksel'): lon,
					('apiKey')    : apiKey
				])
				)



		// 1) Verify HTTP status
		WS.verifyResponseStatusCode(resp, expectedStatus)

		if (expectedStatus == 200) {
			// 2) Non-empty body
			WS.verifyNotEqual(resp.getResponseBodyContent().trim(), '')

			// 3) Parse the JSON
			def payload = new JsonSlurper().parseText(resp.getResponseBodyContent())

			// 4) Extract the date part (YYYY-MM-DD) from each dt_txt, then unique()
			def distinctDates = payload.list
					.collect { it.dt_txt[0..9] }    // "2025-04-28 12:00:00" → "2025-04-28"
					.unique()

			// 5) Assert exactly 6 distinct days
			WS.verifyEqual(distinctDates.size(), 6)

			// 6) For each of those dates, assert there's at least one entry
			distinctDates.each { d ->
				def countForDay = payload.list.count { it.dt_txt.startsWith(d) }
				WS.verifyGreaterThan(countForDay, 0)
			}

			// 7) (Optional) spot-check the very first entry
			WS.verifyElementPropertyValue(resp, 'list[0].main.temp',
					WS.getElementPropertyValue(resp, 'list[0].main.temp'))
		}
		else  if (expectedStatus == 400) {
			// always assert the code
			WS.verifyElementPropertyValue(resp, 'cod', '400')

			// now decide which message to expect
			boolean latIsNum = (lat ==~ /^-?\d+(\.\d+)?$/)
			boolean lonIsNum = (lon ==~ /^-?\d+(\.\d+)?$/)

			if (!latIsNum) {
				// any non-numeric latitude (including empty) → wrong latitude
				WS.verifyElementPropertyValue(resp, 'message', 'wrong latitude')
			}
			else if (!lonIsNum) {
				// any non-numeric longitude (including empty) → wrong longitude
				WS.verifyElementPropertyValue(resp, 'message', 'wrong longitude')
			}
			else {
				// both numeric but still a 400 from the service
				WS.verifyElementPropertyValue(resp, 'message', 'Nothing to geocode')
			}
		} else if (expectedStatus == 401) {
			// 401 Unauthorized
			WS.verifyElementPropertyValue(resp, 'cod', 401)
			WS.verifyElementPropertyValue(resp, 'message',
					'Invalid API key. Please see https://openweathermap.org/faq#error401 for more info.')
		}
	}
}