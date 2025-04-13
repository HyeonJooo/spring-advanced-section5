package hello.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;

//@Configuration도 내부에 @Component가 있기 때문에 자동으로 컴포넌트 스캔 대상이 된다.
//하지만 나중에 Appv2Config처럼 config를 바꿔서 등록할 것이기 때문에, scanBasePackages를 app으로만 되게 변경한 뒤
//Import를 이용해 원하는 Config를 사용하기 위해 아래와 같이 어노테이션을 붙임.

//@Import(AppV1Config.class)
@Import({AppV1Config.class, AppV2Config.class})
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

}
