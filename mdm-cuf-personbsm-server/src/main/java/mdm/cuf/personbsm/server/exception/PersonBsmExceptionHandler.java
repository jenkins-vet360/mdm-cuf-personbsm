package mdm.cuf.personbsm.server.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import mdm.cuf.core.api.ServiceResponse;
import mdm.cuf.core.exception.CufCoreRuntimeException;
import mdm.cuf.core.messages.Message;
import mdm.cuf.core.messages.MessageSeverity;
import mdm.cuf.core.messages.MessageUtils;

@ControllerAdvice
public class PersonBsmExceptionHandler extends ResponseEntityExceptionHandler {

    /** The message source. */
    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CufCoreRuntimeException.class)
    public @ResponseBody ServiceResponse handleRuntimeException(HttpServletRequest request, CufCoreRuntimeException ex) {
        final ServiceResponse serviceResponse = new ServiceResponse();
        Message msg = new Message();
        msg.setPotentiallySelfCorrectingOnRetry(false);
        msg.setSeverity(MessageSeverity.ERROR);
        msg.setKey(ex.getCleanMessage());
        serviceResponse.addMessage(MessageUtils.constructReturnMessage(msg, messageSource));
        return serviceResponse;
    }
}
