package mdm.cuf.personbsm.server.dio.repository;

import java.util.UUID;

import org.junit.Ignore;

import mdm.cuf.core.server.persist.EntityRepository;
import mdm.cuf.personbsm.server.dio.PersonBsmTask;
import mdm.cuf.personbsm.server.dio.PersonBsmTaskId;


@Ignore
public class PersonBsmTaskRepositories_UnitTest extends AbstractPersonBsmRepositoryTest<PersonBsmTask, PersonBsmTaskId> {

    @Override
    protected EntityRepository<PersonBsmTask, PersonBsmTaskId> getRepository() {
        return personBsmTaskRepository;
    }

    @Override
    public PersonBsmTask getNewUniqueDio() {
        return getNewUniqueChangeLogAudit();
    }

    public static PersonBsmTask getNewUniqueChangeLogAudit() {
        final PersonBsmTask entity = new PersonBsmTask();
        PersonBsmTaskId entityId= new PersonBsmTaskId();
        entityId.setTxAuditId(UUID.randomUUID().toString());
        entityId.setBioIndex(1);
        entityId.setBioType("bio.sample.person");
        entity.setBsmTaskId(entityId);
        entity.setMessages("sample errors");
        entity.setOverride(true);
        return entity;
    }

}
