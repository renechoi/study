package org.example.optional;

import java.util.Optional;

public class OptionalEx {
	public static void main(String[] args) {
		String someEmail = "some@email.com";
		String nullEmail = null;

		Optional<String> maybeEmail = Optional.of(someEmail);
		Optional<String> maybeEamil2 = Optional.empty();
		Optional<String> maybeEmail3 = Optional.ofNullable(someEmail);
		Optional<String> maybeEmail4 = Optional.ofNullable(nullEmail);

		String email = maybeEmail.get();

		String defaultEmail = "defaul@email.com";

		maybeEmail3.orElse(defaultEmail);
		maybeEmail3.orElseGet(()->defaultEmail);


	}
}
