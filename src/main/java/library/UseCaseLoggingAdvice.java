package library;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Component
@Aspect
@Order(1)
public class UseCaseLoggingAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(library.UseCaseLoggingAdvice.class);

    @Pointcut("within(@library.UseCase *)")
    public void useCase() {
    }

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
    }

    @Pointcut("publicMethod() && useCase()")
    public void publicMethodInsideAUseCase() {
    }

    @Around("publicMethodInsideAUseCase()")
    public Object aroundServiceMethodAdvice(final ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        try {
            LOGGER.info("Executing use case: {}#{} with parameters: {}", pjp.getTarget().getClass(), pjp.getSignature().getName(), Arrays.toString(pjp.getArgs()));
            stopWatch.start();
            return pjp.proceed();
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished executing use case {}#{} in {}ms", pjp.getTarget().getClass(), pjp.getSignature().getName(), stopWatch.getTotalTimeMillis());
        }
    }
}
