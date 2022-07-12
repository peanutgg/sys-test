package rpc.param;

import lombok.Data;

/**
 * @author: 橘子
 * @date: 2022/7/12 10:15
 */
@Data
public class RpcResponse {
    private String requestId;
    private String error;
    private Object result;
}
