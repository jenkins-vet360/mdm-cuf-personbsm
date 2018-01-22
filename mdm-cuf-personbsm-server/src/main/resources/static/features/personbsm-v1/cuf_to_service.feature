
#################################################
#  Drafted 1/12/2018 by Rachel Fulton		#
#  Reviewed by Amy Rosenthal 1/15/2018		#
#  Edits Made on 1/16/2017:			#
#	-changed correlationTimeMillis type to	#
#	datetime				#
#	- removed IOC column from object	#
#-Removed CUFError code #
#################################################


Feature: This feature file describes the piece of custom  code that is called by the CUF to receive updates from the 
 person BSM error submit this is where the update will be seperated then stored or translated and sent to the BSM for tending

##############################################################################
# Happy Path
# The person error handler receives a Person Update from the CUF
# This Person Update will receive a unique transaction ID
# This Update contains at least one error
# This person will contain a collection of address, email and phone objects
# The handler will sort out the errors based on the object, then by the index
# objects with no errors will be sent to the Pending Updates Database
# objects with errors will be sent to the service_to_BSM piece
##############################################################################

Scenario:  Accepting a person BIO from the contact maintenance endpoint
Given a person BIO is sent from the CUF to the Person BSM service
 And this BIO has at least one error in the messages
 And there is less than 2 email objects
 And there is less than 3 address objects
 And there is less than 6 phone objects
 And the personTraits object is not null
 Then the service will receive this request and return BSM200
 
 Scenario Outline: Reject a request with a null personTraits object
  Given a validpersonBSM request is sent to the system
  And it has a null personsTraits object
  Then the sytem will reject the request and return the code "BSM400" 
  Examples:
      | LastName | FirstName | MiddleName | Mothers Maiden Name (MMN) | Gender | SSN | Address Line1 | Address Line2 | Address Line3 | Address City | Address State | Phone Number | Date of Birth (DOB) | responseCodes  |
      |          |           |            |                           |        |     |               |               |               |              |               |              |                     |   BSM400       |
      |  Smith   | Joe       | James      | Browns                    | M      |     | 123 Main st   |               |               |  Town        |    VA         |              |                     |   BSM200       |  
      |  Doe     | Jane      |  NMN       |                           | F      |     |               |               |               |              |               |              |                     |                | 

Scenario Outline: Splitting and Storing BIOS
Given An update containing "<biosEntering>" comes into the BSM
And The update has more than one BIO
When "<biosEntering>" comes into the BSM handler
Then "<biosWithErrors>" have an error are spilt from the update and are made into individual records
And "<biosStored>" will be held in a temporary cache
And "<callToBSM1>" , "<callToBSM2>", and "<callToBSM3>" are sent to the BSM

Examples:
      | biosEntering          | biosWithErrors        | biosStored | callToBSMphone | callToBSM2email | callToBSM3address | 
      | email, phone, address | email, phone, address |            | true           | true            | true              | 
      | email, address        | email                 | address    | false          | false           | true              | 
      | phone, address, email | phone, address        |            | true           | false           | true              | 
      | email                 | email                 |            | false          | true            | false             | 


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
      
     Then "FirstName" "MiddleName" and "LastName" will be concatenated in the format "FirstName" "MiddleName" "LastName"
       And copied into the field "mviFullName" 
       And "<SSN>" will be shortened to "<lastFourSSN>"
       Examples:
      | SSN         | lastFourSSN | 
      | 123 45 6789 | 6789        | 
      | 987 65 4321 | 4321        | 
      | null        | null        | 
 
       And the system willreceive the following mapped fields to send to the BSM
       
      | fieldsFrom1306                      | bsmFields            | 
      | "FirstName" "MiddleName" "LastName" | mvi_FullName          | 
      | Mothers Maiden Name (MMN)           | mvi_MothersMaidenName | 
      | Gender                              | mvi_Gender            | 
      | Phone Number                        | mvi_Phone             | 
      | Date of Birth (DOB)                 | mvi_DOB               | 
      
      
  # Provide concatinations
  
  



