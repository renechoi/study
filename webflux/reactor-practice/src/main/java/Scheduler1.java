
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Scheduler1 {


    // 스케줄러에서 작동하면서 하나의 쓰레드에서 진행
    public Flux<Integer> fluxMapWithSubscribeOn() {
        return Flux.range(1, 10)
                .map(i -> i * 2)
                .subscribeOn(Schedulers.boundedElastic())
                .log();
    }

    // 다양한 스케줄러를 결합해서 사용 가능
    public Flux<Integer> fluxMapWithPublishOn() {
        return Flux.range(1, 10)
                .map(i -> i + 1)
                .publishOn(Schedulers.boundedElastic())
                .log()
                .publishOn(Schedulers.parallel())
                .log()
                .map(i -> i * 2);
    }

}