
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Operator4 {

    // limit -> 딜레이를 주고 2개라는 limit을 줌 -> publisher가 2개씩 전달함
    public Flux<Integer> fluxDelayAndLimit() {
        return Flux.range(1, 10)
                .delaySequence(Duration.ofSeconds(1))
                .log()
                .limitRate(2);
    }

    // sample -> limit과 동일한 기능
    public Flux<Integer> fluxSample() {
        return Flux.range(1,100)
                .delayElements(Duration.ofMillis(100))
                .sample(Duration.ofMillis(300))
                .log();
    }
}