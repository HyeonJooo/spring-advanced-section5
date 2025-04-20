package hello.proxy.jdkdynamic.code;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeInvocationHandler implements InvocationHandler {

	private final Object target; //동적 프록시가 호출할 대상

	public TimeInvocationHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		log.info("TimeProxy 실행");
		long startTime = System.currentTimeMillis();

		Object result = method.invoke(target, args); //이 메서드 호출하는 부분이 동적임. 리플렉션을 사용해 target 인스턴스의 메서드를 호출함.

		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;

		log.info("TimeProxy 종료 resultTime={}ms", resultTime);
		return result;
	}
}
