package com.aarshinkov.thesheriff.services;

import com.aarshinkov.thesheriff.domain.CardContainer;
import com.aarshinkov.thesheriff.domain.game.*;
import com.aarshinkov.thesheriff.domain.game.total.KingsAndQueens;
import com.aarshinkov.thesheriff.domain.game.total.LegalCardType;
import java.util.List;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public interface CalculatorService {

  TotalResult calculateTotalResult( List<PlayerResult> playersResult);
  
  KingsAndQueens calculateCardsKingsAndQueens(LegalCardType legalCardType, List<CardContainer>... playersCards);
}
