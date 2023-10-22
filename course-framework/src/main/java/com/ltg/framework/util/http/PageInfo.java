package com.ltg.framework.util.http;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * <p> ClassName: PageInfo </p>
 * <p> Package: com.ltg.framework.util.http </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/19 - 17:32
 * @Version: v1.0
 */

@Data
public class PageInfo<T> {
    private Page<T> data;
    private String message;
    private Integer code;

    public PageInfo() {
    }

    public PageInfo(Page<T> data) {
        this.setMessage("success");
        this.setCode(0);
        this.data = data;
    }

}
