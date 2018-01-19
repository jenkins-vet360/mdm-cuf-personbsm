
#################################################
#  Drafted 1/12/2018 by Rachel Fulton		#
#  Reviewed by Amy Rosenthal 1/15/2018		#
#  Edits Made on 1/16/2017:			#
#	-changed correlationTimeMillis type to	#
#	datetime				#
#	- removed IOC column from object	#
#-Removed CUFError code #
#################################################

Feature: This feature file describes the piece of custon code that is called by the CUF to receive updates from the 
contact maintenance endpoint. this is where the update will be seperated, stores or translated and sent to the BSM for tending



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
| phone, address        | phone, address        |                 | phone      | address    |            |
| email                 | email                 |                 | email      |            |            |


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
      | mviSSNLast4          |
      | mviPhone             | 
      | mviDOB               | 
  # Provide concatinations
  
  



