package mdm.cuf.personbsm.server.entity.repository;

import org.junit.Assert;
import org.junit.Test;

import mdm.cuf.personbsm.server.entity.PersonBsmJobStatus;

public class PersonBsmJobStatus_UnitTest {

    @Test
    public void personBsmMessageKeysTestConstructor(){
        Assert.assertEquals("IN_PROGRESS",PersonBsmJobStatus.IN_PROGRESS.name());
        Assert.assertEquals(PersonBsmJobStatus.COMPLETED,PersonBsmJobStatus.fromValue("COMPLETED"));
    }
}
