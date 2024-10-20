package com.java.tutorial.project.infrastucture.repository;

import com.java.tutorial.project.domain.User;
import com.java.tutorial.project.infrastucture.UserEntityRepository;
import com.java.tutorial.project.infrastucture.dao.UserEntityDao;
import com.java.tutorial.project.infrastucture.entity.UserEntity;
import com.java.tutorial.project.infrastucture.entity.UserNameEntity;
import com.java.tutorial.project.infrastucture.mapper.UserEntityMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author meta
 */
@RequiredArgsConstructor
@Repository
public class UserEntityRepositoryImpl implements UserEntityRepository {

    private final UserEntityDao userEntityDao;

    private final UserEntityMapper userEntityMapper;

    private final JPAQueryFactory jpaQueryFactory;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUser(User user) {
        UserEntity userEntity = userEntityMapper.toUserEntity(user);
        UserNameEntity userNameEntity1 = UserNameEntity.builder().nikeName("test1").shortName("qwer1").build();
        UserNameEntity userNameEntity2 = UserNameEntity.builder().nikeName("test2").shortName("qwer2").build();
        userEntity.setUserNameEntities(List.of(userNameEntity1, userNameEntity2));
        userEntity.setCreatedTimestamp(LocalDateTime.now());
        UserEntity saved = userEntityDao.save(userEntity);
    }

    @Override
    public UserEntity findUserByUserName(String userName) {
        UserNameEntity userNameEntity1 = UserNameEntity.builder().nikeName("test1").shortName("qwer1").build();
        UserEntity userEntity = new UserEntity();
        List<UserNameEntity> arrayList = new ArrayList<>();
        arrayList.add(userNameEntity1);
        userEntity.setUserNameEntities(arrayList);

        List<UserEntity> all = userEntityDao.findAll(Example.of(userEntity));

        return all.stream().findFirst().orElse(null);
    }

    @Override
    public long updateUser(UserEntity userEntity) {
        return 0;
    }

    @Override
    public void deleteUserById(Long id) {

    }
}
