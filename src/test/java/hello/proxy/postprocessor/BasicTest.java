package hello.proxy.postprocessor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

public class BasicTest {

	@Test
	void basicConfig() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);

		//beanA 이름으로 B 객체가 등록된다.
		B b = applicationContext.getBean("beanA", B.class);
		b.helloB();

		//A는 빈으로 등록되지 않는다.
		Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(A.class));
	}

	@Slf4j
	@Configuration
	static class BeanPostProcessorConfig {
		@Bean(name = "beanA")
		public A a() {
			return new A();
		}

		@Bean
		public AtoBPostProcessor atoBPostProcessor() {
			return new AtoBPostProcessor();
		}
	}

	@Slf4j
	static class A {
		public void helloA() {
			log.info("helloA");
		}
	}

	@Slf4j
	static class B {
		public void helloB() {
			log.info("helloB");
		}
	}

	@Slf4j
	static class AtoBPostProcessor implements BeanPostProcessor {

		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
			log.info("beanName={}, bean{}", beanName, bean);

			if (bean instanceof A) {
				return new B();
			}

			return bean;
		}
	}

}
