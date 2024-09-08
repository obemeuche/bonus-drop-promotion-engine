package com.example.demo.service;

import com.example.demo.dto.BonusPlayResult;
import com.example.demo.dto.PlayerChoice;

public interface BonusDropEngine {
    BonusPlayResult calculateBonus(PlayerChoice choice);
}
