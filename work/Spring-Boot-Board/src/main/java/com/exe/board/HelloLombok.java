package com.exe.board;

import com.exe.board.question.Question;
import com.exe.board.user.SiteUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;


@RequiredArgsConstructor
@Getter
@Setter
public class HelloLombok {

	private final String name;
	private final int age;

	/**
	public static void main(String[] args) {
		HelloLombok hk = new HelloLombok("유인나", 40);

//		hk.setName("배수지");
//		hk.setAge(27);

		System.out.println(hk.getName());
		System.out.println(hk.getAge());


	}
	 * 
	 * @param args
	 */


}
