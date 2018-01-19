#################################################
#  Drafted 1/19/2018 by Rachel Fulton		#
#################################################



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
  
