package mdm.cuf.personbsm.db;

import mdm.cuf.core.db.AbstractMdmCufCoreDbTest;

/**
 * This unit test executes the database schema scripts against an in-memory H2 database in order
 * to enable CI verification of changes to the core vet360 database schema. This will let us know before the next
 * deployment if there are issues with the database scripts.
 * 
 * @author darias
 */
public class PersonBsmDb_UnitTest extends AbstractMdmCufCoreDbTest {
    
    /** liquibase change log path for mdm-cuf-personbsm-db project */ 
    private static final String LIQUIBASE_CHANGE_LOG_PATH = "classpath:/cuf-personbsm-database/mdm.cuf.personbsm-master-changelog.xml";
    
    /** liquibase change log path for mdm-cuf-personbsm-db project */ 
    @Override
    public String getChangeLogPath() {
        return LIQUIBASE_CHANGE_LOG_PATH;
    }

}
