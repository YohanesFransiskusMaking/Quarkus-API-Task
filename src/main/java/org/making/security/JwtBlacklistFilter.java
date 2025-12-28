package org.making.security;

import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.security.UnauthorizedException;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtBlacklistFilter implements ContainerRequestFilter {

    @Inject
    JsonWebToken jwt;

    @Inject
    TokenBlacklist tokenBlacklist;

    @Override
    public void filter(ContainerRequestContext requestContext){
        if (jwt !=  null){
            String jti = jwt.getClaim("jti");
            if (jti != null && tokenBlacklist.isBlacklist(jti)){
                throw new UnauthorizedException("Token sudah logout");
            }
        }
    }
}
