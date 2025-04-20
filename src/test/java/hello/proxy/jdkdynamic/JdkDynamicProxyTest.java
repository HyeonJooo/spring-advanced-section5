package hello.proxy.jdkdynamic;

import java.lang.reflect.Proxy;

import org.junit.jupiter.api.Test;

import hello.proxy.jdkdynamic.code.AImpl;
import hello.proxy.jdkdynamic.code.AInterface;
import hello.proxy.jdkdynamic.code.BImpl;
import hello.proxy.jdkdynamic.code.BInterface;
import hello.proxy.jdkdynamic.code.TimeInvocationHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JdkDynamicProxyTest {
	@Test
	void dynamicA() {
		AInterface target = new AImpl();
		TimeInvocationHandler handler = new TimeInvocationHandler(target);

		//프록시는 이렇게 생성하는거임. 규칙임. (클래스 로더 정보, 인터페이스, 핸들러 로직)
		AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[] {AInterface.class}, handler);
		proxy.call();

		log.info("targetClass={}", target.getClass());
		log.info("proxyClass={}", proxy.getClass());
	}

	@Test
	void dynamicB() {
		BInterface target = new BImpl();
		TimeInvocationHandler handler = new TimeInvocationHandler(target);

		BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[] {BInterface.class}, handler);
		proxy.call();

		log.info("targetClass={}", target.getClass());
		log.info("proxyClass={}", proxy.getClass());
	}

}
