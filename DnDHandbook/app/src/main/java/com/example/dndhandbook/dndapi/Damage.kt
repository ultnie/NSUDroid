package com.example.dndhandbook.dndapi

class SpellDamage(
    val damage_type: DamageType,
    val damage_at_character_level: Map<String, String>?,
    val damage_at_slot_level: Map<String, String>?,
    val damage_dice: String?
): java.io.Serializable

class DamageType(
    val name: String,
    val desc: List<String>
): java.io.Serializable

class Damage (
    val damage_dice: String?,
    val damage_type: DamageType?
): java.io.Serializable

class DC(
    val dc_type: DCType,
    val dc_value: Int,
    val dc_success: String
): java.io.Serializable {
    class DCType(
        val name: String
    ): java.io.Serializable
}

class AreaOfEffect(
    val type: String,
    val size: Int
): java.io.Serializable