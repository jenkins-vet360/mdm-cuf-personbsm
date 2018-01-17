
#################################################
#  Drafted 1/12/2018 by Rachel Fulton		#
#  Reviewed by Amy Rosenthal 1/15/2018		#
#  Edits Made on 1/16/2017:			#
#	-changed correlationTimeMillis type to	#
#	datetime				#
#	- removed IOC column from object	#
#################################################

Feature: Listening to the error queue and transforming the BIOs in the exception queue to something that can be consumed by the BSM

Scenario Outline:
Given the "<CufErrorCode>" is passed to the BSM
When the BSM handler receives the "<CufErrorCode>"
Then the BSM handler will pass the "<messageToDisplay>" in the proper format to the BSM
Examples:
  | CufErrorCode | messageToDisplay                                                                                                                 | 
      | PHON104      | Area Code must be three characters                                                                                               | 
      | PHON105      | Phone Number cannot be empty                                                                                                     | 
      | PHON106      | Cannot contain letters                                                                                                           | 
      | PHON107      | Phone Number must be between 1 and 14 characters                                                                                 | 
      | PHON108      | Phone Number Extension must be bless than 11 characters                                                                          | 
      | PHON109      | Phone Type cannot be empty                                                                                                       | 
      | PHON110      | Phone Type must be: Mobile, Work, Fax, Home, or Pager                                                                            | 
      | PHON111      | Effective Start Date needs to be in  YYYY-MM-DD format                                                                           | 
      | PHON112      | Effective End Date needs to be in  YYYY-MM-DD format                                                                             | 
      | PHON113      | Text Message Capable Indicator must be either true or false                                                                      | 
      | PHON114      | Text Message Permission Indicator must be either true or false                                                                   | 
      | PHON115      | Voicemail Permission Indicator must be either true or false                                                                      | 
      | PHON116      | TTY Indicator must be either true or false                                                                                       | 
      | PHON117      | Connection Status Code must match: no known problem, no answer, non working number, no correct number, or null                   | 
      | PHON118      | Confirmation Date must be in YYYY-MM-DD format                                                                                   | 
      | PHON119      | Source Date must not be null                                                                                                     | 
      | PHON120      | Source Date must be in YYYY-MM-DDTHH:MM:SSZ  format                                                                              | 
      | PHON121      | Source Date is missing time stamp                                                                                                | 
      | PHON123      | Telephone ID provided does not exist                                                                                             | 
      | PHON124      | You cannot have a telephone                                                                                                      | 
      | PHON125      | Telephone ID cannot be null                                                                                                      | 
      | PHON126      | Area Code must contain only numbers                                                                                              | 
      | PHON201      | Effective Start Date can not be more than 6 months after Source Date                                                             | 
      | PHON205      | Area Code contains an invalid value                                                                                              | 
      | PHON206      | Confirmation Date can not be in the future                                                                                       | 
      | PHON207      | Domestic phone number size must be  7 characters                                                                                 | 
      | PHON300      | Effective End date has passed - cannot modify an existing inactive record                                                        | 
      | PHON301      | Can not insert a record for a phone type that already exists. Pull the phone record and update it using the telphoneId provided. | 
      | PHON302      | Effective End Date can not be in the past and must be after Effective Start Date                                                 | 
      | PHON304      | Cannot accept a request with multiple phones of the same type                                                                    | 
      | PHON305      | Cannot update the record if Phone Type  does not match: Mobile, Work, Fax or Home                                                | 
  

  Scenario Outline: Translating Email Error Codes that are going into the BSM to be seen by the data steward
    Given the "<CufErrorCode>" is passed to the BSM
     When the BSM handler receives the "<CufErrorCode>"
     Then the BSM handler will pass the "<messageToDisplay>" in the proper format to the BSM
    Examples: 
      | CufErrorCode | messageToDisplay                                                                                                                                                                                                                                                    | 
      | EMAIL101     | Email Address cannot be empty                                                                                                                                                                                                                                       | 
      | EMAIL102     | Effective Start Date needs to be in  YYYY-MM-DD format                                                                                                                                                                                                              | 
      | EMAIL103     | Effective End Date needs to be in  YYYY-MM-DD format                                                                                                                                                                                                                | 
      | EMAIL104     | Email Permission Indicator must be either true or false                                                                                                                                                                                                             | 
      | EMAIL105     | emailStatusCode must match:  no known problem, returned undeliverable or incorrect address                                                                                                                                                                        	 | 
      | EMAIL106     | Confirmation Date needs to be in  YYYY-MM-DD format                                                                                                                                                                                                                 | 
      | EMAIL107     | Effective Start Date cannot be empty                                                                                                                                                                                                                                | 
      | EMAIL108     | Source Date needs to be in YYYY-MM-DDTHH:MM:SSZ  format and cannot be in the future                                                                                                                                                                                 | 
      | EMAIL109     | Source Date needs to be in YYYY-MM-DDTHH:MM:SSZ  format                                                                                                                                                                                                             | 
      | EMAIL110     | Email ID does not exist                                                                                                                                                                                                                                             | 
      | EMAIL201     | Email ID cannot be empty                                                                                                                                                                                                                                            | 
      | EMAIL202     | Effective Start Date can not be more than 6 months after Source Date                                                                                                                                                                                                | 
      | EMAIL204     | Confirmation Date can not be in the future                                                                                                                                                                                                                          | 
      | EMAIL205     | Effective End Date can not be in the past and must be after Effective Start Date                                                                                                                                                                                    | 
      | EMAIL206     | Confirmation Date can not be older than Source Date                                                                                                                                                                                                                 | 
      | EMAIL300     | This Record is inactive                                                                                                                                                                                                                                             | 
      | EMAIL301     | Can not insert a record for an email address that already exists. Please pull the email record and update it using the emailId provided                                                                                                                             | 
      | EMAIL302     | email Address Text must be between 0 and 255 characters                                                                                                                                                                                                             | 
      | EMAIL303     | The local part of the email cannot exceed 65 characters                                                                                                                                                                                                             | 
      | EMAIL304     | The Domain name of the email cannot exceed 64 characters                                                                                                                                                                                                            | 
      | EMAIL305     | Email Address Text cannot have 2 @ symbols, must have at least one period '.' after the @ character, and cannot have  '.%' or '%.' or '%..%' or " ( ) , : ; < > @ [ ] \ or space unless in a quoted string in the local part.                                       | 
      | EMAIL306     | Cannot have more than one type of email address                                                                                                                                                                                                                     | 
      | EMAIL307     | Cannot modify an existing inactive record.                                                                                                                                                                                                                          | 

  Scenario Outline: Translating Address Error Codes that are going into the BSM to be seen by the data steward
    Given the "<CufErrorCode>" is passed to the BSM
     When the BSM handler receives the "<CufErrorCode>"
     Then the BSM handler will pass the "<messageToDisplay>" in the proper format to the BSM
    Examples: 
      | CufErrorCode | messageToDisplay                                                                                             | 
      | ADDR101      | Address Type cannot be empty                                                                                 | 
      | ADDR102      | Effective Start Date needs to be in  YYYY-MM-DD format                                                       | 
      | ADDR103      | Effective End Date needs to be in  YYYY-MM-DD format                                                         | 
      | ADDR104      | Confirmation Date needs to be in  YYYY-MM-DD format                                                          | 
      | ADDR105      | Source Date needs to be in YYYY-MM-DDTHH:MM:SSZ  format                                                      | 
      | ADDR106      | Source Date is missing a time stamp                                                                          | 
      | ADDR107      | Address ID provided does not exist                                                                           | 
      | ADDR110      | Address Purpose of use cannot be empty                                                                       | 
      | ADDR110      | Address Purpose of use cannot be more than 35 character                                                      | 
      | ADDR108      | Address Line 1 cannot be more than 100 characters on input and cannot be more than 40 for storage            | 
      | ADDR109      | Address Line 1 must contain only letters and numbers                                                         | 
      | ADDR110      | Address Line 2 cannot be more than 100 characters on input and cannot be more than 40 for storage            | 
      | ADDR111      | Address Line 2 must contain only letters and numbers                                                         | 
      | ADDR112      | Address Line 3 cannot be more than 100 characters on input and cannot be more than 40 for storage            | 
      | ADDR113      | Address Line 3 must contain only letters and numbers                                                         | 
      | ADDR114      | Bad Address Indicator must be true or false                                                                  | 
      | ADDR115      | Bad Address indicator cannot be more than 35 characters                                                      | 
      | ADDR116      | Bad Address indicator cannot be more than 35 characters                                                      | 
      | ADDR117      | Bad Address indicator must be alphanumeric                                                                   | 
      | ADDR118      | City Name cannot be more than 100 characters on input and cannot be more than 40 for storage                 | 
      | ADDR119      | City Name must contain only letters and numbers                                                              | 
      | ADDR120      | State code cannot be more than 2 character                                                                   | 
      | ADDR121      | State code must have only letters                                                                            | 
      | ADDR122      | Zip Code cannot be more than 5 characters                                                                    | 
      | ADDR123      | Zip Code must contain only letters and numbers                                                               | 
      | ADDR124      | Zip Code 4 cannot be more than 4 characters                                                                  | 
      | ADDR125      | Zip Code 4 must contain only letters and numbers                                                             | 
      | ADDR126      | Province Name cannot be more than 100 characters on input and cannot be more than 40 for storage             | 
      | ADDR127      | Province name must contain only letters and numbers                                                          | 
      | ADDR128      | International Postal Code cannot be more than 100 characters on input and cannot be more than 40 for storage | 
      | ADDR129      | International Postal code must contain only numbers                                                          | 
      | ADDR130      | Country Name cannot be more than 35 characters                                                               | 
      | ADDR131      | Country Name can be only letters                                                                             | 
      | ADDR132      | FIPS Country Code cannot be more than 3 characters                                                           | 
      | ADDR133      | FIPS Country Code can be only letters or numbers                                                             | 
      | ADDR134      | ISO2 Country Code cannot be more than 2 characters                                                           | 
      | ADDR135      | ISO2 Country Code can be only letters or numbers                                                             | 
      | ADDR136      | ISO3 Country Code cannot be more than 3 characters                                                           | 
      | ADDR137      | ISO3 Country Code can be only letters or numbers                                                             | 
      | ADDR138      | Latitude cannot be more than 35 characters                                                                   | 
      | ADDR139      | Lattitude must be alphanumeric                                                                               | 
      | ADDR140      | Longitude cannot be more than 35 characters                                                                  | 
      | ADDR141      | Longitude must be alphanumeric                                                                               | 
      | ADDR142      | Geocode Precision cannot be more than 35 characters                                                          | 
      | ADDR143      | Geocode Precision must be alphanumeric                                                                       | 
      | ADDR144      | Geocode Date cannot be more than 35 characters                                                               | 
      | ADDR145      | Geocode Date must be in YYYY-MM-DDTHH:MM:SSZ Format                                                          | 
      | ADDR200      | The Address ID in the email bio must be empty for inserts/adds                                               | 
      | ADDR201      | Address ID cannot be empty                                                                                   | 
      | ADDR202      | Effective Start Date can not be more than 6 months after Source Date                                         | 
      | ADDR203      | effectiveEndDate cannot be present when adding an address                                                    | 
      | ADDR204      | Confirmation Date cannot be in the future                                                                    | 
      | ADDR205      | Effective End Date can not be in the past and must be after Effective Start Date                             | 
      | ADDR206      | ConfirmationDate can not be older than Source Date                                                           | 
      | ADDR300      | The Effective End Date has passed, you cannot modify an existing inactive record                             | 
  
  

Scenario Outline: Splitting and Storing BIOS
Given An update containing "<biosEntering>" comes into the BSM
And The update has more than one BIO
When "<biosEntering>" comes into the BSM handler
Then "<biosWithErrors>" have an error are spilt from the update and are made into individual records
And "<biosStored>" will be held in a temporary cache
And "<callToBSM1>" , "<callToBSM2>", and "<callToBSM3>" are sent to the BSM

Examples:
| biosEntering          | biosWithErrors        | biosStored      | callToBSM1 | callToBSM2 | callToBSM3 |
| email, phone, address | email, phone, address |                 | email      | phone      | address    |
| email, address        | email                 | address         | email      |            |            |
| phone, address        | phone, address        | null            | phone      |  address   |            |
| email                 | email                 | null            | email      |            |            |


 Scenario Outline: Hints in other exceptions
    Given An update containing "<biosEntering>" comes into the BSM
      And The update has more than one BIO
      And the update has more than one error
     When the number of  "<biosEntering>" comes into the BSM handler and is greater than 1
      And and the number of "<biosWithErrors>" is also greater than 1
     Then the "<messageToDisplayEmail>" will be passed as a field into the email exception
      And the "<messageToDisplayPhone>" will be passed as a field into the phone exception
      And the "<messageToDisplayAddress>" will be passed as a field into the address exception
  
    Examples: 
      | ID # | biosEntering          | biosWithErrors        | messageToDisplayEmail                                                  | messageToDisplayPhone                                                   | messageToDisplayAddress                                               | 
      | 123  | email, phone, address | email, phone, address | There is a phone and address exception for this ID number in the queue | There is an email and address exception for this ID number in the queue | There is an email and phone exception for this ID number in the queue | 
      | 125  | phone, address        | phone, address        | null                                                                   | There is an address exception for this ID number in the queue           | There is a phone exception for this ID number in the queue            | 
      | 126  | email, address        | email, address        | There is an address exception for this ID number in the queue          |                                                                         | There is an email exception for this ID number in the queue           | 
      | 127  | email                 | email                 | null                                                                   | null                                                                    | null                                                                  | 
  


   
  Scenario Outline: Return error message based on what Confidence level is returned after address validation
    Given the address BIO with "<addressLine1>", "<addressLine2>", "<city>", and "<state>"  was sent to spectrum for address validation
      And the address BIO returned from address validation and Confidence field has "<Confidence>" 
      And the address is in the exception queue
     When the BSM validates Confidence field and determines that it has "<Confidence>" 
     Then the BSM will display the "<errorToDisplay>" error in the BSM field 'BIOerror'
    Examples: 
      | addressLine1          | addressLine2 | city              | state | Confidence | errorToDisplay                                       | 
      | 5134 Carters Run rd   |              | Marshall Va 20115 |       | 100        | Address could be validated                           | 
      | 5134 Carters Run rd   |              | Marshall          | VA    | 81         | Address could be validated                           | 
      | 5134 Carters run W rd |              | Marshall          | VA    | 77         | Address output is significantly different than input | 
      | 5134 Carters          |              | Marshall          | VA    | 0          | Address can not be validated                         | 


 Scenario:
    Given an address with a valid person is sent to the exception queue
      And the person exists in MVI
     When the system makes a 1306 Get correlated IDs call
     And gets the following fields 
      | fieldsFrom1306            | 
      | LastName                  | 
      | FirstName                 | 
      | MiddleName                | 
      | Mothers Maiden Name (MMN) | 
      | Gender                    | 
      | SSN                       | 
      | Address Line1             | 
      | Address Line2             | 
      | Address Line3             | 
      | Address City              | 
      | Address State             | 
      | Phone Number              | 
      | Date of Birth (DOB)       | 
      
     Then the system will send the following fields to the BSM
      | bsmFields            | 
      | mviFullName          | 
      | mviMothersMaidenName | 
      | mviGender            | 
      | mviAddress           | 
      | mviPhone             | 
      | mviDOB               | 
  
  



