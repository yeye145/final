DROP
DATABASE IF EXISTS `forum`;

CREATE SCHEMA `forum` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE
forum;

-- 用户表
CREATE TABLE user
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    email           VARCHAR(255) UNIQUE COMMENT '邮箱',
    phone           VARCHAR(20) UNIQUE COMMENT '手机号',
    password        VARCHAR(255) NOT NULL COMMENT '密码',
    is_admin        BOOLEAN DEFAULT FALSE COMMENT '是否为管理员',
    name            VARCHAR(50)  NOT NULL COMMENT '昵称',
    grade           INT     DEFAULT 1 COMMENT '等级',
    avatar          VARCHAR(255) COMMENT '头像路径',
    if_receive_like BOOLEAN DEFAULT TRUE COMMENT '接收点赞通知',
    INDEX           idx_email (email),
    INDEX           idx_phone (phone)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 板块表
CREATE TABLE board
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    host_id    INT          NOT NULL COMMENT '版主ID',
    time       DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    title      VARCHAR(255) NOT NULL COMMENT '标题',
    type       VARCHAR(255) NOT NULL COMMENT '类型',
    post_count INT      DEFAULT 0 COMMENT '帖子数量',
    FOREIGN KEY (host_id) REFERENCES user (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 申请板块表
CREATE TABLE board_apply
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    host_id INT          NOT NULL COMMENT '版主ID',
    time    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
    title   VARCHAR(255) NOT NULL COMMENT '标题',
    type    VARCHAR(255) NOT NULL COMMENT '类型',
    FOREIGN KEY (host_id) REFERENCES user (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 帖子表
CREATE TABLE post
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    title         VARCHAR(255) NOT NULL COMMENT '标题',
    content       TEXT         NOT NULL COMMENT '内容',
    author_id     INT          NOT NULL COMMENT '作者ID',
    board_id      INT          NOT NULL COMMENT '版块ID',
    view_count    INT      DEFAULT 0 COMMENT '浏览次数',
    like_count    INT      DEFAULT 0 COMMENT '点赞数',
    comment_count INT      DEFAULT 0 COMMENT '评论数量',
    time          DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (author_id) REFERENCES user (id) ON DELETE CASCADE,
    FOREIGN KEY (board_id) REFERENCES board (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 举报表
CREATE TABLE report
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    user_id     INT     NOT NULL COMMENT '举报者ID',
    board_id    INT     NOT NULL COMMENT '版块ID',
    post_id     INT     NOT NULL COMMENT '帖子ID',
    reported_id INT     NOT NULL COMMENT '被举报者ID',
    judge       BOOLEAN NOT NULL COMMENT '举报受理对象',
    # 1 -> 管理员，0 -> 版主
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (board_id) REFERENCES board (id) ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 历史记录表
CREATE TABLE history
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    user_id  INT NOT NULL COMMENT '用户ID',
    board_id INT NOT NULL COMMENT '版块ID',
    post_id  INT NOT NULL COMMENT '帖子ID',
    time     DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '浏览时间',
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE,
    FOREIGN KEY (board_id) REFERENCES board (id) ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 收藏表
CREATE TABLE collect
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    user_id  INT NOT NULL COMMENT '用户ID',
    board_id INT NOT NULL COMMENT '版块ID',
    post_id  INT NOT NULL COMMENT '帖子ID',
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE,
    FOREIGN KEY (board_id) REFERENCES board (id) ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 版块公告表
CREATE TABLE notice
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    board_id INT  NOT NULL COMMENT '版块ID',
    time     DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
    content  TEXT NOT NULL COMMENT '公告内容',
    FOREIGN KEY (board_id) REFERENCES board (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 评论表
CREATE TABLE comment
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    post_id    INT  NOT NULL COMMENT '帖子ID',
    time       DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
    content    TEXT NOT NULL COMMENT '评论内容',
    user_id    INT  NOT NULL COMMENT '评论者ID',
    parent_id  INT COMMENT '父评论ID',
    like_count INT      DEFAULT 0 COMMENT '点赞数',
    FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE,
    FOREIGN KEY (parent_id) REFERENCES comment (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 版块封禁表
CREATE TABLE board_ban
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    ban_id   INT NOT NULL COMMENT '被禁用户ID',
    board_id INT NOT NULL COMMENT '版块ID',
    FOREIGN KEY (ban_id) REFERENCES user (id) ON DELETE CASCADE,
    FOREIGN KEY (board_id) REFERENCES board (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 通知表
CREATE TABLE message
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    content TEXT NOT NULL COMMENT '通知内容',
    user_id INT  NOT NULL COMMENT '接收用户ID',
    time    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 日志表
CREATE TABLE log
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    user_id   INT          NOT NULL COMMENT '用户ID',
    user_name VARCHAR(50)  NOT NULL COMMENT '用户名',
    action    VARCHAR(255) NOT NULL COMMENT '操作内容',
    ip        VARCHAR(45)  NOT NULL COMMENT 'IP地址',
    time      DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 关注表
CREATE TABLE subscription
(
    id                    INT PRIMARY KEY AUTO_INCREMENT,
    user_id               INT NOT NULL,
    subscribe_to_user_id  INT,
    subscribe_to_board_id INT,
    FOREIGN KEY (subscribe_to_user_id) REFERENCES user (id) ON DELETE CASCADE,
    FOREIGN KEY (subscribe_to_board_id) REFERENCES board (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `forum`.`user`
    ADD COLUMN `receive_like_count` INT NULL DEFAULT '0' AFTER `if_receive_like`,
ADD COLUMN `fans_count` INT NULL DEFAULT '0' AFTER `receive_like_count`,
ADD COLUMN `post_count` INT NULL DEFAULT '0' AFTER `fans_count`,
ADD COLUMN `receive_read_count` INT NULL DEFAULT '0' AFTER `post_count`,
ADD COLUMN `my_subscribe_count` INT NULL DEFAULT '0' AFTER `receive_read_count`,
ADD COLUMN `my_collect_count` INT NULL DEFAULT '0' AFTER `my_subscribe_count`,
ADD COLUMN `my_board_count` INT NULL DEFAULT '0' AFTER `my_collect_count`;

ALTER TABLE `forum`.`board`
    ADD COLUMN `host_name` VARCHAR(255) NULL AFTER `post_count`;


-- 关注与粉丝 -- 触发器
DELIMITER //

CREATE TRIGGER after_insert_subscription
    AFTER INSERT ON subscription
    FOR EACH ROW
BEGIN
    -- 更新关注者的 my_subscribe_count 字段
    UPDATE user
    SET my_subscribe_count = my_subscribe_count + 1
    WHERE id = NEW.user_id;

    -- 如果被关注的是用户，则更新被关注者的 fans_count 字段
    IF NEW.subscribe_to_user_id IS NOT NULL THEN
    UPDATE user
    SET fans_count = fans_count + 1
    WHERE id = NEW.subscribe_to_user_id;
END IF;
END //

DELIMITER ;

-- 发帖 -- 触发器
DELIMITER //

CREATE TRIGGER after_insert_post
    AFTER INSERT ON post
    FOR EACH ROW
BEGIN
    UPDATE user
    SET post_count = post_count + 1
    WHERE id = NEW.author_id;
END //

DELIMITER ;

-- 收藏 -- 触发器
DELIMITER //

CREATE TRIGGER after_insert_collect
    AFTER INSERT ON collect
    FOR EACH ROW
BEGIN
    UPDATE user
    SET my_collect_count = my_collect_count + 1
    WHERE id = NEW.user_id;
END //

DELIMITER ;

-- 版块 -- 触发器
DELIMITER //

CREATE TRIGGER after_insert_board
    AFTER INSERT ON board
    FOR EACH ROW
BEGIN
    UPDATE user
    SET my_board_count = my_board_count + 1
    WHERE id = NEW.host_id;
END //

DELIMITER ;

-- 插入帖子后更新版块帖子数 -- 触发器
DELIMITER //

CREATE TRIGGER after_insert_post_update_board
    AFTER INSERT ON post
    FOR EACH ROW
BEGIN
    UPDATE board
    SET post_count = post_count + 1
    WHERE id = NEW.board_id;
END //

DELIMITER ;

INSERT INTO `forum`.`user` (`id`, `email`, `phone`, `password`, `is_admin`, `name`, `grade`, `avatar`,
                            `if_receive_like`)
VALUES ('1', 'y@y.com', '18300000985', 'yyy111', '1', 'Yiiie.', '7', 'initAvatar.jpg', '0');

-- 生成新用户
INSERT INTO `forum`.`user` (`email`, `phone`, `password`, `name`, `grade`, `avatar`)
VALUES ('3@3.com', '18300003030', 'sss333', '张三', '3', 'initAvatar.jpg'),
       ('4@4.com', '18400001234', 'lll444', '李四', '4', 'initAvatar.jpg'),
       ('user4@test.com', '18511110001', 'password123', '王五', 2, 'initAvatar.jpg'),
       ('user5@test.com', '18511110002', 'password123', '赵六', 3, 'initAvatar.jpg'),
       ('user6@test.com', '18511110003', 'password123', '陈七', 1, 'initAvatar.jpg'),
       ('user7@test.com', '18511110004', 'password123', '林八', 4, 'initAvatar.jpg'),
       ('user8@test.com', '18511110005', 'password123', '周九', 2, 'initAvatar.jpg'),
       ('user9@test.com', '18511110006', 'password123', '吴十', 3, 'initAvatar.jpg'),
       ('user10@test.com', '18511110007', 'password123', '郑十一', 1, 'initAvatar.jpg'),
       ('user11@test.com', '18511110008', 'password123', '孙十二', 5, 'initAvatar.jpg'),
       ('user12@test.com', '18511110009', 'password123', '朱十三', 2, 'initAvatar.jpg'),
       ('user13@test.com', '18511110010', 'password123', '秦十四', 3, 'initAvatar.jpg'),
       ('user14@test.com', '18511110011', 'password123', '许十五', 1, 'initAvatar.jpg'),
       ('user15@test.com', '18511110012', 'password123', '何十六', 4, 'initAvatar.jpg'),
       ('user16@test.com', '18511110013', 'password123', '吕十七', 2, 'initAvatar.jpg'),
       ('user17@test.com', '18511110014', 'password123', '张十八', 3, 'initAvatar.jpg'),
       ('user18@test.com', '18511110015', 'password123', '高十九', 1, 'initAvatar.jpg'),
       ('user19@test.com', '18511110016', 'password123', '程二十', 5, 'initAvatar.jpg'),
       ('user20@test.com', '18511110017', 'password123', '黄二一', 2, 'initAvatar.jpg');

INSERT INTO `forum`.`board` (`id`, `host_id`, `title`, `type`, `post_count`, `time`, `host_name`)
VALUES ('1', '2', '张三学java', 'java', '0', '2025-04-14 15:14:50', '张三');
INSERT INTO `forum`.`log` (`id`, `user_id`, `user_name`, `action`, `ip`, `time`)
VALUES ('1', '2', '张三', '新建版块', '127.0.0.1', '2025-04-14 15:14:50');

UPDATE `forum`.`user`
SET `my_board_count` = '1'
WHERE (`id` = '2');

-- 新增版块
INSERT INTO `forum`.`board` (`host_id`, `title`, `type`, `host_name`)
VALUES (3, 'Python学习交流', 'Python', "李四"),
       (4, 'Web开发讨论区', 'Web', "王五"),
       (3, '人工智能与机器学习', 'AI', "李四"),
       (5, '移动开发', 'Mobile', "赵六"),
       (6, '数据库技术', 'Database', "陈七");

-- 生成帖子
INSERT INTO `forum`.`post` (`title`, `content`, `author_id`, `board_id`)
VALUES
-- 版块1（Java）
('Java入门指南', 'Java基础语法和开发环境配置...', 2, 1),
('Spring框架实战', 'Spring Boot快速入门教程...', 3, 1),
('MySQL索引优化', 'B+树原理与索引设计...', 8, 1),
('Redis缓存设计', 'Redis持久化策略...', 11, 1),
('微服务架构', 'Spring Cloud实战...', 12, 1),
-- 版块2（Python）
('Python数据分析', '迭代方法...', 4, 2),
('机器学习入门', '线性回归模型原理...', 10, 2),
('Django项目实战', '从零搭建一个博客系统...', 5, 2),
('Flask快速开发', '轻量级Web框架实践...', 13, 2),
('Linux系统管理', '常用命令与Shell脚本...', 16, 2),
-- 版块3（Web）
('React最佳实践', 'React Hooks深度解析...', 6, 3),
('Node.js性能优化', 'Node.js高并发解决方案...', 7, 3),
('Vue3新特性', 'Composition API详解...', 2, 3),
('前端工程化', 'Webpack配置指南...', 14, 3),
('Git高级技巧', 'Rebase与Cherry-pick...', 15, 3);



-- 生成评论
INSERT INTO `forum`.`comment` (`post_id`, `user_id`, `content`, `parent_id`)
VALUES (1, 3, '非常实用的入门教程！', NULL),
       (1, 4, '期待更新更多内容！', 2),
       (2, 5, 'Spring Boot真方便！', NULL),
       (2, 6, '有没有项目源码？', 4),
       (3, 7, '这个办法处理数据确实高效', NULL),
       (3, 8, '案例可以再详细些吗？', NULL),
       (15, 19, '脚本部分讲得很清楚', NULL);

-- 生成关注关系
INSERT INTO `forum`.`subscription` (`user_id`, `subscribe_to_user_id`, `subscribe_to_board_id`)
VALUES (2, 3, NULL),
       (2, 5, NULL),
       (2, NULL, 1),
       (3, NULL, 1),
       (4, 5, NULL),
       (5, NULL, 2);

