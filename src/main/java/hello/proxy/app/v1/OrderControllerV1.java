package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping //@RequestMapping이나 @ResponseBody가 있어야 스프링이 controller로 인식
@ResponseBody
public interface OrderControllerV1 {

	@GetMapping("/v1/request")
	String request(@RequestParam String itemId);

	@GetMapping("/v1/no-log")
	String noLog(@RequestParam String itemId);

}
