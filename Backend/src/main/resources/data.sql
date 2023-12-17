# CREATE TABLE IF NOT EXISTS project
# (
#     `id`              BIGINT          NOT NULL    AUTO_INCREMENT,
#     `title`           VARCHAR(255)    NOT NULL    COMMENT '제목',
#     `content`         TEXT            NOT NULL    COMMENT '내용',
#     `like_count`      INT             NOT NULL    COMMENT '좋아요수',
#     `visited_number`  INT             NOT NULL    COMMENT '조회수',
#     `created_at`      TIMESTAMP       NOT NULL    COMMENT '생성일',
#     `updated_at`      TIMESTAMP       NOT NULL    COMMENT '수정일',
#     `project_type`    VARCHAR(255)    NOT NULL    COMMENT '프로젝트 분야',
#     `start_date`      VARCHAR(255)    NOT NULL    COMMENT '프로젝트 시작일',
#     `end_date`        VARCHAR(255)    NOT NULL    COMMENT '프로젝트 종료일',
#     `location`        INT             NOT NULL    COMMENT '위치',
#     `is_available`    VARCHAR(255)    NOT NULL,
#     `avg_score`       FLOAT           NULL        COMMENT '평균 숙련도',
#     PRIMARY KEY (id)
# );
#
# CREATE TABLE IF NOT EXISTS user
# (
#     `id`                BIGINT          NOT NULL    AUTO_INCREMENT,
#     `nickname`          VARCHAR(255)    NULL,
#     `password`          VARCHAR(255)    NULL,
#     `profile`           BLOB            NULL,
#     `introduce`         TEXT            NULL,
#     `email`             VARCHAR(255)    NULL,
#     `stacks`            VARCHAR(255)    NULL        COMMENT '사용스택 문자열로 표현',
#     `github_nickname`   VARCHAR(255)    NULL,
#     PRIMARY KEY (id)
# );
#
# CREATE TABLE IF NOT EXISTS apply
# (
#     `id`          BIGINT          NOT NULL    AUTO_INCREMENT,
#     `project_id`  BIGINT          NOT NULL,
#     `user_id`     BIGINT          NOT NULL,
#     `state`       VARCHAR(255)    NOT NULL,
#     PRIMARY KEY (id)
# );
#
# ALTER TABLE apply
#     ADD CONSTRAINT FK_apply_project_id_project_id FOREIGN KEY (project_id)
#         REFERENCES project (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
#
# ALTER TABLE apply
#     ADD CONSTRAINT FK_apply_user_id_user_id FOREIGN KEY (user_id)
#         REFERENCES user (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
#
# CREATE TABLE IF NOT EXISTS project_member
# (
#     `id`          BIGINT          NOT NULL    AUTO_INCREMENT,
#     `project_id`  BIGINT          NOT NULL    COMMENT '프로젝트 id',
#     `user_id`     BIGINT          NOT NULL    COMMENT '유저 id',
#     `position`    VARCHAR(255)    NOT NULL    COMMENT '포지션',
#     PRIMARY KEY (id)
# );
#
# ALTER TABLE project_member
#     ADD CONSTRAINT FK_project_member_project_id_project_id FOREIGN KEY (project_id)
#         REFERENCES project (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
#
# ALTER TABLE project_member
#     ADD CONSTRAINT FK_project_member_user_id_user_id FOREIGN KEY (user_id)
#         REFERENCES user (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
#
# CREATE TABLE IF NOT EXISTS invitation
# (
#     `id`          BIGINT          NOT NULL    AUTO_INCREMENT,
#     `project_id`  BIGINT          NOT NULL    COMMENT '프로젝트 id',
#     `user_id`     BIGINT          NOT NULL    COMMENT '초대받은 유저 id',
#     `state`       VARCHAR(255)    NOT NULL    COMMENT '승인 여부',
#     PRIMARY KEY (id)
# );
#
# ALTER TABLE invitation
#     ADD CONSTRAINT FK_invitation_user_id_user_id FOREIGN KEY (user_id)
#         REFERENCES user (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
#
# ALTER TABLE invitation
#     ADD CONSTRAINT FK_invitation_project_id_project_id FOREIGN KEY (project_id)
#         REFERENCES project (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
#
# CREATE TABLE IF NOT EXISTS project_like
# (
#     `id`          BIGINT    NOT NULL    AUTO_INCREMENT,
#     `project_id`  BIGINT    NULL,
#     `user_id`     BIGINT    NULL,
#     PRIMARY KEY (id)
# );
#
# ALTER TABLE project_like
#     ADD CONSTRAINT FK_project_like_user_id_user_id FOREIGN KEY (user_id)
#         REFERENCES user (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
#
# ALTER TABLE project_like
#     ADD CONSTRAINT FK_project_like_project_id_project_id FOREIGN KEY (project_id)
#         REFERENCES project (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
#
# CREATE TABLE IF NOT EXISTS project_front
# (
#     `id`               BIGINT         NOT NULL    AUTO_INCREMENT,
#     `project_id`       BIGINT         NULL        COMMENT '프로젝트 id',
#     `required_member`  INT            NULL        COMMENT '구인 멤버수',
#     `current_member`   INT            NULL        COMMENT '현재 멤버수',
#     `stacks`           VARCHAR(50)    NULL        COMMENT '요구 스택',
#     PRIMARY KEY (id)
# );
#
# ALTER TABLE project_front
#     ADD CONSTRAINT FK_project_front_project_id_project_id FOREIGN KEY (project_id)
#         REFERENCES project (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
#
# CREATE TABLE IF NOT EXISTS project_back
# (
#     `id`               BIGINT          NOT NULL    AUTO_INCREMENT,
#     `project_id`       BIGINT          NULL        COMMENT '프로젝트 id',
#     `required_member`  INT             NULL        COMMENT '구인 멤버수',
#     `current_member`   INT             NULL        COMMENT '현재 멤버수',
#     `stacks`           VARCHAR(255)    NULL        COMMENT '요구 스택',
#     PRIMARY KEY (id)
# );
#
# ALTER TABLE project_back
#     ADD CONSTRAINT FK_project_back_project_id_project_id FOREIGN KEY (project_id)
#         REFERENCES project (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
#
# CREATE TABLE IF NOT EXISTS project_etc
# (
#     `id`               BIGINT          NOT NULL    AUTO_INCREMENT,
#     `project_id`       BIGINT          NULL        COMMENT '프로젝트 id',
#     `required_member`  INT             NULL        COMMENT '구인 멤버수',
#     `current_member`   INT             NULL        COMMENT '현재 멤버수',
#     `stacks`           VARCHAR(255)    NULL        COMMENT '요구 스택',
#     PRIMARY KEY (id)
# );
#
# ALTER TABLE project_etc
#     ADD CONSTRAINT FK_project_etc_project_id_project_id FOREIGN KEY (project_id)
#         REFERENCES project (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
#
# -- stack table 50개 생성
# CREATE TABLE IF NOT EXISTS  `node_js` (
#                                          `id` BIGINT AUTO_INCREMENT,
#                                          `user_id` BIGINT,
#                                          `score` FLOAT,
#                                          PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `react` (
#                                        `id` BIGINT AUTO_INCREMENT,
#                                        `user_id` BIGINT,
#                                        `score` FLOAT,
#                                        PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `jquery` (
#                                         `id` BIGINT AUTO_INCREMENT,
#                                         `user_id` BIGINT,
#                                         `score` FLOAT,
#                                         PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `express` (
#                                          `id` BIGINT AUTO_INCREMENT,
#                                          `user_id` BIGINT,
#                                          `score` FLOAT,
#                                          PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `angular` (
#                                          `id` BIGINT AUTO_INCREMENT,
#                                          `user_id` BIGINT,
#                                          `score` FLOAT,
#                                          PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `next_js` (
#                                          `id` BIGINT AUTO_INCREMENT,
#                                          `user_id` BIGINT,
#                                          `score` FLOAT,
#                                          PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `vue_js` (
#                                         `id` BIGINT AUTO_INCREMENT,
#                                         `user_id` BIGINT,
#                                         `score` FLOAT,
#                                         PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `flask` (
#                                        `id` BIGINT AUTO_INCREMENT,
#                                        `user_id` BIGINT,
#                                        `score` FLOAT,
#                                        PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `spring_boot` (
#                                              `id` BIGINT AUTO_INCREMENT,
#                                              `user_id` BIGINT,
#                                              `score` FLOAT,
#                                              PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `django` (
#                                         `id` BIGINT AUTO_INCREMENT,
#                                         `user_id` BIGINT,
#                                         `score` FLOAT,
#                                         PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `laravel` (
#                                          `id` BIGINT AUTO_INCREMENT,
#                                          `user_id` BIGINT,
#                                          `score` FLOAT,
#                                          PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `angular_js` (
#                                             `id` BIGINT AUTO_INCREMENT,
#                                             `user_id` BIGINT,
#                                             `score` FLOAT,
#                                             PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `svelte` (
#                                         `id` BIGINT AUTO_INCREMENT,
#                                         `user_id` BIGINT,
#                                         `score` FLOAT,
#                                         PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `ruby_on_rails` (
#                                                `id` BIGINT AUTO_INCREMENT,
#                                                `user_id` BIGINT,
#                                                `score` FLOAT,
#                                                PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `nest_js` (
#                                          `id` BIGINT AUTO_INCREMENT,
#                                          `user_id` BIGINT,
#                                          `score` FLOAT,
#                                          PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `blazor` (
#                                         `id` BIGINT AUTO_INCREMENT,
#                                         `user_id` BIGINT,
#                                         `score` FLOAT,
#                                         PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `asp_net` (
#                                          `id` BIGINT AUTO_INCREMENT,
#                                          `user_id` BIGINT,
#                                          `score` FLOAT,
#                                          PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `dot_net` (
#                                          `id` BIGINT AUTO_INCREMENT,
#                                          `user_id` BIGINT,
#                                          `score` FLOAT,
#                                          PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `pandas` (
#                                         `id` BIGINT AUTO_INCREMENT,
#                                         `user_id` BIGINT,
#                                         `score` FLOAT,
#                                         PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `rabbit_mq` (
#                                            `id` BIGINT AUTO_INCREMENT,
#                                            `user_id` BIGINT,
#                                            `score` FLOAT,
#                                            PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `tensor_flow` (
#                                              `id` BIGINT AUTO_INCREMENT,
#                                              `user_id` BIGINT,
#                                              `score` FLOAT,
#                                              PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `scikit_learn` (
#                                               `id` BIGINT AUTO_INCREMENT,
#                                               `user_id` BIGINT,
#                                               `score` FLOAT,
#                                               PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `flutter` (
#                                          `id` BIGINT AUTO_INCREMENT,
#                                          `user_id` BIGINT,
#                                          `score` FLOAT,
#                                          PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `torch` (
#                                        `id` BIGINT AUTO_INCREMENT,
#                                        `user_id` BIGINT,
#                                        `score` FLOAT,
#                                        PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `py_torch` (
#                                           `id` BIGINT AUTO_INCREMENT,
#                                           `user_id` BIGINT,
#                                           `score` FLOAT,
#                                           PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `react_native` (
#                                               `id` BIGINT AUTO_INCREMENT,
#                                               `user_id` BIGINT,
#                                               `score` FLOAT,
#                                               PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `open_cv` (
#                                          `id` BIGINT AUTO_INCREMENT,
#                                          `user_id` BIGINT,
#                                          `score` FLOAT,
#                                          PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `electron` (
#                                           `id` BIGINT AUTO_INCREMENT,
#                                           `user_id` BIGINT,
#                                           `score` FLOAT,
#                                           PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `open_gl` (
#                                          `id` BIGINT AUTO_INCREMENT,
#                                          `user_id` BIGINT,
#                                          `score` FLOAT,
#                                          PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `qt` (
#                                     `id` BIGINT AUTO_INCREMENT,
#                                     `user_id` BIGINT,
#                                     `score` FLOAT,
#                                     PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `keras` (
#                                        `id` BIGINT AUTO_INCREMENT,
#                                        `user_id` BIGINT,
#                                        `score` FLOAT,
#                                        PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `apache_spark` (
#                                               `id` BIGINT AUTO_INCREMENT,
#                                               `user_id` BIGINT,
#                                               `score` FLOAT,
#                                               PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `swift_ui` (
#                                           `id` BIGINT AUTO_INCREMENT,
#                                           `user_id` BIGINT,
#                                           `score` FLOAT,
#                                           PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `kotlin` (
#                                         `id` BIGINT AUTO_INCREMENT,
#                                         `user_id` BIGINT,
#                                         `score` FLOAT,
#                                         PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `java_script` (
#                                              `id` BIGINT AUTO_INCREMENT,
#                                              `user_id` BIGINT,
#                                              `score` FLOAT,
#                                              PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `html_css` (
#                                           `id` BIGINT AUTO_INCREMENT,
#                                           `user_id` BIGINT,
#                                           `score` FLOAT,
#                                           PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `python` (
#                                         `id` BIGINT AUTO_INCREMENT,
#                                         `user_id` BIGINT,
#                                         `score` FLOAT,
#                                         PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `type_script` (
#                                              `id` BIGINT AUTO_INCREMENT,
#                                              `user_id` BIGINT,
#                                              `score` FLOAT,
#                                              PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `java` (
#                                       `id` BIGINT AUTO_INCREMENT,
#                                       `user_id` BIGINT,
#                                       `score` FLOAT,
#                                       PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `c_sharp` (
#                                          `id` BIGINT AUTO_INCREMENT,
#                                          `user_id` BIGINT,
#                                          `score` FLOAT,
#                                          PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `cpp` (
#                                      `id` BIGINT AUTO_INCREMENT,
#                                      `user_id` BIGINT,
#                                      `score` FLOAT,
#                                      PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `c` (
#                                    `id` BIGINT AUTO_INCREMENT,
#                                    `user_id` BIGINT,
#                                    `score` FLOAT,
#                                    PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `php` (
#                                      `id` BIGINT AUTO_INCREMENT,
#                                      `user_id` BIGINT,
#                                      `score` FLOAT,
#                                      PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `go` (
#                                     `id` BIGINT AUTO_INCREMENT,
#                                     `user_id` BIGINT,
#                                     `score` FLOAT,
#                                     PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `rust` (
#                                       `id` BIGINT AUTO_INCREMENT,
#                                       `user_id` BIGINT,
#                                       `score` FLOAT,
#                                       PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `ruby` (
#                                       `id` BIGINT AUTO_INCREMENT,
#                                       `user_id` BIGINT,
#                                       `score` FLOAT,
#                                       PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `lua` (
#                                      `id` BIGINT AUTO_INCREMENT,
#                                      `user_id` BIGINT,
#                                      `score` FLOAT,
#                                      PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `dart` (
#                                       `id` BIGINT AUTO_INCREMENT,
#                                       `user_id` BIGINT,
#                                       `score` FLOAT,
#                                       PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `swift` (
#                                        `id` BIGINT AUTO_INCREMENT,
#                                        `user_id` BIGINT,
#                                        `score` FLOAT,
#                                        PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );
#
#
# CREATE TABLE IF NOT EXISTS  `r` (
#                                    `id` BIGINT AUTO_INCREMENT,
#                                    `user_id` BIGINT,
#                                    `score` FLOAT,
#                                    PRIMARY KEY (`id`),
#     FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
#     );