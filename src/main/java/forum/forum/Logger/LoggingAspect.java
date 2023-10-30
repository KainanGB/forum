package forum.forum.Logger;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private static final Pattern pattern = Pattern.compile("(?<=password=|email=|phone=)(?!null).*?(?=\\Snull|@|(,\\s)|(\\)$)|$)");
    private static final String REPLACEMENT = "********";

    @Pointcut("@annotation(forum.forum.Logger.Log)")
    public void logPointCut() {}

    @Before("logPointCut()")
    public void log(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        StringBuilder stringParams = new StringBuilder();
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        var method = signature.getName();
        var calledClass = joinPoint.getTarget().getClass().getSimpleName();

        for(int i = 0; i < signature.getParameterNames().length; i++) {
            String param = signature.getParameterNames()[i];
            Object arg = args[i];

            if (i != 0) {
                stringParams.append(", ").append(param).append("=").append(arg);
            } else {
                stringParams.append(param).append("=").append(arg);
            }
        }

        String formattedParams = stringParams.toString();
        logger.info("C=" + calledClass + ", M=" + method + ", I=" + formattedParams);
    }

    @AfterReturning(value = "logPointCut()", returning = "result")
    public void logReturnValues(JoinPoint joinPoint, Object result) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        var method = signature.getName();
        var calledClass = joinPoint.getTarget().getClass().getSimpleName();
        var replaceUnsafeValues = "null";
        if(result != null) {
            replaceUnsafeValues = result.toString().replaceAll(pattern.toString(), REPLACEMENT);
        }
        logger.info("C="+ calledClass + ", M=" + method + ", O=" + replaceUnsafeValues);
    }
}
