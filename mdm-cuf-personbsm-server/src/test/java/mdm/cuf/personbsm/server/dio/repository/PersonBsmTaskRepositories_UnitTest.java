package mdm.cuf.personbsm.server.dio.repository;

import java.util.UUID;

import org.junit.Test;

import mdm.cuf.core.server.persist.EntityRepository;
import mdm.cuf.core.server.persist.EntityRepositoryTestHelper;
import mdm.cuf.personbsm.server.dio.PersonBsmTask;
import mdm.cuf.personbsm.server.dio.PersonBsmTaskId;

public class PersonBsmTaskRepositories_UnitTest extends AbstractPersonBsmRepositoryTest {

    @Test
    public void fullCommonSuite(){
        entityRepositoryTestCommon.fullCommonSuite(new EntityRepositoryTestHelper<PersonBsmTask, PersonBsmTaskId>(){
            @Override
            public PersonBsmTask getNewUniqueEntity() {
                return getNewUniquePersonBsmTask();
            }

            @Override
            public EntityRepository<PersonBsmTask, PersonBsmTaskId> getRepository() {
                return personBsmTaskRepository;
            }
        });
    }

    public static PersonBsmTask getNewUniquePersonBsmTask() {
        final PersonBsmTask entity = new PersonBsmTask();
        PersonBsmTaskId entityId= new PersonBsmTaskId();
        entityId.setTxAuditId(UUID.randomUUID().toString());
        entityId.setBioIndex(1);
        entityId.setBioType("bio.sample.person");
        entity.setBsmTaskId(entityId);
        entity.setMessages("sample errors");
        entity.setOverride(true);
        entity.setRecordSts("whatever");
        return entity;
    }

}
