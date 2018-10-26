package com.hbjoe.data;

import java.util.List;

import com.hbjoe.Spittle;

public interface SpittleRepository {
	List<Spittle> findSpittles(long max, int count);
	Spittle findOne(long id);
}
