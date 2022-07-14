package rpc;

import rpc.api.IUserService;
import rpc.pojo.User;
import rpc.proxy.RpcProxy;

/**
 * @author: 橘子
 * @date: 2022/7/14 10:03
 */
public class Consumer {
    public static void main(String[] args) {
        IUserService userProxy = (IUserService) RpcProxy.creatProxy(IUserService.class);
        User user = userProxy.getById(1);
        System.out.println(user.getName());
    }
}
