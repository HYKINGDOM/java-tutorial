package com.java.tutorial.project.consumer;

import com.java.tutorial.project.domain.LiveDataEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author meta
 */
@Service
public class DatabaseWriter {

    @Transactional
    public void saveToDatabase(String liveData) {
        // 解析 JSON 数据
        int liveId = Integer.parseInt(liveData.split("\"liveId\":")[1].split(",")[0].trim());
        int viewers = Integer.parseInt(liveData.split("\"viewers\":")[1].split(",")[0].trim());
        int likes = Integer.parseInt(liveData.split("\"likes\":")[1].split("}")[0].trim());

        // 创建实体对象
        LiveDataEntity entity = new LiveDataEntity();
        entity.setLiveId(liveId);
        entity.setViewers(viewers);
        entity.setLikes(likes);

        // 保存到数据库
        //entityManager.persist(entity);
        System.out.println("Data saved to database: " + liveData);
    }
}

