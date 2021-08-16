package org.openreading.readingisgood.security;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Muhammet Sakarya
 * created at 8/14/2021
 */
@Scope("singleton")
public interface TokenService {

    String generateToken(User user);

    UserPrincipal parseToken(String token);
}
