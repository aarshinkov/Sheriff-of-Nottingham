package com.aarshinkov.thesheriff.services;

import com.aarshinkov.thesheriff.domain.*;
import java.util.*;
import lombok.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService
{
  @Override
  public Integer calculateStockMoney(List<CardContainer> cards)
  {
    Integer total = 0;
    
    for (CardContainer card : cards)
    {
      if (card.getCount() == null) {
        card.setCount(0);
      }
      total += (card.getCount() * card.getCard().getPrice());
    }
    
    return total;
  }
}
