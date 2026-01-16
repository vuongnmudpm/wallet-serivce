package com.jpay.wallet_service.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component // Quan trọng: Để Spring biết đây là một linh kiện và đem đi lắp ráp (Inject)
public class JwtProvider {

    // 1. Chìa khóa bí mật để ký tên lên thẻ (Phải giữ kín!)
    private final String JWT_SECRET = "lam-marketing-chuyen-sang-lam-dev-java-sieu-cap-pro";

    // 2. Thời gian thẻ có hiệu lực (ví dụ: 24 giờ)
    private final long JWT_EXPIRATION = 86400000L;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
    }

    // 3. Hàm tạo thẻ (Token) từ thông tin đăng nhập thành công
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(username) // Lưu tên người dùng vào thẻ
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Ký tên xác nhận
                .compact();
    }
    // 1. Hàm lấy Username từ Token (Giải mã thẻ để xem tên chủ thẻ)
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // 2. Hàm kiểm tra Token có hợp lệ không
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        // Thẻ hợp lệ khi: Tên trên thẻ khớp với Database VÀ thẻ chưa hết hạn
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // 3. Kiểm tra xem thẻ đã hết hạn chưa
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // 4. Hàm bổ trợ để "bóc tách" toàn bộ thông tin trong Payload (Claims)
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Dùng chìa khóa bí mật để mở khóa
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
