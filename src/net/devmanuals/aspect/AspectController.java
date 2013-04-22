package net.devmanuals.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectController {

	@Before("execution(* net.devmanuals.service.*.*(..))")
	public void takeSeats() {
		System.out.println("The audience is taking their seats.");
	}

}
