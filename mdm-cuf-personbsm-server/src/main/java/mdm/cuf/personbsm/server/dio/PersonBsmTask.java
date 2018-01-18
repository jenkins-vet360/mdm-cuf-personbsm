package mdm.cuf.personbsm.server.dio;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import mdm.cuf.core.server.persist.AbstractAuditedEntity;

/**
 * The person data information object.
 *
 * @author jshrader
 */
@Entity
@Table(name = "BSM_TASK")
public class PersonBsmTask extends AbstractAuditedEntity<PersonBsmTaskId> {

    /**
     * 
     */
    private static final long serialVersionUID = -2300861190894370912L;

    private PersonBsmTaskId bsmTaskId;

    private String messages;

    private String bsmApproved;

    private String recordSts;

    private Boolean override;

    @Override
    @Transient
    public PersonBsmTaskId getUniqueId() {
        return getBsmTaskId();
    }

    @EmbeddedId
    public PersonBsmTaskId getBsmTaskId() {
        return bsmTaskId;
    }

    public void setBsmTaskId(PersonBsmTaskId bsmTaskId) {
        this.bsmTaskId = bsmTaskId;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "MESSAGES")
    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "BSM_APPROVED")
    public String getBsmApproved() {
        return bsmApproved;
    }

    public void setBsmApproved(String bsmApproved) {
        this.bsmApproved = bsmApproved;
    }

    @Column(name = "RECORD_STS", nullable = false, length = 10)
    public String getRecordSts() {
        return recordSts;
    }

    public void setRecordSts(String recordSts) {
        this.recordSts = recordSts;
    }

    @Column(name = "OVERRIDE")
    public Boolean getOverride() {
        return override;
    }

    public void setOverride(Boolean override) {
        this.override = override;
    }

}
