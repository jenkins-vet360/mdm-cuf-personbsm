
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

#Assumption: WE are receiving a person object it is of the same 



##############################################################################
# Happy Path
# The person error handler receives a Person Update from the CUF
# This Person Update will have a unique transaction ID
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
 
 #Failure Scenarios
 Scenario Outline: Reject a request with a null personTraits object
  Given a validpersonBSM request is sent to the system
  And it has a null personsTraits object
  Then the sytem will reject the request and return the code "BSM400" 
  And the request is stored in a audit table and will not be tended
  Examples:
         | mvi_FullName | mvi_MothersMaidenName | mvi_Gender | mvi_Phone | mvi_DOB | mvi_SSNLast4 | IEN | IENSourceSystem | responseCode |
         |              |                       |            |           |         |              |     |                 | BSM400       |
         | John Smith   |                       |            |           |         |              |     |                 | BSM200       |
         | John Smith   |          Smithy       | Male       | 123456789 | 1/1/99  |     1234     | 123 |      ADR        | BSM200       |

Scenario Outline: Reject a request with a malformed request
 Given the personBSMservice receives a "<malformedRequest>"
 Then the service will return "<BSMresponse>"
 And the request will be stored in an audit table and will not be tended
 
 |  malformedRequest |  

Scenario Outline: Splitting and Storing BIOS
Given An update containing "<biosEntering>" comes into the BSM
And The update has more than one BIO
When "<biosEntering>" comes into the BSM handler
Then "<biosWithErrors>" have an error are spilt from the update and are made into individual records
And "<biosStored>" will be held in a temporary cache
And "<callToBSM1>" , "<callToBSM2>", and "<callToBSM3>" are sent to the BSM

Examples:
      | biosEntering          | biosWithErrors        | biosStored | callToBSMphone | callToBSMemail  | callToBSMaddress  | 
      | email, phone, address | email, phone, address |            | true           | true            | true              | 
      | email, address        | email                 | address    | false          | false           | true              | 
      | phone, address, email | phone, address        |            | true           | false           | true              | 
      | email                 | email                 |            | false          | true            | false             | 


      

  
  



