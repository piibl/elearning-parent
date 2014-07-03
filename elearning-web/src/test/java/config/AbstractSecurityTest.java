package config;

import javax.inject.Inject;

import org.junit.After;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;

/**
 * Fournit des utilitaires pour les classes nécessitant un support spring security
 */
@ContextConfiguration(locations = { WebTest.SPRING_SECURITY_CONFIG })
public class AbstractSecurityTest {

	@Inject
	private AuthenticationManager authenticationManager;

	@After
	public void clear() {
		SecurityContextHolder.clearContext();
	}

	protected void login(String name, String password) {
		Authentication auth = new UsernamePasswordAuthenticationToken(name, password);
		SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(auth));
	}

}
