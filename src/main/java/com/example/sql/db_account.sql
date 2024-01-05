create table db_account
(
    id            integer      null,
    username      varchar(255) null,
    email         varchar(255) null,
    password      varchar(255) null,
    role          varchar(255) null,
    register_time datetime     null,
    points        integer      null,
    avtar_url     varchar(255) null,
    avtar_uuid    varchar(255) null
);
insert into test.db_account (id, username, email, password, role, register_time, points, avatar_url, avatar_uuid) values (8, 'yhempr', 'yhempr@gmail.com', '$2a$10$zJLWCSvVbKTTt.UgkgklkeaJVnSQVHFj.XTRTVH3HhithpafAQ3/y', 'user', '2024-01-04 09:54:07', 5, null, null);
insert into test.db_account (id, username, email, password, role, register_time, points, avatar_url, avatar_uuid) values (9, '马英九', '2323223@qq.com', '$2a$10$9C1sEUj30.Gky5YTg2NBp.GLemjnftCTu/aiR1YlyPwokLdDfI9yG', 'user', '2024-01-04 09:56:43', 5, null, null);
insert into test.db_account (id, username, email, password, role, register_time, points, avatar_url, avatar_uuid) values (10, '马英华', '2031282938@qq.com', '$2a$10$oP9Hj6EWcYJ7e/U.I6Cd8O4hyAThis5mYCZaKTPd/.SYa7.u2c3L2', 'user', '2024-01-04 10:20:04', 5, null, null);
insert into test.db_account (id, username, email, password, role, register_time, points, avatar_url, avatar_uuid) values (11, 'jocker', 'myhsnd666@gmail.com', '$2a$10$4v9twRnhoepwqlhW0.hDveVoh3W4FmQA.PDdWRuNwGs0quDoM62mG', 'user', '2024-01-04 13:40:49', 5, null, null);
insert into test.db_account (id, username, email, password, role, register_time, points, avatar_url, avatar_uuid) values (12, '小丑', '851314610@qq.com', '$2a$10$Unl.fve4zSloPy6QC7rX5u1zyQO8Pb7JB2rxOHGXkf8aeux.n4m9C', 'user', '2024-01-04 18:18:49', 5, null, null);
