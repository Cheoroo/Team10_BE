package bdbe.bdbd._core.exception;


import bdbe.bdbd._core.utils.ApiUtils;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * HTTP 상태 코드 404 (Not Found)
 * 리소스 찾을 수 없을 때 발생합니다.
 */
@Getter
public class NotFoundError extends ApiException {

    public NotFoundError(ErrorCode errorCode, Object errors) {
        super(errorCode, errors, HttpStatus.NOT_FOUND);
    }

    public enum ErrorCode implements ApiException.ErrorCode {
        RESOURCE_NOT_FOUND(1301, "Nonexistent Resource Access");

        private final int code;
        private final String message;

        ErrorCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}