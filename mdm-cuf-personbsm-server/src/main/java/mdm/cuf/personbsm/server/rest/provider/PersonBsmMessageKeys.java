package mdm.cuf.personbsm.server.rest.provider;

import mdm.cuf.core.api.CufCommonMessageKeys;


/**
 * personBsmErrorSubmit rest end point error keys
 * @author diego.arias
 *
 */
public class PersonBsmMessageKeys extends CufCommonMessageKeys {

    public static final String MESSAGES_EMPTY_KEY = "messages.NotNull";

    public static final String PERSON_BIO_NULL_KEY = "preValidationPersonBio.NotNull";

    public static final String TX_AUDIT_ID_NULL_KEY = "txAuditId.NotNull";

    public static final String PERSON_TRAITS_NULL_KEY = "personTraitsBio.NotNull";
    
    public static final String CALL_BACK_URI_NULL_KEY = "callBackUri.NotNull";


    protected PersonBsmMessageKeys() {
        super();
    }
}
