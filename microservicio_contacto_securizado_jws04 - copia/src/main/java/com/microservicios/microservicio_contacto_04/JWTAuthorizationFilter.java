package com.microservicios.microservicio_contacto_04;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.microservicios.microservicio_contacto_04.utils.Constantes.CLAVE;
import static com.microservicios.microservicio_contacto_04.utils.Constantes.ENCABEZADO;
import static com.microservicios.microservicio_contacto_04.utils.Constantes.PREFIJO_TOKEN;

@Configuration
public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        String header = request.getHeader(ENCABEZADO);
        if (header == null || !header.startsWith(PREFIJO_TOKEN)) {
            chain.doFilter(request, response);
            return;
        }

        // Obtenemos los datos del usuario a partir del token
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);

        // La informacion del usuario se almacena en el contexto de seguridad
        // para que pueda ser utilizada por Spring security durante el
        // proceso de autoriuzacion
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // El token viene en la cabecera de la peticion
        String token = request.getHeader(ENCABEZADO);
        if (token != null) {
            //  Se procesa el token y se recupera el usuario y los roles
            Claims claims = Jwts.parserBuilder()
                        .setSigningKey(CLAVE.getBytes())
                        .build()
                        .parseClaimsJws(token.replace(PREFIJO_TOKEN, ""))
                        .getBody();
            
            String user = claims.getSubject();
            List<String> authorities = (List<String>) claims.get("authorities");
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, authorities.stream()
                                                                            .map(SimpleGrantedAuthority::new)
                                                                            .collect(Collectors.toList()));
            }
            return null;
        }
        return null;
    }
}
