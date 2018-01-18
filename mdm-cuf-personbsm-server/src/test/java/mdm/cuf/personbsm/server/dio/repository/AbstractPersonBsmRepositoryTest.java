package mdm.cuf.personbsm.server.dio.repository;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import mdm.cuf.core.server.persist.AbstractJpaRepositoryTest;
import mdm.cuf.core.server.persist.Entity;

/**
 * Base test class for unit testing the Person DIO repositories.
 * 
 * @author jshrader
 * @param <T>
 * @param <ID>
 */
public abstract class AbstractPersonBsmRepositoryTest<T extends Entity<ID>, ID extends Serializable>
        extends AbstractJpaRepositoryTest<T, ID> {

    @Autowired
    protected PersonBsmJobRepository personBsmJobRepository;

    @Autowired
    protected PersonBsmTaskRepository personBsmTaskRepository;

}
