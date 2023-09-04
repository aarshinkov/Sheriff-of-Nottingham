package com.aarshinkov.thesheriff.controllers;

import com.aarshinkov.thesheriff.base.Base;
import com.aarshinkov.thesheriff.memory.Memory;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
public class MemoryController extends Base {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private final Memory memory;

  @GetMapping(value = "/memory", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> readMemory() {

    log.debug("Reading objects, stored in the memory");

    Map<String, Object> storedObjects = memory.getStoredObjects();

    return ResponseEntity.ok(storedObjects);
  }

  @GetMapping(value = "/clearMemory", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> clearMemory() {

    return ResponseEntity.ok(memory.clearMemory());
  }
}
