INSERT
IGNORE INTO member(email, nickname, password, role, image_url)
VALUES ('aa@aa.com', '로피', '1234', 'USER',
        'https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000');

INSERT
IGNORE INTO member(email, nickname, password, role, image_url)
VALUES ('bb@bb.com', '링링', '1234', 'USER',
        'https://newsimg.hankookilbo.com/2017/11/06/201711060912547465_1.jpg');

INSERT
IGNORE INTO member(email, nickname, password, role, image_url)
VALUES ('cc@cc.com', '하티', '1234', 'USER',
        'https://mblogthumb-phinf.pstatic.net/MjAyMTAyMDRfNjIg/MDAxNjEyNDA4OTk5NDQ4.6UGs399-0EXjIUwwWsYg7o66lDb-MPOVQ-zNDy1Wnnkg.m-WZz0IKKnc5OO2mjY5dOD-0VsfpXg7WVGgds6fKwnIg.JPEG.sunny_side_up12/1612312679152%EF%BC%8D2.jpg?type=w800');

INSERT
IGNORE INTO member(email, nickname, password, role, image_url)
VALUES ('dd@dd.com', '관리자', '1234', 'ADMIN',
        'https://t1.daumcdn.net/news/202105/22/petzzi/20210522141352278yebt.jpg');

INSERT
IGNORE INTO notice(title, description, created_at, writer_id)
VALUES ('제목입니다', '본문입니다', now(), 1);

INSERT
IGNORE INTO image(notice_id,image_url)
VALUES (1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3z5gsHelQmfcSmSGZxOJKfmCU71wrnXes4JiffDvS&s');
