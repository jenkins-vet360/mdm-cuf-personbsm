package mdm.cuf.personbsm.server.entity.repository;

import java.util.UUID;

import org.junit.Test;

import mdm.cuf.core.server.persist.EntityRepository;
import mdm.cuf.core.server.persist.EntityRepositoryTestHelper;
import mdm.cuf.personbsm.server.entity.PersonBsmJob;
import mdm.cuf.personbsm.server.entity.PersonBsmJobStatus;

public class PersonBsmJobRepositories_UnitTest extends AbstractPersonBsmRepositoryTest {
    
    @Test
    public void fullCommonSuite(){
        entityRepositoryTestCommon.fullCommonSuite(new EntityRepositoryTestHelper<PersonBsmJob, String>(){
            @Override
            public PersonBsmJob getNewUniqueEntity() {
                return getNewUniquePersonBsmJob();
            }

            @Override
            public EntityRepository<PersonBsmJob, String> getRepository() {
                return personBsmJobRepository;
            }
        });
    }

    public static PersonBsmJob getNewUniquePersonBsmJob() {
        final PersonBsmJob entity = new PersonBsmJob();
        entity.setOrigTxRequest("Sample Request");
        entity.setOrigTxSrcSys("VETSGOV");
        entity.setOrigTxAuditId(UUID.randomUUID().toString());
        entity.setStatus(PersonBsmJobStatus.IN_PROGRESS);
        return entity;
    }

}
