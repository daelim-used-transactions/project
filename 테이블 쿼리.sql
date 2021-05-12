CREATE TABLE listboard (
    boardIdx int NOT NULL auto_increment COMMENT'보더 (PK)',
    loginId VARCHAR(64) NOT NULL COMMENT '쓴 아이디',
    title VARCHAR(20) NOT NULL COMMENT '게시판 제목',
    price VARCHAR(64) NOT NULL COMMENT '가격',
    category VARCHAR(20) NOT NULL COMMENT '카테고리',
    contents VARCHAR(100) NOT NULL COMMENT '내용',
    deleteYn ENUM('Y', 'N') NOT NULL DEFAULT 'N' COMMENT '삭제 여부', 
    insertTime DATETIME NOT NULL DEFAULT NOW() COMMENT '가입일',
	deleteTime DATETIME  COMMENT '삭제일',
    PRIMARY KEY (boardIdx),
    foreign key (loginId) references test(loginId)
)COMMENT '게시판';

CREATE TABLE attach (
    attachIdx int NOT NULL auto_increment COMMENT'파일번호 (PK)',
    boardIdx int NOT NULL COMMENT '쓴 게시판',
    saveNane VARCHAR(64) NOT NULL COMMENT '사진명',
    size long NOT NULL COMMENT '사진 크기',
    deleteYn ENUM('Y', 'N') NOT NULL DEFAULT 'N' COMMENT '삭제 여부', 
    insertTime DATETIME NOT NULL DEFAULT NOW() COMMENT '가입일',
    deleteTime DATETIME  COMMENT '삭제일',
    count int NOT NULL COMMENT '파일 갯수',
    PRIMARY KEY (attachIdx),
    foreign key (boardIdx) references listboard(boardIdx)
)COMMENT '파일';

CREATE TABLE buylistboard(
    boardIdx int NOT NULL auto_increment COMMENT'보더 (PK)',
    loginId VARCHAR(64) NOT NULL COMMENT '쓴 아이디',
    title VARCHAR(20) NOT NULL COMMENT '게시판 제목',
    category VARCHAR(20) NOT NULL COMMENT '카테고리',
    contents VARCHAR(100) NOT NULL COMMENT '내용',
    deleteYn ENUM('Y', 'N') NOT NULL DEFAULT 'N' COMMENT '삭제 여부', 
    insertTime DATETIME NOT NULL DEFAULT NOW() COMMENT '가입일',
	deleteTime DATETIME  COMMENT '삭제일',
    PRIMARY KEY (boardIdx),
    foreign key (loginId) references test(loginId)
)COMMENT '게시판';


CREATE TABLE buy_comment (
    idx INT NOT NULL AUTO_INCREMENT COMMENT '번호 (PK)',
    boardIdx INT NOT NULL COMMENT '게시글 번호 (FK)',
    content VARCHAR(3000) NOT NULL COMMENT '내용',
    writer VARCHAR(20) NOT NULL COMMENT '작성자',
    deleteYn ENUM('Y', 'N') NOT NULL DEFAULT 'N' COMMENT '삭제 여부',
    insertTime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
    updateTime DATETIME DEFAULT NULL COMMENT '수정일',
    deleteTime DATETIME DEFAULT NULL COMMENT '삭제일',
    PRIMARY KEY (idx)
) COMMENT '댓글';

alter table buy_comment add constraint fk_comment_board_idx foreign key (boardIdx) references buylistboard(boardIdx);

