package ru.parsentev.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

	private int id;
	private User user;
	private String text;

}
