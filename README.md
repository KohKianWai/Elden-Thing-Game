# FIT2099 Assignment (Semester 1, 2025)

```
`7MM"""YMM  `7MMF'      `7MM"""Yb. `7MM"""YMM  `7MN.   `7MF'    MMP""MM""YMM `7MMF'  `7MMF'`7MMF'`7MN.   `7MF' .g8"""bgd  
  MM    `7    MM          MM    `Yb. MM    `7    MMN.    M      P'   MM   `7   MM      MM    MM    MMN.    M .dP'     `M  
  MM   d      MM          MM     `Mb MM   d      M YMb   M           MM        MM      MM    MM    M YMb   M dM'       `  
  MMmmMM      MM          MM      MM MMmmMM      M  `MN. M           MM        MMmmmmmmMM    MM    M  `MN. M MM           
  MM   Y  ,   MM      ,   MM     ,MP MM   Y  ,   M   `MM.M           MM        MM      MM    MM    M   `MM.M MM.    `7MMF'
  MM     ,M   MM     ,M   MM    ,dP' MM     ,M   M     YMM           MM        MM      MM    MM    M     YMM `Mb.     MM  
.JMMmmmmMMM .JMMmmmmMMM .JMMmmmdP' .JMMmmmmMMM .JML.    YM         .JMML.    .JMML.  .JMML..JMML..JML.    YM   `"bmmmdPY  
```

[Contribution Log](https://docs.google.com/spreadsheets/d/1bvDhMj2gSG9Eo_L3SHJJwk9FKI8Z1Q4fOyCXxTyV2ME/edit?usp=sharing)


# Creative Mode: Realistic Farming System (REQ 3)

# Scenario:
The lands are harsh and unrelenting, plagued by the Crimson Rot that makes survival difficult.
Amid the chaos, the “Farmer” has discovered that a handful of resilient crops still have the potential to
grow — but only with patient and consistent watering.

To reclaim the land's fertility, the Farmer can plant and nurture special crops,
each requiring a different number of waterings before they can grow into full harvestable plants.

Carrot seed(c) needs to be watered 5 times to grow. A grown (mature) Carrot(C) can be harvested
, upon harvest, the farmer will gain a carrot item and consume 20 stamina points. When consumed,
the farmer will restore 15 hitpoints, and the farmer will gain a strong effect for 3 turns where
each turn the farmer will increase 5 stamina points.

Mushroom seed(m) needs to be watered 3 times to grow. A grown (mature) Mushroom(M) can be harvested.
Upon harvest, the farmer will gain a mushroom item and consume 10 stamina points. When consumed,
the farmer will be poisoned for 5 turns, each turn reduce the farmer hitpoints by 5.

Lettuce seed(l) needs to be watered 6 times to grow. A grown (mature) Lettuce(L) can be harvested.
Upon harvest, the farmer will gain a lettuce item and consume 25 stamina points. When consumed,
it restores the farmer's health by 10 and restores stamina points by 20. The farmer will also gain
a healing effect for 3 turns, each turn increase the farmer's health by 5.

All seeds can only be planted on soil(.) ground. The farmer will require a WateringCan to be able to
water the seeds. Note that the display character of the seed and its corresponding ground (sprout) is the same,
the display character of the mature plant and the plant item is also the same.

Testing Instruction:
To test the farming system, plant Carrot, Mushroom, and Lettuce seeds on Soil tiles and water them 
the required number of times (5, 3, and 6 respectively) using a WateringCan to trigger growth. Ensure 
seeds cannot be watered without the tool or on non-Soil ground. Once mature, harvest the crops and confirm 
correct stamina cost, item gain, and tile reset. Then consume each item to verify their effects: 
Carrot restores 15 HP and grants stamina-boosting StrongEffect, Mushroom inflicts PoisonEffect, 
and Lettuce restores both HP and stamina while granting a HealingEffect.

New low-level class: Mushroom, Carrot, Lettuce ground, each corresponding seed, each corresponding item
Interface: Waterable, Harvestable
High-level class: WateringCan

[Approved by Mogana and Chong (31/05/2025)]


# Creative Mode: Affection System (REQ 4)

# Scenario:

If an Human NPC(e.g. Guts, Sellen, MerchantKale) is nearby, the player will have the option to give an item to the Human NPC, and the 
Human NPC's affection level will change accordingly based on the item received.

Three new items will be added that affect affection levels:

- Flower (f) – A simple gift that adds 10 affection.
    
- Golden Necklace (!) – A valuable item that adds 15 affection.
    
- Rotten Flesh (/) – A repulsive item that decreases 25 affection.
  It can also be consumed by the player, reducing 20 stamina and 20 health.

Each Human NPC starts with an affection level of 30.

Depending on the affection level, Human NPCs will respond differently:

For Sellen:
If affection is 100, Every item Sellen sells will be free.
If affection is above 50, Sellen will offer the player a 50% discount on items.
If affection is below 10, Sellen will raise 50% price on items.
If affection is below 5, Sellen will refuse to sell items to the player.

For MerchantKale:
If affection is 100, Every item MerchantKale sells will be free.
If affection is above 80, MerchantKale will offer the player a 50% discount on items.
If affection is below 20, MerchantKale will raise 50% price on items.
If affection is below 10, MerchantKale will refuse to sell items to the player.

For Guts:
It will do nothing, Guts will still be angry.


Testing Instruction:
To test the affection system, move near the Human NPC and give item options should appear.
Give a Flower, Golden Necklace, or Rotten Flesh to the NPC, and the affection level will change accordingly (+10, +15, -25 respectively).

To test the discount, refusal to sell, or free item features, keep giving items to the NPC until the affection level reaches a certain range.
The price of items the NPC sells will change based on the affection level as described above.

New low-level class: FLower, Golden Necklace, Rotten Flesh
Interface: GivableItem
High-level class: GiveAction

[comments: you can add additional new features, NPC (merchant) will offer discount, you can add features, i.e., free weapons or rejecting the trade when affection is too low,etc.]

[Approved  by Mogana and Chong (31/05/2025)]