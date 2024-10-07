package hello.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try {
            if( ex instanceof IllegalArgumentException) {

                log.info("IllegalArgumentException resolver to 400");
                //오류를 여기서 먹어버리고 새로운 오류코드로 보낸다.
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView(); // 그냥 빈 ModelAndView 반환
            }

        } catch (IOException e) {
            log.error("resolver ex", e);
        }

        return null;
    }
}
