package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AImpl implements AInterface {
	@Override
	public String callA() {
		log.info("callA");
		return "a";
	}
}
