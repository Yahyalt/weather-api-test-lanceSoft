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
				findTestObject(
				'Postman/current_air_pollution_in_jakarta',
				[

					('lat_jaksel'): lat,
					('lon_jaksel'): lon,
					('apiKey')    : apiKey
				]))


		String body = resp.getResponseBodyContent()
		KeywordUtil.logInfo("🛈 Response body:\n" + body)







		WS.verifyResponseStatusCode(resp, expectedStatus)
		WS.verifyNotEqual(
				resp.getResponseBodyContent().trim(),
				''
				)
	}
}
