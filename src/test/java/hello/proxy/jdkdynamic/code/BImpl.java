package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BImpl implements BInterface {
	@Override
	public String callB() {
		log.info("callB");
		return "b";
	}
}
