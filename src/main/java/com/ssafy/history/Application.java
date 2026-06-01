package com.ssafy.history;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({
		"com.ssafy.history.board.mapper",
		"com.ssafy.history.history.mapper",
		"com.ssafy.history.quiz.mapper",
		"com.ssafy.history.region.mapper",
		"com.ssafy.history.trip.mapper"
})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
