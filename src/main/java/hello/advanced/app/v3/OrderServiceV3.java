package hello.advanced.app.v3;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final LogTrace trace;
    private final OrderRepositoryV3 orderRepository;
//    @RequiredArgsConstructor 가 final 필드값을 사용하여 생성자를 만들어 준다.
//    public OrderServiceV0(OrderRepositoryV0 orderRepositoryV0) {
//        this.orderRepositoryV0 = orderRepositoryV0;
//    }

    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin(" OrderService.orderItem()");
            orderRepository.save(status.getTraceId(), itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            //로그는 기존 애플리케이션 동작에 영향을 주면 안됌 -> 로그에서 예외를 먹어버리기 때문에 다시 예외를 던져줘야됌
            throw e;
        }
    }
}
