package mdm.cuf.personbsm.server.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mdm.cuf.core.transfer.AbstractTransferObject;

@Embeddable
public class PersonBsmTaskId extends AbstractTransferObject {

    /**
     * 
     */
    private static final long serialVersionUID = -7216734365450281902L;

    private String txAuditId;

    private Integer bioIndex;

    private String bioType;

    @Column(name = "TX_AUDIT_ID", nullable = false, updatable = false , length = 36)
    public String getTxAuditId() {
        return txAuditId;
    }

    public void setTxAuditId(String txAuditId) {
        this.txAuditId = txAuditId;
    }

    @Column(name = "BIO_INDEX", nullable = false)
    public Integer getBioIndex() {
        return bioIndex;
    }

    public void setBioIndex(Integer bioIndex) {
        this.bioIndex = bioIndex;
    }

    @Column(name = "BIO_TYPE", nullable = false , length = 255)
    public String getBioType() {
        return bioType;
    }

    public void setBioType(String bioType) {
        this.bioType = bioType;
    }

}
