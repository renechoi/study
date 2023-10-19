
/**
 * @author : Rene
 * @since : 2023/10/17
 */

public class Main {
	public static void main(String[] args) {
		var publisher = new Publisher();

		// publisher.startFlux()
		// 	.subscribe(System.out::println);

		publisher.startMono()
			.subscribe();
	}
}