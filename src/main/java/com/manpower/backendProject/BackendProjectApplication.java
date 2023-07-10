package com.manpower.backendProject;

import com.manpower.backendProject.emailSender.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@RequiredArgsConstructor
public class BackendProjectApplication {

	private final EmailSender emailSender;

	public static void main(String[] args) {
		SpringApplication.run(BackendProjectApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendEmail(){
		emailSender.sendEmail("akiskawi@gmail.com",
							"This is the subject",
							"This is the body of email");
	}

}
