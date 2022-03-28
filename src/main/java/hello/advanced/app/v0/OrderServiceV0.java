package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV0 {

    private final OrderRepositoryV0 orderRepository;
//    @RequiredArgsConstructor 가 final 필드값을 사용하여 생성자를 만들어 준다.
//    public OrderServiceV0(OrderRepositoryV0 orderRepositoryV0) {
//        this.orderRepositoryV0 = orderRepositoryV0;
//    }

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
