DROP TABLE course;
DROP TABLE subject;
DROP TABLE users;

CREATE TABLE users (
	no VARCHAR(100) PRIMARY KEY,
	id VARCHAR(100) UNIQUE,
	pw VARCHAR(100),
	name VARCHAR(100),
	title VARCHAR(100)
);

CREATE TABLE subject (
	sno VARCHAR(100) UNIQUE,
	sname VARCHAR(100),
	pno VARCHAR(100),
	PRIMARY KEY (sno, sname),
	CONSTRAINT subject_pno_fk FOREIGN KEY (pno)
	REFERENCES users (no)
);
	
CREATE TABLE course (
	no VARCHAR(100),
	sno VARCHAR(100),
	grade VARCHAR(100),
	PRIMARY KEY (no, sno),
	CONSTRAINT course_no_fk FOREIGN KEY (no)
	REFERENCES users (no),
	CONSTRAINT course_sno_fk FOREIGN KEY (sno)
	REFERENCES subject (sno)
);

INSERT INTO users VALUES('0', 'admin', '123', '홍길동', '관리자');
