use forum;

create table comments (
        deleted bit not null,
        comment_id bigint not null auto_increment,
        created_at datetime(6),
        post_id bigint,
        updated_at datetime(6),
        user_id bigint,
        body varchar(255),
        primary key (comment_id)
    ) engine=InnoDB DEFAULT CHARSET = utf8;

    create table comment_comments (
        child_id bigint not null,
        parent_id bigint not null
    ) engine=InnoDB DEFAULT CHARSET = utf8;
    
    create table comment_upvotes (
        comment_id bigint not null,
        user_id bigint not null,
        PRIMARY KEY (comment_id, user_id)
    ) engine=InnoDB DEFAULT CHARSET = utf8;
    
    create table posts (
        created_at datetime(6),
        post_id bigint not null auto_increment,
        updated_at datetime(6),
        upvote bigint,
        user_id bigint,
        body varchar(255) not null,
        title varchar(255) not null,
        primary key (post_id)
    ) engine=InnoDB DEFAULT CHARSET = utf8;
    
    create table users (
        username varchar(4) not null,
        created_at datetime(6),
        user_id bigint not null auto_increment,
        email varchar(255) not null,
        password varchar(255) not null,
        primary key (user_id)
    ) engine=InnoDB DEFAULT CHARSET = utf8;
    
    alter table comment_comments 
       add constraint FKcbcqvbilo8mfkr5oetnnd7o5p 
       foreign key (child_id) 
       references comments (comment_id);
       
    alter table comment_comments 
       add constraint FKkeosamd5ythie9229xdric1dd 
       foreign key (parent_id) 
       references comments (comment_id);
       
    alter table comments 
       add constraint FKh4c7lvsc298whoyd4w9ta25cr 
       foreign key (post_id) 
       references posts (post_id);
       
    alter table comments 
       add constraint FK8omq0tc18jd43bu5tjh6jvraq 
       foreign key (user_id) 
       references users (user_id);
       
    alter table posts 
       add constraint FK5lidm6cqbc7u4xhqpxm898qme 
       foreign key (user_id) 
       references users (user_id);


