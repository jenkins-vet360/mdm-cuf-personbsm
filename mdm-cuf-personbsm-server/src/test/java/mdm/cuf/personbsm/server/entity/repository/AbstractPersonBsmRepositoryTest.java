package mdm.cuf.personbsm.server.entity.repository;

import org.springframework.beans.factory.annotation.Autowired;

import mdm.cuf.core.server.persist.EntityRepositoryTestCommon;
import mdm.cuf.personbsm.server.PersonBsmServerSpringTestBase;

/**
 * Base test class for unit testing the Person DIO repositories.
 * 
 * @author jshrader
 * @param <T>
 * @param <ID>
 */
public abstract class AbstractPersonBsmRepositoryTest extends PersonBsmServerSpringTestBase {

    @Autowired
    protected EntityRepositoryTestCommon entityRepositoryTestCommon;
    
    @Autowired
    protected PersonBsmJobRepository personBsmJobRepository;

    @Autowired
    protected PersonBsmTaskRepository personBsmTaskRepository;

}
