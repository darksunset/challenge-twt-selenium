package api.restcountries.constants.enums;

public enum StatusCode {
    CODE_200(200, ""),
    CODE_201(201, ""),
    CODE_400(400, "Missing required field"),
    CODE_401(401, "Invalid access"),
    CODE_404(404, "Not found");

    public final int code;
    public final String msg;

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
