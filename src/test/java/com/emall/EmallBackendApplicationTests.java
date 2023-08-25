package com.emall;

import com.emall.entity.Admin;
import com.emall.mapper.AdminMapper;
import com.emall.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class EmallBackendApplicationTests {

    //DI注入数据源
    @Autowired
    DataSource dataSource;

    @Autowired
    private ProductService productService;

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void contextLoads() throws SQLException {

//        List<Product> products = productService.listProduct();
//        System.out.println(products);

        for (Admin admin : adminMapper.getAllAdmin()) {
            System.out.println(admin);
        }
    }

}
