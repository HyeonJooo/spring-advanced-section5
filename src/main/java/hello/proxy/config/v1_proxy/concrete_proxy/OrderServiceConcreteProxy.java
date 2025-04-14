package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV2 {
	private final OrderServiceV2 target;
	private final LogTrace logTrace;

	public OrderServiceConcreteProxy(OrderServiceV2 target, LogTrace logTrace) {
		//자바 기본 문법에 의해 자식 클래스를 생성할 때는 항상 super()로 부모 클래스(OrderServiceV2)의 생성자를 호출해야 한다. 이 부분을 생략하면 기본 생성자(아무 것도 없는 거)가 호출된다.
		//OrderServiceV2는 기본 생성자가 없고, 생성자에서 파라미터 1개를 필수로 받고 있다.
		//근데 이 클래스에서는 프록시 역할만 하고, 부모 객체의 기능은 사용하지 않기 때문에 null을 넣어준다.
		super(null);
		this.target = target;
		this.logTrace = logTrace;
	}

	@Override
	public void orderItem(String itemId) {
		TraceStatus status = null;

		try {
			status = logTrace.begin("OrderService.request()");

			//target 호출
			target.orderItem(itemId);
			logTrace.end(status);
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
