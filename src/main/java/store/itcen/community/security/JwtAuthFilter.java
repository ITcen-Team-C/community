package store.itcen.community.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 클라이언트 헤더에 담아서 보낸 토큰을 검사하는 필터
// username + password를 통한 인증을 Jwt를 통해 수행
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final TokenProvider provider;

    // 필터의 할 일
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        try {
            // 요청 헤더에서 토큰 가져오기
            Cookie tokenCookie = WebUtils.getCookie(request, "token");
            String token = tokenCookie.getValue();

//            String token = parseBearerToken(request);

            // 채팅 알림 log 멈추기용 임시
            boolean flag = request.getRequestURI().startsWith("/chatUnreadAlert");
            if (flag) return;

            // 토큰 위조 여부 검사
            if (token != null) {
                log.info("Jwt Token Filter is running.... - token: {}", token);
                String userId = provider.validateAndGetUserId(token);
                log.info("인증된 userId : {}", userId);

                // 인증 완료!! api서버에서는 SecurityContextHolder에 등록해야 인증된 사용자라고 생각한다.
                AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userId, // 컨트롤러의 @AuthenticationPrincipal 값
                        null,
                        AuthorityUtils.NO_AUTHORITIES
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authentication);

                SecurityContextHolder.setContext(securityContext);

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("인증되지 않은 사용자입니다.");
        }

        // 필터 체인에 내가 만든 커스텀필터 실행시킴.
        filterChain.doFilter(request, response);
    }

    private String parseBearerToken(HttpServletRequest request) {
        // 요청 헤더에서 토큰을 읽어온다.
        // 순수한 토큰값이 아니라 앞에 Bearer 가 붙어있으므로 제거해야 된다.
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken)
                && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}