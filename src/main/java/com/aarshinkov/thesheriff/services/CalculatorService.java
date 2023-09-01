package com.aarshinkov.thesheriff.services;

import com.aarshinkov.thesheriff.domain.*;
import java.util.*;

public interface CalculatorService
{
  Integer calculateStockMoney(List<CardContainer> cards);
}
