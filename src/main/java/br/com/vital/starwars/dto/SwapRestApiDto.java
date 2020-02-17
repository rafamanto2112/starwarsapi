package br.com.vital.starwars.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SwapRestApiDto {
	private Integer count;
	private String next;
	private String previous;
	private List<SwapPlanetDto> results;
	

	

}
