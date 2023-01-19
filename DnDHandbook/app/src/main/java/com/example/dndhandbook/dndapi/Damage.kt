package com.example.dndhandbook.dndapi

class SpellDamage(
    val damage_type: DamageType,
    val damage_at_character_level: Map<String, String>?,
    val damage_at_slot_level: Map<String, String>?,
    val damage_dice: String?
)

class DamageType(
    val name: String,
    val desc: List<String>
)

class Damage (
    val damage_dice: String?,
    val damage_type: DamageType?
)

class DC(
    val dc_type: DCType,
    val dc_value: Int,
    val dc_success: String
) {
    class DCType(
        val name: String
    )
}

class AreaOfEffect(
    val type: String,
    val size: Int
)