package ru.krotov.models;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

	private int id;
	private String name;

}
