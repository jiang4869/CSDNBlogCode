package cn.jxj4869.blog.aspect;


import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class LogAspect {
    private Logger logger = Logger.getLogger(this.getClass());

    @Pointcut("execution(* cn.jxj4869.blog.controller.*.*(..))")
    public void log() {
    }


    @Around("log()")
    public Object aroundPrintLog(ProceedingJoinPoint joinPoint) {
        Object resValue = null;
        Object[] args = joinPoint.getArgs();//得到方法执行所需的参数
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        RequestLog requestLog = new RequestLog(url, ip, classMethod);
        logger.info("Request :  " + requestLog.toString());
        try {


            resValue = joinPoint.proceed(args);
            if (resValue != null)
                logger.info("Result : " + resValue.toString());
            else
                logger.info("Result :  "+null);
            return resValue;
        } catch (Throwable t) {
            logger.error("Request Exception ", t);
            throw new RuntimeException(t);
        }
    }


    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;

        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    '}';
        }
    }


}
