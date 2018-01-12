Feature: the testable outcomes of custom dataflow work within the BSM.  These are not custom code pieces, but custom configuration features

Scenario Outline: Display error code from UAM field CouldNotValidate to the Data Steward
	Given the address BIO with "<addressLine1>", "<addressLine2>", "<city>", and "<state>"  was sent to the error queue
	And the address is domestic
	And the confidence is 0
When the address exception is sent to the webservice
	Then the address will be revalidated
  And the CouldNotValidate will be returned
  And the BSM will display the "<fieldResult>" error in the BSM field CouldNotValidate
Examples: 
      | fieldResult             | addressLine1        | addressLine2 | city            | state |  
      | HouseNumber, PostalCode | 12312312 Gordon Ave |              | Charlottesville | VA    | 
      | StreetName, PostalCode  | 1305 Gorgqn Ave     |              | Charlottesville | VA    | 
      | PostalCode, City        | 1305 Gordon Ave     |              | ajljdlsafsda    | VA    | 
  
Scenario Outline: Display error code from UAM field RDI to the Data Steward
    Given the address BIO with "<addressLine1>", "<addressLine2>", "<city>", and "<state>"  was sent to spectrum for address validation
      And the address BIO returned from address validation and RDI field has "<RDI>" 
      And the address is domestic
      And the address is in the exception queue
     When the system validates RDI field and determines that it has "<RDI>" 
     Then the BSM will display the "<errorToDisplay>" error in the BSM field 'TBD'
    Examples: 
      | addressLine1         | addressLine2 | city     | state | RDI  | errorToDisplay                                           | 
      | 5134 Carters Run rd  |              | Marshall | VA    | R    | null                                                     | 
      | 4197-F Winchester Rd |              | Marshall | VA    | B    | The address is a business address                        | 
      | 8453 W Main St       |              | Marshall | VA    | M    | The address is both a residential and a business address | 
      | fake                 |              | nowhere  | VA    | null | This address was unable to be confirmed                  | 
  