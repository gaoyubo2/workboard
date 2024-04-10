package cn.cestc.Utils;


import lombok.Data;

import java.io.Serializable;

/**
 * 后端统一返回结果
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    private Integer code;       //编码：1成功，0和其它数字为失败
    private String msg;         //错误信息
    private T data;             //数据

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 1;
        return result;
    }

    public static <T> Result<T> success(String msg, T object) {
        Result<T> result = new Result<T>();
        result.code = 1;
        result.msg = msg;
        result.data = object;
        return result;
    }
    public static <T> Result<T> success(String msg) {
        Result<T> result = new Result<T>();
        result.code = 1;
        result.msg = msg;
        return result;
    }

    public static <T> Result<T> success( T object) {
        Result<T> result = new Result<T>();
        result.data = object;       //result的数据为传入的对象
        result.code = 1;
        return result;
    }
    /*
        <T>：这是一个泛型声明，表示该方法可以接受任意类型的参数

     */

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = 500;
        return result;
    }
    public static <T> Result<T> error(String msg,Integer code) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = code;
        return result;
    }

}
