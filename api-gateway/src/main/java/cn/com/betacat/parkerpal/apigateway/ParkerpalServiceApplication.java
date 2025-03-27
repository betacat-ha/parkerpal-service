package cn.com.betacat.parkerpal.apigateway;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@EnableAspectJAutoProxy
@EnableScheduling
@SpringBootApplication
@MapperScan("cn.com.betacat.parkerpal.apicontracts.mapper")
@ComponentScan(basePackages = {"cn.com.betacat.parkerpal"})
public class ParkerpalServiceApplication {

    public static void main(String[] args) throws UnknownHostException {
        log.info("\n=============> 开始启动智泊无忧服务 <===================");
        ConfigurableApplicationContext context = SpringApplication.run(ParkerpalServiceApplication.class, args);
        Environment env = context.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        log.info("\n----------------------------------------------------------------------\n\t" +
                "==============> 智泊无忧服务启动成功 <=================\n\t" +
                "Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + "/\n\t" +
                "Swagger文档: \thttp://" + ip + ":" + port + "/doc.html\n" +
                "----------------------------------------------------------------------");
    }
}

