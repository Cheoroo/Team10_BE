package bdbe.bdbd.controller.common;


import bdbe.bdbd._core.security.CustomUserDetails;
import bdbe.bdbd._core.utils.ApiUtils;
import bdbe.bdbd.dto.member.owner.OwnerResponse;
import bdbe.bdbd.service.member.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/common")
@RequiredArgsConstructor
public class CommonMemberController {

    private final UserService userService;

    @GetMapping("/member/info")
    public ResponseEntity<?> findUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        if (userDetails == null || userDetails.getMember() == null) {
            // 인증 정보가 없는 경우 적절한 상태 코드와 메시지를 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiUtils.error("Authentication is required to access this resource.", HttpStatus.UNAUTHORIZED));
        }
        OwnerResponse.UserInfoDTO dto = userService.findUserInfo(userDetails.getMember());
        return ResponseEntity.ok(ApiUtils.success(dto));
    }

    // 로그아웃 사용안함 - 프론트에서 JWT 토큰을 브라우저의 localstorage에서 삭제하면 됨.
}

