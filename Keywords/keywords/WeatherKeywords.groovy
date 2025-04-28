package keywords

import com.kms.katalon.core.annotation.Keyword
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.ResponseObject

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
			// 2) Non-empty body
			WS.verifyNotEqual(resp.getResponseBodyContent().trim(), '')

			// 3) “list” array has at least one element
			int count = WS.getElementsCount(resp, 'list')
			WS.verifyGreaterThan(count, 0)

			// 4) Spot-check a field inside the first element
			WS.verifyElementPropertyValue(resp, 'list[0].main.aqi', 1)
		} else if (expectedStatus == 400) {
			WS.verifyElementPropertyValue(resp, 'cod', '400')

			boolean latIsNum = (lat ==~ /^-?\d+(\.\d+)?$/)
			boolean lonIsNum = (lon ==~ /^-?\d+(\.\d+)?$/)

			if (!latIsNum) {
				WS.verifyElementPropertyValue(resp, 'message', 'wrong latitude')
			}
			else if (!lonIsNum) {
				WS.verifyElementPropertyValue(resp, 'message', 'wrong longitude')
			}
		} else if (expectedStatus == 401) {
			// “Invalid API key” error
			WS.verifyElementPropertyValue(resp, 'cod', 401)
			WS.verifyElementPropertyValue(
					resp,
					'message',
					'Invalid API key. Please see https://openweathermap.org/faq#error401 for more info.'
					)
		}
	}

				)
	}
}
