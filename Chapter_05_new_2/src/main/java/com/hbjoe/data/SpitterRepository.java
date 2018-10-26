package com.hbjoe.data;

import com.hbjoe.Spitter;

public interface SpitterRepository {

  Spitter save(Spitter spitter);
  
  Spitter findByUsername(String username);

}
