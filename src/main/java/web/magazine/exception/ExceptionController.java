//package web.magazine.exception;
//
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.boot.web.error.ErrorAttributeOptions;
//import org.springframework.boot.web.servlet.error.ErrorAttributes;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.context.request.WebRequest;
//
//import java.util.Map;
//
//@Controller
//@FieldDefaults(level = AccessLevel.PRIVATE)
//@RequiredArgsConstructor
//
//public class ExceptionController {
//    private static final String PATH = "/error";
//
//    ErrorAttributes errorAttributes;
//
//    @RequestMapping(ExceptionController.PATH)
//    public ResponseEntity<AppError> error(WebRequest webRequest) {
//
//        Map<String, Object> attributes = errorAttributes.getErrorAttributes(
//                webRequest,
//                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.EXCEPTION, ErrorAttributeOptions.Include.MESSAGE)
//        );
//
//        return ResponseEntity
//                .status((Integer) attributes.get("status"))
//                .body(AppError
//                        .builder()
//                        .error((String) attributes.get("error"))
//                        .message((String) attributes.get("message"))
//                        .build()
//                );
//    }
//}
