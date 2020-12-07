create sequence s_category;

alter sequence s_category owner to postgres;

create sequence s_contractor;

alter sequence s_contractor owner to postgres;

create sequence s_material_list;

alter sequence s_material_list owner to postgres;

create sequence s_publication;

alter sequence s_publication owner to postgres;

create sequence s_report;

alter sequence s_report owner to postgres;

create table category
(
    id            bigint    not null
        constraint category_pkey
            primary key,
    created_at    timestamp not null,
    deleted_at    timestamp,
    updated_at    timestamp not null,
    category_name varchar(255),
    description   varchar(255)
);

alter table category
    owner to postgres;

create table contractor
(
    id              bigint    not null
        constraint contractor_pkey
            primary key,
    created_at      timestamp not null,
    deleted_at      timestamp,
    updated_at      timestamp not null,
    contractor_name varchar(255),
    e_mail          varchar(255),
    category_id     bigint
        constraint fk3bq3ri54ixcd0wfj2t1ymwvck
            references category,
    bin             varchar(255),
    phone_number    varchar(255)
);

alter table contractor
    owner to postgres;

create table report
(
    id                    bigint    not null
        constraint report_pkey
            primary key,
    created_at            timestamp not null,
    deleted_at            timestamp,
    updated_at            timestamp not null,
    auto_sending          boolean,
    generate_in_friday    boolean,
    generate_in_monday    boolean,
    generate_in_saturday  boolean,
    generate_in_sunday    boolean,
    generate_in_thursday  boolean,
    generate_in_tuesday   boolean,
    generate_in_wednesday boolean,
    name                  varchar(255),
    send_after_time       bigint,
    time_of_publication   varchar(255)
);

alter table report
    owner to postgres;

create table material_list
(
    id               bigint    not null
        constraint material_list_pkey
            primary key,
    created_at       timestamp not null,
    deleted_at       timestamp,
    updated_at       timestamp not null,
    material_code    varchar(255),
    material_link    bigint,
    material_measure varchar(255),
    material_name    varchar(255),
    material_owner   bigint,
    report_id        bigint
        constraint fkth8d70aklum7we5b0e5yimd5e
            references report
);

alter table material_list
    owner to postgres;

create table publication
(
    id               bigint    not null
        constraint publication_pkey
            primary key,
    created_at       timestamp not null,
    deleted_at       timestamp,
    updated_at       timestamp not null,
    auto_sending     boolean,
    publication_date timestamp,
    status           varchar(255),
    report_id        bigint
        constraint fkgehv6tqmemvhk0lapj5hysxye
            references report
);

alter table publication
    owner to postgres;

create table report_category
(
    category_id bigint not null
        constraint fkflhvowyerk4ioi0megn84xl19
            references report,
    report_id   bigint not null
        constraint fkjrl4vrk1mypnay48p1donr5ru
            references category
);

alter table report_category
    owner to postgres;
