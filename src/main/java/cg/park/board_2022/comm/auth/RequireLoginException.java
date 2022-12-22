package cg.park.board_2022.comm.auth;

public class RequireLoginException extends RuntimeException {
    private static final long serialVersionUID = -3355176539836895374L;
    private boolean isRequireLogin = false;
    public RequireLoginException() {
        super();
    }

    public RequireLoginException(String message) {
        super(message);
    }
    public RequireLoginException(String message, boolean isRequireLogin) {
        super(message);
        this.isRequireLogin = isRequireLogin;
    }

    public RequireLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequireLoginException(Throwable cause) {
        super(cause);
    }

    protected RequireLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public boolean isRequireLogin() {
        return isRequireLogin;
    }
}
