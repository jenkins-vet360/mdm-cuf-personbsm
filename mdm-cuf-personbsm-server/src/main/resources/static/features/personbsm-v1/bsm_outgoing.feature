Feature: The operations that are going to take place as an exception leaves the queue
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
| biosEntering          | biosWithErrors        | biosStored      | biosResolved            | exitTheQueue |
| email, phone, address | email, phone, address | null            |  email, phone, address  | true |
| email, address        | email, address        | null            |  email                  | false |
| phone, address        | phone                 | address         | phone                   |true |
| email                 | email                 | null            | null                   | false |




Scenario Outline: Source System Values Update

Given A BIO leaves the exception queue
And That BIO had been altered by a data steward
When the BIO exits the queue 
Then following "<change>" will be made to the "<values>" 
Examples:
| values                  |   change |
| originatingSourceSystem | No Change |
| sourceSystem            | Vet360BSM |
| sourceDate              | time the record exits the BSM in YYYY-MM-DDTHH:MM:SSZ format |





