#################################################
#  Drafted 1/19/2018 by Rachel Fulton		#
#################################################

#############################################
# Happy Path
# A record is received and sorted by the service, this service then makes a call to the BSM
# The service makes a field for additional exception hints
# The service calls the BSM 

# Assumption:
# There is always a orignatingSourceSystem
# There is always a person in MVI for each exceptions

# Add how many errors are accepted

# Add indecies to exceptions in the scenario
# Add MVI errors
# No data type errors

Scenario Outline: Hints in other exceptions
    Given An update containing "<biosEntering>" comes into the BSM
      And The update has more than one BIO
      And the update has more than one error
     When the number of  "<biosEntering>" comes into the BSM handler and is greater than 1
      And and the number of "<biosWithErrors>" is also greater than 1
     Then the "<messageToDisplayEmail>" will be passed as a field named 'additionalExceptions' into the email exception
      And the "<messageToDisplayPhone>" will be passed as a field named 'additionalExceptions' into the phone exception
      And the "<messageToDisplayAddress>" will be passed as a field named 'additionalExceptions' into the address exception
  
    Examples: 
      | ID # | biosEntering          | biosWithErrors        | messageToDisplayEmail | messageToDisplayPhone | messageToDisplayAddress | 
      | 123  | email, phone, address | email, phone, address | Phone; Address        | Email; Address        | Email; Phone            | 
      | 125  | phone, address        | phone, address        | null                  | Address               | Phone                   | 
      | 126  | email, address        | email, address        | Address               |                       | Email                   | 
      | 127  | email                 | email                 | null                  | null                  | null                    |


email WADL: http://spectrum.vet360.halfakerlabs.com:8080/rest/email_bsm_service?_wadl
phone WADL: http://spectrum.vet360.halfakerlabs.com:8080/rest/phone_bsm_service?_wadl
address WADL: http://spectrum.vet360.halfakerlabs.com:8080/rest/address_bsm_service?_wadl
