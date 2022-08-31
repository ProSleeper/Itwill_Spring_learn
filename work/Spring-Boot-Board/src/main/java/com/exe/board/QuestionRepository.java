package com.exe.board;

import org.springframework.data.jpa.repository.JpaRepository;

//데이터 처리를 위해서는 실제 데이터베이스와 연동하는
//JPA의 Repository가 필요

//엔터티에 생성된 데이터베이스 테이블에 접근하는 메소드를
//사용하기 위한 인터페이스이다(findAll, save)
//CRUD(Create, Read, Update, Delete)를 정의하는 계층

//key는 사용할 dto? 혹은 엔터티를 넣어주면 된다.
//value에는 고유값 pk의 자료형을 적어주면 된다.
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	//기본적인 id나 name을 제외한 다른 column으로 무언가를 찾고 싶으면
	//findByxxxx 컬럼명을 적어주면 된다.
	Question findBySubject(String subject);
	
	
}
