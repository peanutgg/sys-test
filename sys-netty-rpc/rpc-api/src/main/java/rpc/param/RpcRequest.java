package rpc.param;

import lombok.Data;

/**
 * @author: 橘子
 * @date: 2022/7/12 10:15
 */
@Data
public class RpcRequest {
    private String requestId;
    private String className;
    private String methodName;
    private Class<?>[] paramTypes;
    private Object[] paramValues;
}
