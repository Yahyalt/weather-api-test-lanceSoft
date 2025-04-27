import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.testobject.ResponseObject


// 1) Send the API request
ResponseObject response = WS.sendRequest(findTestObject(
	'Postman/current_air_pollution_in_jakarta',
	[
		('apiKey'): GlobalVariable.apiKey,
		('lat_jaksel')   : GlobalVariable.lat_jaksel,
		('lon_jaksel')   : GlobalVariable.lon_jaksel
	]
))

// —— Assert #1: HTTP status & non-empty body ——
WS.verifyResponseStatusCode(response, 200)
WS.verifyNotEqual(WS.getResponseText(response).trim(), '', 'Response body should not be empty')


// print out what Katalon thinks your key is
println "API key = '${GlobalVariable.apiKey}'"



// print the exact URL that was called
println "Called URL: " + response.getUrl()
