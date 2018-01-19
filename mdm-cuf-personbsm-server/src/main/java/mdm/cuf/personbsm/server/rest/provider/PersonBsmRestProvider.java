package mdm.cuf.personbsm.server.rest.provider;

import mdm.cuf.personbsm.api.PersonBsmCorrectedBioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mdm.cuf.core.api.CufCommonMessageKeys;
import mdm.cuf.core.messages.Message;
import mdm.cuf.core.messages.MessageSeverity;
import mdm.cuf.core.server.rest.provider.AbstractRestProvider;
import mdm.cuf.core.server.rest.provider.MsgKeyGen;
import mdm.cuf.core.server.rest.provider.SwaggerCommon;
import mdm.cuf.personbsm.api.PersonBsmErrorRequest;
import mdm.cuf.personbsm.api.PersonBsmErrorResponse;
import mdm.cuf.personbsm.server.PersonBsmServerProperties;
import mdm.cuf.personbsm.server.service.PersonBsmService;

/**
 * The PersonBsm App Rest endpoint.
 *
 * @author darias
 */
@RequestMapping(value = PersonBsmRestProvider.URL_PREFIX)
@RestController
@Api(tags = PersonBsmRestProvider.TAG)
public class PersonBsmRestProvider extends AbstractRestProvider {
    
    @Autowired
    private PersonBsmService personBsmService;

    /** The version of this rest endpoint */
    protected static final String VERSION = "1";

    /** The URL prefix of this endpoint. */
    protected static final String URL_PREFIX = "/personbsm/v" + VERSION;

    /** the message keys file url */
    protected static final String MSG_KEYS_URL = "swagger/personbsm-v" + VERSION + "-msg-keys.html";
    
    /** the req's file url */
    protected static final String REQUIREMENTS_URL = "features/personbsm-v" + VERSION + "/index.html";
    
    /** The tag used in swagger documentation. */
    protected static final String TAG = "PersonBsm-v" + VERSION;
    
    /** The description that shows up in swagger documentation. */
    protected static final String DESCRIPTION = "";
    
    @Autowired
    private PersonBsmServerProperties appServerProperties;
    
    @RequestMapping(value = "/personbsmerrorsubmit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    @ApiOperation(value = "PersonBSM Error Entry.", notes = "Will send the PersonBio into the PersonBSM error resolution process.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = SwaggerCommon.MESSAGE_200) })
    @MsgKeyGen( keyInterfaces={PersonBsmMessageKeys.class})
    public ResponseEntity<PersonBsmErrorResponse> personBsmErrorSubmit(@RequestBody PersonBsmErrorRequest request) {
        return new ResponseEntity<>(personBsmService.personBsmErrorSubmit(request), HttpStatus.OK);
    }

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    @ApiOperation(value = "PersonBSM Error Entry.", notes = "Will send the PersonBio into the PersonBSM error resolution process.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = SwaggerCommon.MESSAGE_200) })
    @MsgKeyGen(keyInterfaces={PersonBsmMessageKeys.class})
    public ResponseEntity<PersonBsmErrorResponse> personBsmCorrectedBioSubmit(@RequestBody PersonBsmCorrectedBioRequest request) {
        PersonBsmErrorResponse response = new PersonBsmErrorResponse();
        response.addMessage(new Message(MessageSeverity.INFO, "GOT_IT", "Not sure what sort of response we want to send back to caller, any form of tx for them to use for tracking?!?!"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
