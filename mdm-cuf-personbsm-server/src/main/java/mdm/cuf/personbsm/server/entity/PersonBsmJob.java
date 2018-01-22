package mdm.cuf.personbsm.server.entity;

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
    
    private String callbackUri;

    @Override
    @Transient
    public String getUniqueId() {
        return getOrigTxAuditId();
    }

    @Id
    @Column(name = "ORIG_TX_REQUEST_ID", nullable = false , length = 36)
    public String getOrigTxAuditId() {
        return origTxAuditId;
    }

    public void setOrigTxAuditId(String origTxAuditId) {
        this.origTxAuditId = origTxAuditId;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ORIG_TX_REQUEST", nullable = false )
    public String getOrigTxRequest() {
        return origTxRequest;
    }

    public void setOrigTxRequest(String origTxRequest) {
        this.origTxRequest = origTxRequest;
    }

    @Column(name = "ORIG_TX_SRC_SYS", nullable = false , length = 255)
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

    @Column(name = "MAINTENANCE_TX_AUDIT_ID", length = 36)
    public String getMaintenanceTxAuditId() {
        return maintenanceTxAuditId;
    }

    public void setMaintenanceTxAuditId(String maintenanceTxAuditId) {
        this.maintenanceTxAuditId = maintenanceTxAuditId;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 25)
    public PersonBsmJobStatus getStatus() {
        return status;
    }

    public void setStatus(PersonBsmJobStatus status) {
        this.status = status;
    }

    @Column(name = "CALLBACK_URI", nullable = false , length = 2083)
    public String getCallbackUri() {
        return callbackUri;
    }

    public void setCallbackUri(String callbackUri) {
        this.callbackUri = callbackUri;
    }

}
