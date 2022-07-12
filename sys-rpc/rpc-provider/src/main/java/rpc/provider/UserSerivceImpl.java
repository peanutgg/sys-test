package rpc.provider;

import org.springframework.stereotype.Service;
import rpc.api.IUserService;
import rpc.pojo.User;
import rpc.provider.anno.RpcService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 橘子
 * @date: 2022/7/12 11:33
 */
@RpcService
@Service
public class UserSerivceImpl implements IUserService {
    Map<Object, User> userMap = new HashMap();

    @Override
    public User getById(int id) {
        if (userMap.size() == 0) {
            User user1 = new User();
            user1.setId(1);
            user1.setName("张三");
            User user2 = new User();
            user2.setId(2);
            user2.setName("李四");
            userMap.put(user1.getId(), user1);
            userMap.put(user2.getId(), user2);
        }
        return userMap.get(id);
    }
}
