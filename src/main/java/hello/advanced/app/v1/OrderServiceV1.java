package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final HelloTraceV1 trace;
    private final OrderRepositoryV1 orderRepository;
//    @RequiredArgsConstructor 가 final 필드값을 사용하여 생성자를 만들어 준다.
//    public OrderServiceV0(OrderRepositoryV0 orderRepositoryV0) {
//        this.orderRepositoryV0 = orderRepositoryV0;
//    }

    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin(" OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            //로그는 기존 애플리케이션 동작에 영향을 주면 안됌 -> 로그에서 예외를 먹어버리기 때문에 다시 예외를 던져줘야됌
            throw e;
        }
    }
}
