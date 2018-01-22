#################################################
#  Drafted 1/12/2018 by Rachel Fulton		#
#  Reviewed by Amy Rosenthal 1/15/2018		#
#  Edits Made on 1/16/2017:			#
#	-Changed Source System from Vet360 to Vet360BSM	#
#################################################



Feature: The operations that are going to take place as an exception leaves the queue.  The BSM will call a REST endpoint to a 
microservice that will rehydrate the records and prepare them to be sent back to the CUF

#############################################
# Happy Path
# A record is sent from the exception queue to the REST endpoint as a JSON request
# The source system values are updated to reflect the records were manipulated
# The record is matched via a transaction ID to the record within the database of good BIOs
# The record is sent back to the CUF
#############################################

Scenario Outline: Reconstitute a BIO
Given A BIO exception record is resolved
And There are no other BIOs associated with that transaction
Then The BIO will be sent to the CUF through the Contact Info Maintenence Point


Scenario Outline: Reconstitute more than one BIO with errors
Given An update with "<biosEntering>" is in the BSM
When the number of "<biosResolved>" is equal to the number of "<biosWithErrors>"
Then Those BIOs will be stitched together 
And "<exitTheQueue>" will be true
And The BIOs will be sent to the CUF through the Contact Info Maintenence Point in one transaction
Examples:
| biosEntering          | biosWithErrors        | biosStored      | biosResolved            | exitTheQueue  | storedInDB  |
| email, phone, address | email, phone, address | null            |  email, phone, address  | true          | false       |
| email, address        | email, address        | null            |  email                  | false         |  true       |
| phone, address        | phone                 | address         |  phone                  | true          | false       |
| email                 | email                 | null            |  null                   | false         |  false      |



Scenario Outline: Source System Values Update

Given A BIO leaves the exception queue
And That BIO had been altered by a data steward
When the BIO exits the queue 
Then following "<change>" will be made to the "<values>" 
Examples:
| values                  |   change                                                      |
| originatingSourceSystem | No Change                                                     |
| sourceSystem            | Vet360BSM                                                     |
| sourceDate              | time the record exits the BSM in YYYY-MM-DDTHH:MM:SSZ format  |





