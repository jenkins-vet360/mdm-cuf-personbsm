package mdm.cuf.personbsm.server.service;

import mdm.cuf.personbsm.api.PersonBsmErrorRequest;
import mdm.cuf.personbsm.api.PersonBsmErrorResponse;

@FunctionalInterface
public interface PersonBsmService {

    public PersonBsmErrorResponse personBsmErrorSubmit(PersonBsmErrorRequest request);

}
