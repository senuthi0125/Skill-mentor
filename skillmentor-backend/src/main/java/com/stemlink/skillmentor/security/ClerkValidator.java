package com.stemlink.skillmentor.security;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.net.URL;
import java.security.PublicKey;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
public class ClerkValidator implements TokenValidator {

    private final JwkProvider jwkProvider;

    public ClerkValidator(@Value("${clerk.jwks.url}") String clerkJwksUrl) {
        try {
            this.jwkProvider = new UrlJwkProvider(new URL(clerkJwksUrl));
        } catch (Exception e) {
            log.error("Failed to initialize JwkProvider with URL: {}", clerkJwksUrl, e);
            throw new RuntimeException("Failed to initialize Clerk validator", e);
        }
    }

    @Override
    public boolean validateToken(String token) {
        return verifyAndDecode(token) != null;
    }

    @Override
    public String extractUserId(String token) {
        DecodedJWT decodedJWT = verifyAndDecode(token);
        return decodedJWT != null ? decodedJWT.getSubject() : null;
    }

    @Override
    public List<String> extractRoles(String token) {
        DecodedJWT decodedJWT = verifyAndDecode(token);
        if (decodedJWT == null) {
            return Collections.emptyList();
        }


        List<String> roles = decodedJWT.getClaim("role").asList(String.class);
        if (roles != null && !roles.isEmpty()) {
            return roles;
        }


        String singleRole = decodedJWT.getClaim("role").asString();
        if (singleRole != null && !singleRole.isBlank()) {
            return List.of(singleRole);
        }


        Claim publicMetadataClaim = decodedJWT.getClaim("public_metadata");
        if (!publicMetadataClaim.isNull()) {
            Map<String, Object> publicMetadata = publicMetadataClaim.asMap();
            if (publicMetadata != null) {
                Object roleObj = publicMetadata.get("role");
                if (roleObj instanceof String role && !role.isBlank()) {
                    return List.of(role);
                }
            }
        }


        Claim unsafeMetadataClaim = decodedJWT.getClaim("unsafe_metadata");
        if (!unsafeMetadataClaim.isNull()) {
            Map<String, Object> unsafeMetadata = unsafeMetadataClaim.asMap();
            if (unsafeMetadata != null) {
                Object roleObj = unsafeMetadata.get("role");
                if (roleObj instanceof String role && !role.isBlank()) {
                    return List.of(role);
                }
            }
        }

        return Collections.emptyList();
    }

    @Override
    public String extractFirstName(String token) {
        DecodedJWT decodedJWT = verifyAndDecode(token);
        return decodedJWT != null ? decodedJWT.getClaim("first_name").asString() : null;
    }

    @Override
    public String extractLastName(String token) {
        DecodedJWT decodedJWT = verifyAndDecode(token);
        return decodedJWT != null ? decodedJWT.getClaim("last_name").asString() : null;
    }

    @Override
    public String extractEmail(String token) {
        DecodedJWT decodedJWT = verifyAndDecode(token);
        return decodedJWT != null ? decodedJWT.getClaim("email").asString() : null;
    }

    private DecodedJWT verifyAndDecode(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);

            String kid = decodedJWT.getKeyId();
            if (kid == null || kid.isBlank()) {
                log.error("Token does not contain a key ID (kid)");
                return null;
            }

            Jwk jwk = jwkProvider.get(kid);
            PublicKey publicKey = jwk.getPublicKey();

            Algorithm algorithm =
                    Algorithm.RSA256((java.security.interfaces.RSAPublicKey) publicKey, null);

            JWT.require(algorithm)
                    .acceptLeeway(60)
                    .build()
                    .verify(token);

            return decodedJWT;

        } catch (Exception e) {
            log.error("Token validation failed: {}", e.getMessage());
            return null;
        }
    }
}