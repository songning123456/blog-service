package com.simple.blog.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @Author songning
 * @create 2019/8/9 8:59
 */
@Data
@Document(indexName = "simple_blog", type = "es_blog")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class EsBlog {
}
