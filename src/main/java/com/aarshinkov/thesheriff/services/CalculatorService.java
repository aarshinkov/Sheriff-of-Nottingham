package com.aarshinkov.thesheriff.services;

import com.aarshinkov.thesheriff.domain.*;
import java.util.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public interface CalculatorService {

  Integer calculateStockMoney(List<CardContainer> cards);
}
