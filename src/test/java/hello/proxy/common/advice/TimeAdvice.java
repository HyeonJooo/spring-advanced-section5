package hello.proxy.common.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		log.info("TimeProxy 실행");
		long startTime = System.currentTimeMillis();

		Object result = invocation.proceed(); //target 클래스의 정보가 이 안에 담겨져있다.

		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;

		log.info("TimeProxy 종료 resultTime={}ms", resultTime);
		return result;
	}
}
