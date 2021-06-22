package  com.boardgame.mp.server.controller;



import com.boardgame.mp.server.components.Exception.NotFoundByUUID;
import com.boardgame.mp.server.components.Exception.PlayerNotFoundByUUID;
import com.boardgame.mp.server.components.data.APIError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;




@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        APIError error = new APIError(HttpStatus.INTERNAL_SERVER_ERROR,"Server Error",ex);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(value = PlayerNotFoundByUUID.class)
    public final ResponseEntity<Object> handleNoPlayerFound(PlayerNotFoundByUUID ex){
        APIError error = new APIError(HttpStatus.NOT_FOUND,"Request Error",ex);
        return new ResponseEntity<>(error,error.getStatus());
    }

    @ExceptionHandler(value = NotFoundByUUID.class)
    public final ResponseEntity<Object> handleNoFound(PlayerNotFoundByUUID ex){
        APIError error = new APIError(HttpStatus.NOT_FOUND,"Request Error",ex);
        return new ResponseEntity<>(error,error.getStatus());
    }



}
