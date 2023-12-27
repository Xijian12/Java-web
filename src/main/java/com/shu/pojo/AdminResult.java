package com.shu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 85131
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminResult<T> {
    private Integer code; // 业务状态码  0-成功  1-失败
    private String message; // 提示信息
    private T data; // 响应数据

    // 快速返回操作成功响应结果(带响应数据)
    public static <E> AdminResult<E> success(E data) {
        return new AdminResult<>(0, "操作成功", data);
    }

    // 快速返回操作成功响应结果
    public static <E> AdminResult<E> success() {
        return new AdminResult<>(0, "操作成功", null);
    }

    // 快速返回操作失败响应结果
    public static <E> AdminResult<E> error(String message) {
        return new AdminResult<>(1, message, null);
    }
}
