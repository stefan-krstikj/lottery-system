insert into users (first_name, is_account_non_expired, is_account_non_locked, is_credentials_non_expired,
                   is_enabled, last_name, password, username)
values ('firstname', true, true, true, true, 'lastname',
        '$2a$10$Y.a4JYX4MQ6cC7Vt1mg3D.uzfZ5trNyhdYsoD5RPrRX2e/bZBRk7.', 'user1');

insert into users (first_name, is_account_non_expired, is_account_non_locked, is_credentials_non_expired,
                   is_enabled, last_name, password, username)
values ('Stefan', true, true, true, true, 'Krstikj',
        '$2a$10$GTEH9pH/OBueB4VpJS1rgeXRMTBWip5krdWs2q88ewHJkN2D77g2S',
        'stefankrstikj');

insert into users (first_name, is_account_non_expired, is_account_non_locked, is_credentials_non_expired,
                   is_enabled, last_name, password, username)
values ('Johnny', true, true, true, true, 'Bravo',
        '$2a$10$xRlCx5ZiyfVzNnQIJ4L/VuQqT83DxshhL74WWo6xhrR9SgCUKXCSG', 'johnnybravo');
