package com.exe.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


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
