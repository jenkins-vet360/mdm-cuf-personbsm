#################################################
#  Drafted 1/12/2018 by Rachel Fulton		#
#  Reviewed by Amy Rosenthal 1/15/2018		#
#  Edits Made on 1/16/2017:			#
#	-changed correlationTimeMillis type to	#
#	datetime				#
#	- removed IOC column from object	#
#################################################


Feature: the request and response objects for the BSM will be identical, this feature defines what this request/ response looks like

    Given the valid messages object:
      | attributeName                    | type    | 
      | code                             | string  | 
      | key                              | string  | 
      | potentiallySelfCorrectingOnRetry | boolean | 
      | severity                         | string  | 

    Given a valid personIdentityBIO:
      | attributeName           | type     | 
      | correlationTimeMillis   | datetime | 
      | createDate              | datetime | 
      | dob                     | date     | 
      | firstName               | string   | 
      | gender                  | string   | 
      | lastName                | string   | 
      | middleName              | string   | 
      | mviCorrelations         | array    | 
      | originatingSourceSystem | string   | 
      | otherIdentifiers        | object   | 
      | sourceDate              | datetime | 
      | sourceSystem            | string   | 
      | sourceSystemUser        | string   | 
      | txAuditId               | string   | 
      | updateDate              | datetime | 
      | versionHash             | ??       | 
      | vet360Id                | ??       | 
  
  And given the otherIdentifiers object can be defined as:
      | attributeName | type   | 
      | edipn         | string | 
      | enrollmentIen | string | 
      | filenumber    | string | 
      | icn           | string | 
      | pid           | string | 
      | vet360Id      | string | 
  

   #Pending CR add county fields- use requirements from CI feature file
    With Address Objects:
      | Attribute Name            | Coded Value         | Mandatory/Optional | Type            | BIO Field Length | Stored Field Length | Standard | common/core |
      | Address ID                | addressID           | Mandatory          | String          | 255              | 255                 | none     | core        |
      | Originating Source System | orginatingSourceSys | Optional           | String          | 255              | 255                 | none     | core        |
      | Source System             | sourceSystem        | Optional           | String          | 255              | 255                 | none     | core        |
      | Source System User        | sourceSysUser       | Optional           | String          | 255              | 255                 | none     | core        |
      | Source Date               | sourceDate          | Mandatory          | Date/Time (GMT) |                  |                     | ISO 8601 | core        |
      | Confirmation Date         | addressConfDate     | Optional           | Date            |                  |                     | ISO 8601 |             |
      | Effective Start Date      | effectiveStartDate  | Mandatory          | Date            |                  |                     | ISO 8601 |             |
      | Effective End Date        | effectiveEndDate    | Optional           | Date            |                  |                     | ISO 8601 |             |
      | Address Type              | addressType         | Optional           | String          | 35               | 35                  | none     |             |
      | Address Purpose of Use    | addressPOU          | Mandatory          | enumerated list |                  |                     | none     |             |
      | Bad Address Indicator     | badAddressIndicator | Optional           | String          | 35               | 35                  | none     |             |
      | Address Line 1            | addressLine1        | Optional           | String          | 100              | 35                  | USPS/UPN |             |
      | Address Line 2            | addressLine2        | Optional           | String          | 100              | 35                  | USPS/UPN |             |
      | Address Line 3            | addressLine3        | Optional           | String          | 100              | 35                  | USPS/UPN |             |
      | City Name                 | cityName            | Optional           | String          | 100              | 35                  | USPS/UPN |             |
      | State Code                | stateCode           | Optional           | String          | 2                | 2                   | USPS P59 |             |
      | Zip Code 5                | zipCode5            | Optional           | String          | 5                | 5                   | USPS     |             |
      | Zip Code 4                | zipCode4            | Optional           | String          | 4                | 4                   | USPS     |             |
      | Province Name             | provinceName        | Optional           | String          | 100              | 40                  | USPS/UPN |             |
      | International Postal Code | intPostalCode       | Optional           | String          | 100              | 40                  | UPN      |             |
      | Country Name ISO3         | countryName         | Optional           | String          | 35               | 35                  | ISO 3166 |             |
      | CountryCode FIPS          | countryCodeFIPS     | Optional           | String          | 3                | 2                   | FIPS     |             |
      | CountryCode ISO2          | countryCodeISO2     | Optional           | String          | 3                | 2                   | ISO 3166 |             |
      | CountryCode ISO3          | countryCodeISO3     | Optional           | String          | 3                | 3                   | ISO 3166 |             |
      | Confidence Score          | confidenceScore     | Optional           | String          | 3                | 3                   | none     |             |
      | Latitude Coordinate       | latitude            | Optional           | String          | 35               | 35                  | USPS/UPN |             |
      | Longitude Coordinate      | longitude           | Optional           | String          | 35               | 35                  | USPS/UPN |             |
      | Geocode Precision Level   | geocodePrecision    | Optional           | String          | 35               | 35                  | USPS/UPN |             |
      | Geocode Calculated Date   | geocodeDate         | Optional           | Date            | 35               | 35                  | ISO 8601 |             |
      | Override Indicator        | overrideIndicator   | Optional           | String          | 35               | 35                  | none     |             |
 
 And Email Objects:
	| Attribute Name                    | Coded Value            | Mandatory/Optional | Type (length)   | Length | Standard | common/core | 
	| Effective Start Date              | effectiveStartDate     | Mandatory          | Date            |        | ISO 8601 |             |
	| Effective End Date                | effectiveEndDate       | Optional           | Date            |        | ISO 8601 |             |  
	| Email Address                     | emailAddressText       | Mandatory          | String          | 255    | none     |             |
	| Email Permission To Use Indicator | emailPermInd           | Optional           | Boolean         |        | none     |             |
	| Email Delivery Status Code        | emailStatusCode        | Optional           | Enumerated List |        | none     |             |
	| Confirmation Date                 | emailConfDate          | Optional           | Date            |        | ISO 8601 |             |
	| Source System                     | sourceSystem           | Optional           | String          | 255    | none     | core        |
	| Originating Source System         | orginatingSourceSys    | Optional           | String          | 255    | none     | core        |
	| Source System User                | sourceSysUser          | Optional           | String          | 255    | none     | core        |
	| Source Date                       | sourceDate             | Mandatory          | Date/Time (GMT) |        | ISO 8601 | core        |
    	| Email ID                          | emailId		     | Optional           | String          |        | none     |             |

And Phone Objects:
	| Attribute Name                    | Coded Value            | Mandatory/Optional | Type (length)   | Length | Standard | common/core |
	| International Indicator           | internationalInd       | Mandatory          | Boolean         |        | none     |             | 
	| Country Code                      | countryCode            | Optional           | Enumerated List |        | E.164    |             |
	| Area Code                         | areaCode               | Optional           | String          | 3      | none     |             | 
	| Phone Number                      | phoneNumber            | Mandatory          | String          | 14     | none     |             | 
	| Phone Number Extension            | phoneNumberExt         | Optional           | String          | 10     | none     |             | 
	| Phone Type                        | phoneType              | Mandatory          | Enumerated List |        | none     |             |
	| Effective Start Date              | effectiveStartDate     | Mandatory          | Date            |        | ISO 8601 |             |
	| Effective End Date                | effectiveEndDate       | Optional           | Date            |        | ISO 8601 |             |
	| Text Message Capable Indicator    | textMessageCapableInd  | Optional           | Boolean         |        | none     |             | 
	| Text Message Permission Indicator | textMessagePermInd     | Optional           | Boolean         |        | none     |             | 
	| Voice Mail Acceptable Indicator   | voiceMailAcceptableInd | Optional           | Boolean         |        | none     |             |
	| TTY Indicator                     | ttyInd                 | Optional           | Boolean         |        | none     |             | 
	| Connection Status Code            | connectionStatusCode   | Optional           | Enumerated List |        | none     |             | 
	| Confirmation Date                 | ConfDate               | Optional           | Date            |        | ISO 8601 |             |
	| Source System                     | sourceSystem           | Optional           | String          | 255    | none     | core        | 
	| Originating Source System         | orginatingSourceSys    | Optional           | String          | 255    | none     | core        | 
	| Source System User                | sourceSysUser          | Optional           | String          | 255    | none     | core        |
	| Source Date                       | sourceDate             | Mandatory          | Date/Time (GMT) |        | ISO 8601 | core        | 
    	| Telephone ID                      | telephoneId            | Optional           | String          |        | none     |             |
	
	
	Given the BIO is received
	When a BIO error has been resolved
	Then the outgoing object should have the same format as the incoming object
	And no pre validation objects are returned
	
