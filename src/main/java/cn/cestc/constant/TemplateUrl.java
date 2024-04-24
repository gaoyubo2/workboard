package cn.cestc.constant;

import lombok.Getter;

@Getter
public enum TemplateUrl {
    CREATE_DOC("createDoc"),
    SAVE_DOC("saveDoc"),
    GET_CHAR("getChar"),
    DELETE_DOC("deleteDoc"),
    GET_DOC("getDoc"),
    DOC_URL("docUrl");

    private final String methodName;

    TemplateUrl(String methodName) {
        this.methodName = methodName;
    }

}