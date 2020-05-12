package cn.jxj4869.blog.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogVo {
    private String type="all";
    private String orderBy="default";
    private String word="";
    private Integer page=1;
    private Integer uid;


    
}


