package mdm.cuf.personbsm.server.dio;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import mdm.cuf.core.server.persist.AbstractAuditedEntity;

@Entity
@Table(name = "BSM_JOB")
public class PersonBsmJob extends AbstractAuditedEntity<String> {

    /**
     * 
     */
    private static final long serialVersionUID = 848604325033519114L;

    private String origTxAuditId;

    private String origTxRequest;

    private String origTxSrcSys;

    private String maintenanceRequest;

    private String maintenanceTxAuditId;

    private PersonBsmJobStatus status;

    @Override
    @Transient
    public String getUniqueId() {
        return getOrigTxAuditId();
    }

    @Id
    @Column(name = "ORIG_TX_REQUEST_ID")
    public String getOrigTxAuditId() {
        return origTxAuditId;
    }

    public void setOrigTxAuditId(String origTxAuditId) {
        this.origTxAuditId = origTxAuditId;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ORIG_TX_REQUEST")
    public String getOrigTxRequest() {
        return origTxRequest;
    }

    public void setOrigTxRequest(String origTxRequest) {
        this.origTxRequest = origTxRequest;
    }

    @Column(name = "ORIG_TX_SRC_SYS", length = 255)
    public String getOrigTxSrcSys() {
        return origTxSrcSys;
    }

    public void setOrigTxSrcSys(String origTxSrcSys) {
        this.origTxSrcSys = origTxSrcSys;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "MAINTENANCE_REQUEST")
    public String getMaintenanceRequest() {
        return maintenanceRequest;
    }

    public void setMaintenanceRequest(String maintenanceRequest) {
        this.maintenanceRequest = maintenanceRequest;
    }

    @Column(name = "MAINTENANCE_TX_AUDIT_ID")
    public String getMaintenanceTxAuditId() {
        return maintenanceTxAuditId;
    }

    public void setMaintenanceTxAuditId(String maintenanceTxAuditId) {
        this.maintenanceTxAuditId = maintenanceTxAuditId;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    public PersonBsmJobStatus getStatus() {
        return status;
    }

    public void setStatus(PersonBsmJobStatus status) {
        this.status = status;
    }

}
