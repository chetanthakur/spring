package net.devmanuals.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectController {
	
	private final static Logger LOGGER = Logger.getLogger(AspectController.class); 

	@Before("execution(* net.devmanuals.service.*.*(..))")
	public void takeSeats() {
		LOGGER.info("Aspect Pogramming implemented");
	}

}
