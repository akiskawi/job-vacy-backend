package com.manpower.backendProject;

import com.manpower.backendProject.emailSender.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class BackendProjectApplication {


	public static void main(String[] args) {
		SpringApplication.run(BackendProjectApplication.class, args);
	}



}
