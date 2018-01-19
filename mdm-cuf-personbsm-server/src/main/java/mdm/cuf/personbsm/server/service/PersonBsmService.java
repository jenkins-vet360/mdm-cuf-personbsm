package mdm.cuf.personbsm.server.service;

import mdm.cuf.personbsm.api.PersonBsmErrorRequest;
import mdm.cuf.personbsm.api.PersonBsmErrorResponse;

public interface PersonBsmService {

    public PersonBsmErrorResponse personBsmErrorSubmit(PersonBsmErrorRequest request);

}
