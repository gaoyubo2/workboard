package cn.cestc.constant;


import lombok.Getter;

@Getter
public enum SsoUrl {
    GET_USER_WITH_ROLE("userWithRole");

    private final String methodName;

    SsoUrl(String methodName) {
        this.methodName = methodName;
    }

}