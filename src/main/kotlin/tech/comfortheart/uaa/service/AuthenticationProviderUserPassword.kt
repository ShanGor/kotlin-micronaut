package tech.comfortheart.uaa.tech.comfortheart.uaa.service

import io.micronaut.security.authentication.*
import io.micronaut.security.token.jwt.endpoints.TokenRefreshRequest
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import javax.inject.Singleton

@Singleton
class AuthenticationProviderUserPassword: AuthenticationProvider {
    override fun authenticate(authenticationRequest: AuthenticationRequest<*, *>?): Publisher<AuthenticationResponse> {
        if (authenticationRequest != null && authenticationRequest.identity != null && authenticationRequest.secret != null) {
            if (authenticationRequest.identity == "hello" && authenticationRequest.secret == "world") {
                return Flowable.just<AuthenticationResponse>(UserDetails(authenticationRequest.identity as String, ArrayList()))
            }
            TokenRefreshRequest()
        }
        return Flowable.just<AuthenticationResponse>(AuthenticationFailed())
    }
}