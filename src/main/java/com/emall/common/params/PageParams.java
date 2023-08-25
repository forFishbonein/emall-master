/*
 * @Time : 2022/8/8 16:21
 * @Author : hao
 * @File : PageParams.java
 * @Software : IntelliJ IDEA
 */
package com.emall.common.params;

import lombok.Data;

@Data
public class PageParams {
    //默认值
    private int page = 1;

    private int pageSize = 5;

}
