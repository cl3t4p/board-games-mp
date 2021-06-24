package  com.boardgame.mp.server.controller;



import com.boardgame.mp.server.components.exception.NotFoundByUUID;
import com.boardgame.mp.server.components.exception.PlayerNotFoundByUUID;
import com.boardgame.mp.server.components.data.APIError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * Every exception that his thrown while in a request will be redirected here
 */
@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {


    /**
     * Called for General or Internal Error
     * @return Will Return a JSON with Error Type and Error Message
     */
    @ExceptionHandler(value = Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        APIError error = new APIError(HttpStatus.INTERNAL_SERVER_ERROR,"Server Error",ex);
        return new ResponseEntity<>(error, error.getStatus());
    }

    /**
     * Called when a player is not found
     * @return Will Return a JSON with Error Type and the default message
     */
    @ExceptionHandler(value = PlayerNotFoundByUUID.class)
    public final ResponseEntity<Object> handleNoPlayerFound(PlayerNotFoundByUUID ex){
        APIError error = new APIError(HttpStatus.NOT_FOUND,"Request Error",ex);
        return new ResponseEntity<>(error,error.getStatus());
    }

    /**
     * Called when an Object is not found
     * @return Will Return a JSON with Error Type and Error Message
     */
    @ExceptionHandler(value = NotFoundByUUID.class)
    public final ResponseEntity<Object> handleNoFound(NotFoundByUUID ex){
        APIError error = new APIError(HttpStatus.NOT_FOUND,"Request Error",ex);
        return new ResponseEntity<>(error,error.getStatus());
    }



}
