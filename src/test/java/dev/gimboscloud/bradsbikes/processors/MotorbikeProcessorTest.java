package dev.gimboscloud.bradsbikes.processors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dev.gimboscloud.bradsbikes.models.Motorbike;
import dev.gimboscloud.bradsbikes.services.MotorbikeService;

public class MotorbikeProcessorTest {
	
	private MotorbikeService motorbikeService = mock(MotorbikeService.class);
	private MotorbikeProcessor motorbikesProcessor;

	@Before
	public void setUp() throws Exception {
		motorbikesProcessor = new MotorbikeProcessor(motorbikeService);
	}

	@Test
	public void call_to_getMotorbikes_returns_iterable_of_motorbikes() {
		List<Motorbike> motorbikes = new ArrayList<>();
		motorbikes.add(new Motorbike());
		
		when(motorbikeService.getAll()).thenReturn(motorbikes);
		
		assertEquals(motorbikes, motorbikesProcessor.getMotorbikes());
		verify(motorbikeService, times(1)).getAll();
	}
	
	@Test
	public void call_to_getMotorbikeById_returns_motorbike() {
		Motorbike motorbike = new Motorbike();
		
		when(motorbikeService.getMotorbikeById(anyInt())).thenReturn(motorbike);
		
		assertEquals(motorbike, motorbikesProcessor.getMotorbikeById(1));
		verify(motorbikeService, times(1)).getMotorbikeById(1);
	}
	
	@Test
	public void call_to_updateMotorbike_calls_motorbikeService() {
		Motorbike motorbike = new Motorbike();
		
		motorbikesProcessor.updateMotorbike(motorbike);
		
		verify(motorbikeService, times(1)).save(motorbike);
	}

}
