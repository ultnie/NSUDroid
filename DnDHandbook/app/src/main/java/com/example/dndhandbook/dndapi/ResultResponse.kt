package com.example.dndhandbook.dndapi

class SpellResultResponse (
    val name: String,
    val desc: List<String>?,
    val range: String,
    val components: List<String>,
    val material: String?,
    val ritual: Boolean,
    val duration: String,
    val concentration: Boolean,
    val casting_time: String,
    val level: Int,
    val school: School,
    val classes: List<Class>,
    val area_of_effect: AreaOfEffect?,
    val damage: SpellDamage?,
    val dc: DC?
): java.io.Serializable {

    class School(val name: String): java.io.Serializable
    class Class(val name: String): java.io.Serializable
}

class MonsterResultResponse(
    val name: String,
    val desc: String?,
    val strength: Int,
    val dexterity: Int,
    val constitution: Int,
    val intelligence: Int,
    val wisdom: Int,
    val charisma: Int,
    val size: String,
    val type: String,
    val alignment: String,
    val armor_class: Int,
    val hit_points: Int,
    val hit_dice: String,
    val actions: List<DnDAction>?,
    val legendary_actions: List<DnDAction>?,
    val challenge_rating: Double,
    val condition_immunities: List<DnDCondition>?,
    val damage_immunities: List<String>?,
    val damage_resistances: List<String>?,
    val damage_vulnerabilities: List<String>?,
    val languages: String?,
    val proficiencies: List<DnDProficiency>?,
    val reactions: List<DnDAction>?,
    val senses: DnDSenses?,
    val special_abilities: List<DnDSpecialAbilities>?,
    val speed: DnDSpeed?,
    val xp: Int,
    val image: String?
): java.io.Serializable {

    class DnDAction(
        val name: String,
        val desc: String?,
        val attack_bonus: Int?,
        val dc: DC?,
        val damage: List<Damage>?
    ): java.io.Serializable

    class DnDCondition(val name: String): java.io.Serializable
    class DnDProficiency(
        val value: Int,
        val proficiency: DnDSubproficiency
    ): java.io.Serializable {

        class DnDSubproficiency(val name: String): java.io.Serializable
    }

    class DnDSenses(
        val passive_perception: Int,
        val blindsight: String?,
        val darkvision: String?,
        val tremorsense: String?,
        val truesight: String?
    ): java.io.Serializable

    class DnDSpecialAbilities(
        val name: String,
        val desc: String?,
        val attack_bonus: Int?,
        val damage: Damage?,
        val dc: DC?,
        val spellcasting: DnDSpellcasting?,
        val usage: DnDUsage?
    ): java.io.Serializable {

        class DnDSpellcasting(
            val ability: DnDAbility?,
            val dc: Int?,
            val modifier: Int?,
            val components_required: List<String>?,
            val school: String?,
            val slots: Map<String, Int>?,
        ): java.io.Serializable

        class DnDUsage(
            val type: String?,
            val rest_types: List<String>?,
            val times: Int?
        ): java.io.Serializable
    }

    class DnDSpeed(
        val walk: String?,
        val burrow: String?,
        val climb: String?,
        val fly: String?,
        val swim: String?
    ): java.io.Serializable
}

class MIResultResponse(
    val name: String,
    val desc: List<String>?,
    val equipment_category: DnDEquipmentCategory?,
    val rarity: DnDRarity?
    ): java.io.Serializable {
    class DnDRarity (val name: String): java.io.Serializable
}

class DnDAbility(val name: String): java.io.Serializable
class DnDEquipmentCategory (val name: String): java.io.Serializable