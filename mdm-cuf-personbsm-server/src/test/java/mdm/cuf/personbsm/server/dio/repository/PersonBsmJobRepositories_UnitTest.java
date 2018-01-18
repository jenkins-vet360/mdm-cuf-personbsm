package mdm.cuf.personbsm.server.dio.repository;

import java.util.UUID;

import org.junit.Ignore;

import mdm.cuf.core.server.persist.EntityRepository;
import mdm.cuf.personbsm.server.dio.PersonBsmJob;

@Ignore
public class PersonBsmJobRepositories_UnitTest extends AbstractPersonBsmRepositoryTest<PersonBsmJob, String> {

    @Override
    protected EntityRepository<PersonBsmJob, String> getRepository() {
        return personBsmJobRepository;
    }

    @Override
    public PersonBsmJob getNewUniqueDio() {
        return getNewUniqueChangeLogAudit();
    }

    public static PersonBsmJob getNewUniqueChangeLogAudit() {
        final PersonBsmJob entity = new PersonBsmJob();
        entity.setOrigTxRequest("Sample Request");
        entity.setOrigTxSrcSys("VETSGOV");
        entity.setOrigTxAuditId(UUID.randomUUID().toString());
        return entity;
    }

}
