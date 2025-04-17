DROP DATABASE IF EXISTS `forum`;

CREATE SCHEMA `forum` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE forum;

-- 用户表
CREATE TABLE user (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      email VARCHAR(255) UNIQUE COMMENT '邮箱',
                      phone VARCHAR(20) UNIQUE COMMENT '手机号',
                      password VARCHAR(255) NOT NULL COMMENT '密码',
                      is_admin BOOLEAN DEFAULT FALSE COMMENT '是否为管理员',
                      name VARCHAR(50) NOT NULL COMMENT '昵称',
                      grade INT DEFAULT 1 COMMENT '等级',
                      avatar VARCHAR(255) COMMENT '头像路径',
                      if_receive_like BOOLEAN DEFAULT TRUE COMMENT '接收点赞通知',
                      INDEX idx_email (email),
                      INDEX idx_phone (phone)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 板块表
CREATE TABLE board (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       host_id INT NOT NULL COMMENT '版主ID',
                       time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                       title VARCHAR(255) NOT NULL COMMENT '标题',
                       type VARCHAR(255) NOT NULL COMMENT '类型',
                       post_count INT DEFAULT 0 COMMENT '帖子数量',
                       FOREIGN KEY (host_id) REFERENCES user(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 帖子表
CREATE TABLE post (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      title VARCHAR(255) NOT NULL COMMENT '标题',
                      content TEXT NOT NULL COMMENT '内容',
                      author_id INT NOT NULL COMMENT '作者ID',
                      board_id INT NOT NULL COMMENT '版块ID',
                      view_count INT DEFAULT 0 COMMENT '浏览次数',
                      like_count INT DEFAULT 0 COMMENT '点赞数',
                      comment_count INT DEFAULT 0 COMMENT '评论数量',
                      time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                      FOREIGN KEY (author_id) REFERENCES user(id) ON DELETE CASCADE,
                      FOREIGN KEY (board_id) REFERENCES board(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 举报表
CREATE TABLE report (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        user_id INT NOT NULL COMMENT '举报者ID',
                        board_id INT NOT NULL COMMENT '版块ID',
                        post_id INT NOT NULL COMMENT '帖子ID',
                        reported_id INT NOT NULL COMMENT '被举报者ID',
                        judge BOOLEAN NOT NULL COMMENT '举报受理对象', # 1 -> 管理员，0 -> 版主
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                        FOREIGN KEY (board_id) REFERENCES board(id) ON DELETE CASCADE,
                        FOREIGN KEY (post_id) REFERENCES post(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- 历史记录表
CREATE TABLE history (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         user_id INT NOT NULL COMMENT '用户ID',
                         board_id INT NOT NULL COMMENT '版块ID',
                         post_id INT NOT NULL COMMENT '帖子ID',
                         time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '浏览时间',
                         FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                         FOREIGN KEY (board_id) REFERENCES board(id) ON DELETE CASCADE,
                         FOREIGN KEY (post_id) REFERENCES post(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 收藏表
CREATE TABLE collect (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         user_id INT NOT NULL COMMENT '用户ID',
                         board_id INT NOT NULL COMMENT '版块ID',
                         post_id INT NOT NULL COMMENT '帖子ID',
                         FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                         FOREIGN KEY (board_id) REFERENCES board(id) ON DELETE CASCADE,
                         FOREIGN KEY (post_id) REFERENCES post(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 版块公告表
CREATE TABLE notice (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        board_id INT NOT NULL COMMENT '版块ID',
                        time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
                        content TEXT NOT NULL COMMENT '公告内容',
                        FOREIGN KEY (board_id) REFERENCES board(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 评论表
CREATE TABLE comment (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         post_id INT NOT NULL COMMENT '帖子ID',
                         time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
                         content TEXT NOT NULL COMMENT '评论内容',
                         user_id INT NOT NULL COMMENT '评论者ID',
                         parent_id INT COMMENT '父评论ID',
                         like_count INT DEFAULT 0 COMMENT '点赞数',
                         FOREIGN KEY (post_id) REFERENCES post(id) ON DELETE CASCADE,
                         FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                         FOREIGN KEY (parent_id) REFERENCES comment(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 版块封禁表
CREATE TABLE board_ban (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           ban_id INT NOT NULL COMMENT '被禁用户ID',
                           board_id INT NOT NULL COMMENT '版块ID',
                           FOREIGN KEY (ban_id) REFERENCES user(id) ON DELETE CASCADE,
                           FOREIGN KEY (board_id) REFERENCES board(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 通知表
CREATE TABLE message (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         content TEXT NOT NULL COMMENT '通知内容',
                         user_id INT NOT NULL COMMENT '接收用户ID',
                         time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
                         FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 日志表
CREATE TABLE log (
                     id INT PRIMARY KEY AUTO_INCREMENT,
                     user_id INT NOT NULL COMMENT '用户ID',
                     user_name VARCHAR(50) NOT NULL COMMENT '用户名',
                     action VARCHAR(255) NOT NULL COMMENT '操作内容',
                     ip VARCHAR(45) NOT NULL COMMENT 'IP地址',
                     time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
                     FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 关注表
CREATE TABLE subscription (
                              id INT PRIMARY KEY AUTO_INCREMENT,
                              user_id INT NOT NULL,
                              subscribe_to_user_id INT,
                              subscribe_to_board_id INT,
                              FOREIGN KEY (subscribe_to_user_id) REFERENCES user(id) ON DELETE CASCADE,
                              FOREIGN KEY (subscribe_to_board_id) REFERENCES board(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `forum`.`user`
    ADD COLUMN `receive_like_count` INT NULL DEFAULT '0' AFTER `if_receive_like`,
ADD COLUMN `fans_count` INT NULL DEFAULT '0' AFTER `receive_like_count`,
ADD COLUMN `post_count` INT NULL DEFAULT '0' AFTER `fans_count`,
ADD COLUMN `receive_read_count` INT NULL DEFAULT '0' AFTER `post_count`,
ADD COLUMN `my_subscribe_count` INT NULL DEFAULT '0' AFTER `receive_read_count`,
ADD COLUMN `my_collect_count` INT NULL DEFAULT '0' AFTER `my_subscribe_count`,
ADD COLUMN `my_board_count` INT NULL DEFAULT '0' AFTER `my_collect_count`;

INSERT INTO `forum`.`user` (`id`, `email`, `phone`, `password`, `is_admin`, `name`, `grade`, `avatar`, `if_receive_like`) VALUES ('1', 'y@y.com', '18300000985', 'yyy111', '1', 'Yiiie.', '7', 'initAvatar.jpg', '0');
INSERT INTO `forum`.`user` (`id`, `email`, `phone`, `password`, `is_admin`, `name`, `grade`, `avatar`, `if_receive_like`) VALUES ('2', '3@3.com', '18300003030', 'sss333', '0', '张三', '3', 'initAvatar.jpg', '1');

INSERT INTO `forum`.`board` (`id`, `host_id`, `title`, `type`, `post_count`, `time`) VALUES ('1', '2', '张三学java', 'java', '0', '2025-04-14 15:14:50');
INSERT INTO `forum`.`log` (`id`, `user_id`, `user_name`, `action`, `ip`, `time`) VALUES ('1', '2', '张三', '新建版块', '127.0.0.1', '2025-04-14 15:14:50');

