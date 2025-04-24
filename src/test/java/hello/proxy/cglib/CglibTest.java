package hello.proxy.cglib;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CglibTest {

	@Test
	void cglib() {
		ConcreteService target = new ConcreteService();

		Enhancer enhancer = new Enhancer(); // 얘가 cglib를 만드는 코드임.
		enhancer.setSuperclass(ConcreteService.class); //어떤 구체 클래스를 상속받을지 결정
		enhancer.setCallback(new TimeMethodInterceptor(target)); //프록시에 적용할 실행 로직을 할당
		ConcreteService proxy = (ConcreteService) enhancer.create(); //프록시를 생성

		log.info("targetClass={}", target.getClass());
		log.info("proxyClass={}", proxy.getClass());

		proxy.call();
	}
}
