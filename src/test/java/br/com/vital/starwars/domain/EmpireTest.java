package br.com.vital.starwars.domain;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.vital.starwars.builder.PlanetBuilder;
import br.com.vital.starwars.exception.PlanetNotFoundException;
import br.com.vital.starwars.repository.PlanetRepository;

@RunWith(MockitoJUnitRunner.class)
public class EmpireTest {
	
	@InjectMocks
	public Empire empire;
	
	@Mock
	private PlanetRepository planetRepository;
	
	@Test
	public void insertTest() {
		Planet planet = PlanetBuilder.create().build();
		empire.save(planet);
		
		Mockito.verify(planetRepository).insert(Mockito.any());
	}
	
	@Test
	public void updateTest() {
		Planet planet = PlanetBuilder.create().withID("09890sd7975298903").build();
		
		Mockito.when(planetRepository.findById(Mockito.anyString())).thenReturn(new Planet());
		
		empire.save(planet);
		
		Mockito.verify(planetRepository).save(planet);
	}
	
	@Test
	public void planetNotFoundTest() {
		Planet planet = PlanetBuilder.create().build();
		
		try {
			empire.save(planet);
		} catch(PlanetNotFoundException e) {
			Assert.assertThat(e.getCode(), CoreMatchers.is("FIND_0040"));
			Assert.assertThat(e.getMessage(), CoreMatchers.is("Planet not found"));
		}
	}
	
	@Test
	public void removeTest() {
		empire.remove(Mockito.any());
		
		Mockito.verify(planetRepository).remove(Mockito.any());
	}
	
	@Test
	public void listAllSavedTest() {
		List<Planet> resultPlanets = Arrays.asList(PlanetBuilder.create().withName("Alderaan").build(), 
				PlanetBuilder.create().withName("Yavin IV").build(), 
				PlanetBuilder.create().withName("Hoth").build());
		
		Mockito.when(planetRepository.listAllSaved()).thenReturn(resultPlanets);
		
		List<Planet> planets = empire.listAllSaved();
		
		Assert.assertThat(planets.size(), CoreMatchers.is(3));
	}
	
	
	@Test
	public void listAllTest() {
		List<Planet> resultPlanets = Arrays.asList(PlanetBuilder.create().withName("Kamino").build(), 
				PlanetBuilder.create().withName("Utapau").build(), 
				PlanetBuilder.create().withName("Kashyyyk").build());
		
		Mockito.when(planetRepository.listAll()).thenReturn(resultPlanets);
		
		List<Planet> planets = empire.listAll();
		
		Assert.assertThat(planets.size(), CoreMatchers.is(3));
	}
 
}
