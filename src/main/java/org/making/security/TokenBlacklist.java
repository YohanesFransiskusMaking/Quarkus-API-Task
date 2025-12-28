package org.making.security;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenBlacklist {

    private final Set<String> blacklistedJti = ConcurrentHashMap.newKeySet();
    public void blacklist(String jti){
        blacklistedJti.add(jti);
    }

    public boolean isBlacklist(String jti){
        return blacklistedJti.contains(jti);
    }

}
