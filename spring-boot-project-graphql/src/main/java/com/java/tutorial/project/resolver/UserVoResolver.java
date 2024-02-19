package com.java.tutorial.project.resolver;

import com.google.common.collect.Lists;
import com.java.tutorial.project.domain.PostVo;
import com.java.tutorial.project.domain.UserVo;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class UserVoResolver implements GraphQLQueryResolver {
    List<UserVo> userList = Lists.newArrayList();

    public UserVo getUserById(int id) {
        return userList.stream().filter(item -> item.getUserId() == id).findAny().orElse(null);
    }

    public PostVo getPostById(int id) {
        if (id == 1) {
            UserVo user =
                UserVo.builder().userId(1).userName("javadaily").userName("JAVA日知录").email("zhangsan@qq.com")
                    .build();
            PostVo post =
                PostVo.builder().postId(1).title("Hello,Graphql1").category("Graphql初体验1").text("日记").build();
            return post;
        } else {
            return null;
        }

    }

    @PostConstruct
    public void initUsers() {
        PostVo post1 =
            PostVo.builder().postId(1).title("Hello,Graphql1").category("Graphql初体验1").text("日记").build();
        PostVo post2 =
            PostVo.builder().postId(2).title("Hello,Graphql2").category("Graphql初体验2").text("日记").build();
        PostVo post3 =
            PostVo.builder().postId(3).title("Hello,Graphql3").category("Graphql初体验3").text("日记").build();
        List<PostVo> posts = Lists.newArrayList(post1, post2, post3);

        UserVo user1 =
            UserVo.builder().userId(1).userName("zhangsan").userName("张三").email("zhangsan@qq.com").build();
        UserVo user2 = UserVo.builder().userId(2).userName("lisi").userName("李四").email("lisi@qq.com").build();

        user1.setPostVos(posts);
        user2.setPostVos(posts);

        //        userList.add(user1);
        //        userList.add(user2);

    }
}
