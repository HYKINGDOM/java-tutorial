CREATE TABLE douyin_blogger_info (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     blogger_id VARCHAR(255),
                                     blogger_name VARCHAR(255),
                                     blogger_avatar VARCHAR(255),
                                     gender INT,
                                     age INT,
                                     province VARCHAR(255),
                                     city VARCHAR(255),
                                     birthday DATE,
                                     constellation VARCHAR(255),
                                     industry VARCHAR(255),
                                     label VARCHAR(255),
                                     follower_count INT,
                                     following_count INT,
                                     total_likes INT,
                                     total_comments INT,
                                     total_shares INT,
                                     total_videos INT,
                                     average_views INT,
                                     average_likes INT,
                                     average_comments INT,
                                     average_shares INT,
                                     video_gmv_30d DOUBLE,
                                     video_gmv_90d DOUBLE,
                                     video_gmv_180d DOUBLE,
                                     video_gmv_365d DOUBLE,
                                     total_gmv DOUBLE,
                                     last_video_upload_time DATE,
                                     last_active_time DATE,
                                     account_create_time DATE,
                                     verified INT,
                                     platform VARCHAR(255) DEFAULT '抖音',
                                     blogger_type VARCHAR(255),
                                     blogger_level INT,
                                     cooperation_status INT,
                                     channel_id VARCHAR(255),
                                     channel_name VARCHAR(255),
                                     channel_avatar VARCHAR(255),
                                     channel_follower_count INT,
                                     channel_total_videos INT,
                                     channel_total_likes INT,
                                     channel_total_comments INT,
                                     channel_total_shares INT,
                                     channel_video_gmv_30d DOUBLE,
                                     channel_video_gmv_90d DOUBLE,
                                     channel_video_gmv_180d DOUBLE,
                                     channel_video_gmv_365d DOUBLE,
                                     channel_total_gmv DOUBLE,
                                     channel_last_video_upload_time DATE,
                                     channel_last_active_time DATE,
                                     channel_account_create_time DATE,
                                     channel_verified INT,
                                     del_flag INT DEFAULT 0,
                                     create_by BIGINT,
                                     create_time DATETIME,
                                     update_by BIGINT,
                                     update_time DATETIME,
                                     remark TEXT
);














INSERT INTO douyin_blogger_info (
    blogger_id, blogger_name, blogger_avatar, gender, age, province, city, birthday, constellation,
    industry, label, follower_count, following_count, total_likes, total_comments, total_shares,
    total_videos, average_views, average_likes, average_comments, average_shares, video_gmv_30d,
    video_gmv_90d, video_gmv_180d, video_gmv_365d, total_gmv, last_video_upload_time, last_active_time,
    account_create_time, verified, platform, blogger_type, blogger_level, cooperation_status,
    channel_id, channel_name, channel_avatar, channel_follower_count, channel_total_videos,
    channel_total_likes, channel_total_comments, channel_total_shares, channel_video_gmv_30d,
    channel_video_gmv_90d, channel_video_gmv_180d, channel_video_gmv_365d, channel_total_gmv,
    channel_last_video_upload_time, channel_last_active_time, channel_account_create_time,
    channel_verified, del_flag, create_by, create_time, update_by, update_time, remark
) VALUES
      ('blogger1', 'Blogger One', 'avatar1.jpg', 1, 25, 'Beijing', 'Beijing', '1998-05-15', 'Taurus',
       'Technology', 'Tech, Coding', 1000, 500, 2000, 1500, 1200, 10, 5000, 400, 300, 200, 1000.50,
       2000.75, 3000.00, 4000.25, 10000.00, '2023-10-01', '2023-10-15', '2018-06-01', 1, 'Douyin',
       'Influencer', 5, 1, 'channel1', 'Channel One', 'channel_avatar1.jpg', 800, 20, 150, 100, 50,
       800.50, 1600.75, 2400.00, 3200.25, 8000.00, '2023-09-25', '2023-10-10', '2018-05-15', 1, 0, 1,
       '2023-10-01 10:00:00', 1, '2023-10-01 10:00:00', 'Remark for Blogger One'),

      ('blogger2', 'Blogger Two', 'avatar2.jpg', 2, 30, 'Shanghai', 'Shanghai', '1995-08-22', 'Leo',
       'Fashion', 'Fashion, Beauty', 1500, 700, 2500, 2000, 1800, 15, 6000, 500, 400, 300, 1500.50,
       3000.75, 4500.00, 5500.25, 15000.00, '2023-09-20', '2023-10-12', '2017-07-15', 1, 'Douyin',
       'Influencer', 6, 0, 'channel2', 'Channel Two', 'channel_avatar2.jpg', 1000, 25, 200, 150, 60,
       1000.50, 2000.75, 3000.00, 4000.25, 10000.00, '2023-09-18', '2023-10-08', '2017-06-15', 1, 0, 2,
       '2023-10-01 11:00:00', 2, '2023-10-01 11:00:00', 'Remark for Blogger Two'),

      ('blogger3', 'Blogger Three', 'avatar3.jpg', 1, 28, 'Guangzhou', 'Guangzhou', '1997-03-10', 'Pisces',
       'Entertainment', 'Entertainment, Music', 1200, 600, 2200, 1800, 1600, 12, 5500, 450, 350, 250, 1200.50,
       2400.75, 3600.00, 4800.25, 12000.00, '2023-09-15', '2023-10-10', '2016-06-10', 1, 'Douyin',
       'Influencer', 4, 1, 'channel3', 'Channel Three', 'channel_avatar3.jpg', 900, 22, 180, 140, 70,
       900.50, 1800.75, 2700.00, 3600.25, 9000.00, '2023-09-13', '2023-10-06', '2016-05-10', 1, 0, 3,
       '2023-10-01 12:00:00', 3, '2023-10-01 12:00:00', 'Remark for Blogger Three'),

      ('blogger4', 'Blogger Four', 'avatar4.jpg', 2, 26, 'Shenzhen', 'Shenzhen', '1999-07-18', 'Cancer',
       'Health', 'Health, Fitness', 1100, 550, 2100, 1700, 1500, 11, 5200, 420, 320, 220, 1100.50,
       2200.75, 3300.00, 4400.25, 11000.00, '2023-09-10', '2023-10-05', '2015-06-05', 1, 'Douyin',
       'Influencer', 3, 0, 'channel4', 'Channel Four', 'channel_avatar4.jpg', 850, 20, 170, 130, 65,
       850.50, 1700.75, 2550.00, 3450.25, 8500.00, '2023-09-08', '2023-10-03', '2015-05-05', 1, 0, 4,
       '2023-10-01 13:00:00', 4, '2023-10-01 13:00:00', 'Remark for Blogger Four'),

      ('blogger5', 'Blogger Five', 'avatar5.jpg', 1, 24, 'Chengdu', 'Chengdu', '1996-11-25', 'Scorpio',
       'Travel', 'Travel, Adventure', 1300, 650, 2300, 1900, 1700, 13, 5600, 460, 360, 260, 1300.50,
       2600.75, 3900.00, 5200.25, 13000.00, '2023-09-05', '2023-10-03', '2014-06-03', 1, 'Douyin',
       'Influencer', 2, 1, 'channel5', 'Channel Five', 'channel_avatar5.jpg', 950, 23, 190, 150, 80,
       950.50, 1900.75, 2850.00, 3750.25, 9500.00, '2023-09-03', '2023-10-01', '2014-05-03', 1, 0, 5,
       '2023-10-01 14:00:00', 5, '2023-10-01 14:00:00', 'Remark for Blogger Five'),

      ('blogger6', 'Blogger Six', 'avatar6.jpg', 2, 27, 'Hangzhou', 'Hangzhou', '1998-02-12', 'Aquarius',
       'Food', 'Food, Cooking', 1400, 700, 2400, 2000, 1800, 14, 5700, 470, 370, 270, 1400.50,
       2800.75, 4200.00, 5600.25, 14000.00, '2023-09-02', '2023-10-02', '2013-06-02', 1, 'Douyin',
       'Influencer', 1, 0, 'channel6', 'Channel Six', 'channel_avatar6.jpg', 900, 21, 180, 140, 70,
       900.50, 1800.75, 2700.00, 3600.25, 9000.00, '2023-09-01', '2023-09-30', '2013-05-02', 1, 0, 6,
       '2023-10-01 15:00:00', 6, '2023-10-01 15:00:00', 'Remark for Blogger Six'),

      ('blogger7', 'Blogger Seven', 'avatar7.jpg', 1, 29, 'Nanjing', 'Nanjing', '1997-06-28', 'Gemini',
       'Education', 'Education, Learning', 1600, 750, 2600, 2100, 1900, 15, 5800, 480, 380, 280, 1600.50,
       3000.75, 4500.00, 5900.25, 16000.00, '2023-08-31', '2023-09-30', '2012-06-01', 1, 'Douyin',
       'Influencer', 7, 1, 'channel7', 'Channel Seven', 'channel_avatar7.jpg', 1000, 24, 200, 160, 80,
       1000.50, 2000.75, 3000.00, 4000.25, 10000.00, '2023-08-29', '2023-09-28', '2012-05-01', 1, 0, 7,
       '2023-10-01 16:00:00', 7, '2023-10-01 16:00:00', 'Remark for Blogger Seven'),

      ('blogger8', 'Blogger Eight', 'avatar8.jpg', 2, 23, 'Wuhan', 'Wuhan', '1999-01-15', 'Capricorn',
       'Sports', 'Sports, Fitness', 1700, 800, 2700, 2200, 2000, 16, 5900, 490, 390, 290, 1700.50,
       3200.75, 4800.00, 6200.25, 17000.00, '2023-08-28', '2023-09-27', '2011-06-01', 1, 'Douyin',
       'Influencer', 8, 0, 'channel8', 'Channel Eight', 'channel_avatar8.jpg', 1050, 25, 210, 170, 85,
       1050.50, 2100.75, 3150.00, 4200.25, 10500.00, '2023-08-26', '2023-09-26', '2011-05-01', 1, 0, 8,
       '2023-10-01 17:00:00', 8, '2023-10-01 17:00:00', 'Remark for Blogger Eight'),

      ('blogger9', 'Blogger Nine', 'avatar9.jpg', 1, 31, 'Xi\'an', 'Xi\'an', '1994-10-20', 'Libra',
       'Lifestyle', 'Lifestyle, Travel', 1800, 850, 2800, 2300, 2100, 17, 6000, 500, 400, 300, 1800.50,
       3400.75, 5100.00, 6500.25, 18000.00, '2023-08-27', '2023-09-25', '2010-06-01', 1, 'Douyin',
       'Influencer', 9, 1, 'channel9', 'Channel Nine', 'channel_avatar9.jpg', 1100, 26, 220, 180, 90,
       1100.50, 2200.75, 3300.00, 4400.25, 11000.00, '2023-08-25', '2023-09-24', '2010-05-01', 1, 0, 9,
       '2023-10-01 18:00:00', 9, '2023-10-01 18:00:00', 'Remark for Blogger Nine')






CREATE TABLE `audit_log` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `table_name` varchar(100) NOT NULL COMMENT '表名',
                             `operator_id` varchar(50) NOT NULL COMMENT '操作人ID',
                             `operate_time` datetime NOT NULL COMMENT '操作时间',
                             `change_content` text NOT NULL COMMENT '变更内容(JSON)',
                             `change_fields` varchar(500) NOT NULL COMMENT '变更字段列表',
                             PRIMARY KEY (`id`),
                             KEY `idx_operate_time` (`operate_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据审计日志表';
























