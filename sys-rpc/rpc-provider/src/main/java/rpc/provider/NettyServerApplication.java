package rpc.provider;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import rpc.provider.server.NettyRpcServer;

/**
 * @author: 橘子
 * @date: 2022/7/12 10:35
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class NettyServerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        new NettyRpcServer("127.0.0.1", 9999).run();
    }
}
