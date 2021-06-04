package com.formaschool.back.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Message {

	@Id
	private String id;

	@DBRef
	private Member sender;
	@DBRef
	private Salon salon;
	@NonNull
	private String content;
	private String file;
	private LocalDate send = LocalDate.now();
	private LocalDate edit;
}
