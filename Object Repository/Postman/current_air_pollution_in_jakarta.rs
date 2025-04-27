<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>current_air_pollution_in_jakarta</name>
   <tag></tag>
   <elementGuidId>c97f832e-c821-444c-a93e-3aaad547da56</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>true</autoUpdateContent>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Accept</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>def198f0-08c2-4ba9-bb90-468e303e73bc</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>10.2.0</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <path></path>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>http://api.openweathermap.org/data/2.5/air_pollution</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>-6.266</defaultValue>
      <description></description>
      <id>ea9bb3d0-e928-441b-af8f-4f283e1e2b67</id>
      <masked>false</masked>
      <name>lat_jaksel</name>
   </variables>
   <variables>
      <defaultValue>106.8135</defaultValue>
      <description></description>
      <id>19c21b4e-cf22-4868-a406-6a56492f3a7f</id>
      <masked>false</masked>
      <name>lon_jaksel</name>
   </variables>
   <variables>
      <defaultValue>'68cf65b2e2fe6aed98fc6847a87ebb21'</defaultValue>
      <description></description>
      <id>836b6aec-9ebb-4cb6-b0f1-b795b61c7172</id>
      <masked>false</masked>
      <name>apiKey</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()

</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
