package scm.bulletinboard.system.validation.file;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class FileExceptionHandler {
    @ExceptionHandler(value = MultipartException.class)
    public ModelAndView handleFileUploadException(MultipartException mpex, HttpServletRequest request) {

        ModelAndView modelAndVew = new ModelAndView("uploadcsv");
        modelAndVew.addObject("csvError", "*File size is maximum 2MB");
        return modelAndVew;
    }
}
