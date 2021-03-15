CREATE TABLE test (
    loginId VARCHAR(20) NOT NULL COMMENT '아이디 (PK)',
    loginPw VARCHAR(64) NOT NULL COMMENT '비밀번호',
    name VARCHAR(20) NOT NULL COMMENT '이름',
    email VARCHAR(20) NOT NULL COMMENT '이메일',
    nickName INT NOT NULL DEFAULT 0 COMMENT '닉네임',
    status ENUM('Y', 'N') NOT NULL DEFAULT 'N' COMMENT '삭제 여부',
    createTime DATETIME NOT NULL DEFAULT NOW() COMMENT '가입일',
    loginFlag ENUM('0', '1') NOT NULL DEFAULT '0' COMMENT '로그인 여부',
	profile VARCHAR(100) NOT NULL COMMENT '프로필',
    PRIMARY KEY (loginId)
)COMMENT '회원가입';