package com.example.dndhandbook.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.dndhandbook.R
import com.example.dndhandbook.dndapi.MIResultResponse
import com.example.dndhandbook.dndapi.MonsterResultResponse
import com.example.dndhandbook.dndapi.SpellResultResponse
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class ResultFragment() : Fragment() {

    private lateinit var tv: TextView
    private lateinit var ntv: TextView
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        tv = view.findViewById(R.id.resultTextView) as TextView
        ntv = view.findViewById(R.id.resultNameTextView) as TextView
        image = view.findViewById(R.id.resImage) as ImageView
        var text = String()
        var name = String()
        val bundle = arguments
        val result = (bundle!!.getSerializable("res") as ArrayList<*>)[0]

        Log.d("ResFrag", result.toString())
        when (result) {
            is SpellResultResponse -> {
            name = result.name + if (result.ritual) {
                buildString {
                    append("(")
                    append(getString(R.string.ritual))
                    append(")")
                }
            } else {""}
            text =
                buildString {
                    append(result.level.toString())
                    append(buildString {
                        append(" ")
                        append(getString(R.string.level))
                        append(" ")
                    })
                    append(result.school.name.lowercase())
                    append(
                        if (!result.material.isNullOrEmpty()) {
                            buildString {
                                append("\n")
                                append(getString(R.string.materials))
                                append(": ")
                                append(result.material)
                            }
                        } else {
                            ""
                        }
                    )
                    append("\n" + getString(R.string.components) + ": ")
                    append(result.components)
                    append(
                        if (result.concentration) {
                            buildString {
                                append("\n")
                                append(getString(R.string.concentration))
                            }
                        } else {
                            ""
                        }
                    )
                    append("\n" + getString(R.string.casting_time) + ": ")
                    append(result.casting_time)
                    append("\n" + getString(R.string.range) + ": ")
                    append(result.range)
                    append(
                        if (result.area_of_effect != null) {
                            buildString {
                                append("\n")
                                append(getString(R.string.area))
                                append(": ")
                                append(result.area_of_effect.size)
                                append(" ")
                                append(getString(R.string.feet))
                                append(" ")
                                append(result.area_of_effect.type)
                            }
                        } else {
                            ""
                        }
                    )
                    append(buildString {
                        append("\n")
                        append(getString(R.string.duration))
                        append(": ")
                    })
                    append(result.duration)
                }

            if (result.dc != null) {
                text += buildString {
                    append("\n")
                    append(getString(R.string.dc))
                    append(": ")
                    append(result.dc.dc_type.name)
                    append("; ")
                    append(
                        if (result.dc.dc_success == getString(R.string.half)) {
                            buildString {
                                append(getString(R.string.half_damage))
                                append(" ")
                                append(getString(R.string.on_success))
                            }
                        } else if (result.dc.dc_success == getString(R.string.none) && result.damage != null
                        ) {
                            buildString {
                                append(getString(R.string.no_damage))
                                append(" ")
                                append(getString(R.string.on_success))
                            }
                        } else if (result.dc.dc_success == getString(R.string.none) && result.damage != null) {
                            buildString {
                                append(getString(R.string.no_effect))
                                append(" ")
                                append(getString(R.string.on_success))
                            }
                        } else {
                            ""
                        }
                    )
                }
            }

            if (result.damage != null) {
                if (!result.damage.damage_dice.isNullOrEmpty()) {
                    text += buildString {
                        append("\n")
                        append(getString(R.string.damage))
                        append(": ")
                    }
                    text += buildString {
                        append(result.damage.damage_dice)
                        append(" ")
                        append(result.damage.damage_type.name.lowercase())
                        append(getString(R.string.damage).lowercase())
                    }
                } else if (result.damage.damage_at_slot_level != null) {
                    text += buildString {
                        append("\n\n")
                        append(getString(R.string.damage))
                        append(" ")
                        append(getString(R.string.at_slot_level))
                        append(": ")
                    }
                    for (me in result.damage.damage_at_slot_level.keys) {
                        text += buildString {
                            append("\n")
                            append(me)
                            append(" ")
                            append(getString(R.string.level))
                            append(": ")
                            append(result.damage.damage_at_slot_level[me])
                        }
                    }
                } else if (result.damage.damage_at_character_level != null) {
                    text += buildString {
                        append("\n\n")
                        append(getString(R.string.damage))
                        append(getString(R.string.at_character_level))
                        append(": ")
                    }
                    for (me in result.damage.damage_at_character_level.keys) {
                        text += buildString {
                            append("\n")
                            append(me)
                            append(" ")
                            append(getString(R.string.damage))
                            append(": ")
                            append(result.damage.damage_at_character_level[me])
                        }
                    }
                }
            }

            if (result.desc != null) {
                text += buildString {
                    append("\n\n")
                    append(result.desc[0])
                    append("\n")
                }
            }

            text += buildString {
                append("\n\n")
                append(getString(R.string.available_for))
                append(": ")
            }
            for (c in result.classes) {
                text += buildString {
                    append("\n")
                    append(c.name)
                }
            }
        }
            is MonsterResultResponse -> {
            name = result.name
            text =
                buildString {
                    append("Ability Scores")
                    append(": ")
                    append("\n")
                    append(getString(R.string.STR))
                    append(": ")
                    append(result.strength)
                    append(" ")
                    append(getString(R.string.DEX))
                    append(": ")
                    append(result.dexterity)
                    append(" ")
                    append(getString(R.string.CON))
                    append(": ")
                    append(result.constitution)
                    append(" ")
                    append(getString(R.string.INT))
                    append(": ")
                    append(result.intelligence)
                    append(" ")
                    append(getString(R.string.WIS))
                    append(": ")
                    append(result.wisdom)
                    append(" ")
                    append(getString(R.string.CHA))
                    append(": ")
                    append(result.charisma)
                }
            text += buildString {
                append("\n\n")
                append(result.size)
                append(" ")
                append(result.type)
            }
            text += buildString {
                append("\n")
                append(result.alignment)
            }
            text += buildString {
                append("\n")
                append(getString(R.string.AC))
                append(": ")
                append(result.armor_class)
                append(" ")
                append(getString(R.string.HP))
                append(": ")
                append(result.hit_points)
            }
            text += buildString {
                append("\n")
                append(getString(R.string.hit_dice))
                append(": ")
                append(result.hit_dice)
            }
            text += buildString {
                append("\n")
                append(getString(R.string.CR))
                append(": ")
                append(result.challenge_rating.toString())
                append(" ")
                append(getString(R.string.XP))
                append(": ")
                append(result.xp)
            }
            if (result.speed != null) {
                if (result.speed.walk != null) {
                    text += buildString {
                        append("\n")
                        append(getString(R.string.walking_speed))
                        append(": ")
                        append(result.speed.walk)
                    }
                }
                if (result.speed.climb != null) {
                    text += buildString {
                        append("\n")
                        append(getString(R.string.climbing_speed))
                        append(": ")
                        append(result.speed.climb)
                    }
                }
                if (result.speed.fly != null) {
                    text += buildString {
                        append("\n")
                        append(getString(R.string.flying_speed))
                        append(": ")
                        append(result.speed.fly)
                    }
                }
                if (result.speed.swim != null) {
                    text += buildString {
                        append("\n")
                        append(getString(R.string.swimming_speed))
                        append(": ")
                        append(result.speed.swim)
                    }
                }
                if (result.speed.burrow != null) {
                    text += buildString {
                        append("\n")
                        append(getString(R.string.burrowing_speed))
                        append(": ")
                        append(result.speed.burrow)
                    }
                }
                text += "\n"
            }
            if (result.senses != null) {
                text += buildString {
                    append(getString(R.string.senses))
                    append(": ")
                }
                text += buildString {
                    append("\n")
                    append(getString(R.string.passive_perception))
                    append(": ")
                    append(result.senses.passive_perception)
                }
                if (result.senses.blindsight != null) {
                    text += buildString {
                        append("\n")
                        append(getString(R.string.blindsight))
                        append(": ")
                        append(result.senses.blindsight)
                    }
                }
                if (result.senses.darkvision != null) {
                    text += buildString {
                        append("\n")
                        append(getString(R.string.darkvision))
                        append(": ")
                        append(result.senses.darkvision)
                    }
                }
                if (result.senses.tremorsense != null) {
                    text += buildString {
                        append("\n")
                        append(getString(R.string.tremorsense))
                        append(": ")
                        append(result.senses.tremorsense)
                    }
                }
                if (result.senses.truesight != null) {
                    text += buildString {
                        append("\n")
                        append(getString(R.string.truesight))
                        append(": ")
                        append(result.senses.truesight)
                    }
                }
                text += "\n"
            }
            if (result.languages != null) {
                text += buildString {
                    append("\n")
                    append(getString(R.string.languages))
                    append(": ")
                    append(result.languages)
                }
            }
            if (result.special_abilities != null) {
                text += buildString {
                    append("\n")
                    append(getString(R.string.special_abilities))
                    append(": ")
                }
                for (ability in result.special_abilities) {
                    text += "\n" + ability.name
                    if(ability.attack_bonus != null) {
                        text += buildString {
                            append("\n")
                            append(getString(R.string.to_hit))
                            append(": ")
                            append(if(ability.attack_bonus > 0) {"+"} else {""})
                            append(ability.attack_bonus)
                        }
                    }
                    if(ability.dc != null) {
                        text += buildString {
                            append("\n")
                            append(getString(R.string.dc))
                            append(": ")
                            append(ability.dc.dc_type.name)
                            append(" ")
                            append(ability.dc.dc_value)
                            append("; ")
                            append(
                                if (ability.dc.dc_success == getString(R.string.half)) {
                                    buildString {
                                        append(getString(R.string.half_damage))
                                        append(" ")
                                        append(getString(R.string.on_success))
                                    }
                                } else if (ability.dc.dc_success == getString(R.string.none) && ability.damage != null) {
                                    buildString {
                                        append(getString(R.string.no_damage))
                                        append(" ")
                                        append(getString(R.string.on_success))
                                    }
                                } else if (ability.dc.dc_success == getString(R.string.none) && ability.damage != null) {
                                    buildString {
                                        append(getString(R.string.no_effect))
                                        append(" ")
                                        append(getString(R.string.on_success))
                                    }
                                } else {
                                    ""
                                }
                            )
                        }
                    }
                    if(ability.damage != null) {
                        text += buildString {
                            append("\n")
                            append(getString(R.string.damage))
                            append(": ")
                            append(ability.damage.damage_dice)
                            append(" ")
                            append(ability.damage.damage_type?.name?.lowercase())
                            append(" ")
                            append(getString(R.string.damage).lowercase())
                            append(" ")
                        }
                    }
                    if (ability.usage != null) {
                        text += buildString {
                            append("\n")
                            append(getString(R.string.usage))
                            append(": ")
                        }
                        if (ability.usage.type != null) {
                            text += buildString {
                                append(ability.usage.type)
                                append(" ")
                            }
                        }
                        if (ability.usage.times != null) {
                            text += ability.usage.times.toString()
                        }
                        if (ability.usage.rest_types != null) {
                            text += buildString {
                                append("\n")
                                append(getString(R.string.restore_on))
                                append(": ")
                            }
                            if (ability.usage.rest_types.isNotEmpty()) {
                                for (rest in ability.usage.rest_types) {
                                    text += buildString {
                                        append("\n")
                                        append(rest)
                                    }
                                }
                            } else {
                                text += buildString {
                                    append("\n")
                                    append(getString(R.string.at_dawn))
                                }
                            }
                        }
                    }
                    if (ability.desc != null) {
                        text += "\n\n" + ability.desc + "\n"
                    }
                    if(ability.spellcasting != null) {
                        text += buildString {
                            append("\n")
                            append(getString(R.string.spellcasting_ability))
                            append(": ")
                            append(ability.spellcasting.ability?.name)
                        }
                        if (ability.spellcasting.dc != null) {
                            text += "\n" + getString(R.string.dc) + ":" + ability.spellcasting.dc
                        }
                        if (ability.spellcasting.modifier != null) {
                            text += buildString {
                                append("\n")
                                append(getString(R.string.spellcasting_modifier))
                                append(": ")
                                append(ability.spellcasting.modifier)
                            }
                        }
                        if (ability.spellcasting.components_required != null) {
                            text += buildString {
                                append("\n")
                                append(getString(R.string.components))
                                append(": ")
                                append(ability.spellcasting.components_required.toString())
                            }
                        }
                        if (ability.spellcasting.school != null) {
                            text += buildString {
                                append("\n")
                                append(getString(R.string.school))
                                append(": ")
                                append(ability.spellcasting.school)
                            }
                        }
                        if (ability.spellcasting.slots != null) {
                            text += buildString {
                                append("\n\n")
                                append(getString(R.string.slots))
                                append(": ")
                            }
                            for (me in ability.spellcasting.slots.keys) {
                                text += buildString {
                                    append("\n")
                                    append(me)
                                    append(": ")
                                    append(ability.spellcasting.slots[me])
                                }
                            }
                            text += "\n"
                        }
                    }
                }
            }
            if (result.proficiencies != null) {
                text += buildString {
                    append("\n")
                    append(getString(R.string.proficiencies))
                    append(": ")
                }
                for (prof in result.proficiencies) {
                    text += buildString {
                        append("\n")
                        append(prof.proficiency.name)
                        append(": ")
                        append(prof.value)
                    }
                }
                text += "\n"
            }
            if (result.condition_immunities != null && result.condition_immunities.isNotEmpty()) {
                text += buildString {
                    append("\n")
                    append(getString(R.string.condition_immunities))
                    append(": ")
                }
                for (condition in result.condition_immunities) {
                    text += buildString {
                        append("\n")
                        append(condition.name)
                    }
                }
                text += "\n"
            }
            if (result.damage_immunities != null && result.damage_immunities.isNotEmpty()) {
                text += buildString {
                    append("\n")
                    append(getString(R.string.damage_immunities))
                    append(": ")
                }
                for (damage in result.damage_immunities) {
                    text += buildString {
                        append("\n")
                        append(damage)
                    }
                }
                text += "\n"
            }
            if (result.damage_resistances != null && result.damage_resistances.isNotEmpty()) {
                text += buildString {
                    append("\n")
                    append(getString(R.string.damage_resistances))
                    append(": ")
                }
                for (damage in result.damage_resistances) {
                    text += buildString {
                        append("\n")
                        append(damage)
                    }
                }
                text += "\n"
            }
            if (result.damage_vulnerabilities != null && result.damage_vulnerabilities.isNotEmpty()) {
                text += buildString {
                    append("\n")
                    append(getString(R.string.damage_vulnerabilities))
                    append(": ")
                }
                for (damage in result.damage_vulnerabilities) {
                    text += buildString {
                        append("\n")
                        append(damage)
                    }
                }
                text += "\n"
            }
            if (result.actions != null && result.actions.isNotEmpty()) {
                text += buildString {
                    append("\n\n")
                    append(getString(R.string.actions))
                    append(": ")
                }
                for (action in result.actions) {
                    text += buildString {
                        append("\n\n")
                        append(action.name)
                    }
                    if (action.attack_bonus != null) {
                        text += buildString {
                            append("\n")
                            append(getString(R.string.to_hit))
                            append(": ")
                            append(
                                if (action.attack_bonus >= 0) {
                                    "+"
                                } else {
                                    ""
                                }
                            )
                            append(action.attack_bonus)
                        }
                    }
                    if (action.damage != null) {
                        text += buildString {
                            append("\n")
                            append(getString(R.string.damage))
                            append(": ")
                        }
                        for (damage in action.damage) {
                            text += buildString {
                                append(damage.damage_dice)
                                append(" ")
                                append(damage.damage_type?.name?.lowercase())
                                append(" ")
                                append(getString(R.string.damage).lowercase())
                                append(" ")
                            }
                        }
                    }
                    if (action.dc != null) {
                        text += buildString {
                            append("\n")
                            append(getString(R.string.dc))
                            append(": ")
                            append(action.dc.dc_type.name)
                            append(" ")
                            append(action.dc.dc_value)
                            append("; ")
                            append(
                                if (action.dc.dc_success == getString(R.string.half)) {
                                    buildString {
                                        append(getString(R.string.half_damage))
                                        append(" ")
                                        append(getString(R.string.on_success))
                                    }
                                } else if (action.dc.dc_success == getString(R.string.none) && action.damage != null) {
                                    buildString {
                                        append(getString(R.string.no_damage))
                                        append(" ")
                                        append(getString(R.string.on_success))
                                    }
                                } else if (action.dc.dc_success == getString(R.string.none) && action.damage != null) {
                                    buildString {
                                        append(getString(R.string.no_effect))
                                        append(" ")
                                        append(getString(R.string.on_success))
                                    }
                                } else {
                                    ""
                                }
                            )
                        }
                    }
                    if (action.desc != null) text += buildString {
                        append("\n")
                        append(action.desc)
                        append("\n")
                    }
                }
            }
            if (result.reactions != null) {
                text += buildString {
                    append("\n\n")
                    append(getString(R.string.reactions))
                    append(": ")
                }
                for (action in result.reactions) {
                    text += "\n\n" + action.name
                    if (action.attack_bonus != null) {
                        text += buildString {
                            append("\n")
                            append(getString(R.string.to_hit))
                            append(": ")
                            append(
                                if (action.attack_bonus >= 0) {
                                    "+"
                                } else {
                                    ""
                                }
                            )
                            append(action.attack_bonus)
                        }
                    }
                    if (action.damage != null) {
                        text += buildString {
                            append("\n")
                            append(getString(R.string.damage))
                            append(": ")
                        }
                        for (damage in action.damage) {
                            text += buildString {
                                append(damage.damage_dice)
                                append(" ")
                                append(damage.damage_type?.name?.lowercase())
                                append(" damage ")
                            }
                        }
                    }
                    if (action.dc != null) {
                        text += buildString {
                            append("\n")
                            append(getString(R.string.dc))
                            append(": ")
                            append(action.dc.dc_type.name)
                            append(" ")
                            append(action.dc.dc_value)
                            append("; ")
                            append(
                                if (action.dc.dc_success == getString(R.string.half)) {
                                    buildString {
                                        append(getString(R.string.half_damage))
                                        append(" ")
                                        append(getString(R.string.on_success))
                                    }
                                } else if (action.dc.dc_success == getString(R.string.none) && action.damage != null) {
                                    buildString {
                                        append(getString(R.string.no_damage))
                                        append(" ")
                                        append(getString(R.string.on_success))
                                    }
                                } else if (action.dc.dc_success == getString(R.string.none) && action.damage != null) {
                                    buildString {
                                        append(getString(R.string.no_effect))
                                        append(" ")
                                        append(getString(R.string.on_success))
                                    }
                                } else {
                                    ""
                                }
                            )
                        }
                    }
                    if (action.desc != null) text += buildString {
                        append("\n")
                        append(action.desc)
                        append("\n")
                    }
                }
            }
            if (result.legendary_actions != null && result.legendary_actions.isNotEmpty()) {
                text += buildString {
                    append("\n\n")
                    append(getString(R.string.legendary_actions))
                    append(": ")
                }
                for (action in result.legendary_actions) {
                    text += buildString {
                        append("\n\n")
                        append(action.name)
                    }
                    if (action.attack_bonus != null) {
                        text += buildString {
                            append("\n")
                            append(getString(R.string.to_hit))
                            append(": ")
                            append(
                                if (action.attack_bonus >= 0) {
                                    "+"
                                } else {
                                    ""
                                }
                            )
                            append(action.attack_bonus)
                        }
                    }
                    if (action.damage != null) {
                        text += buildString {
                            append("\n")
                            append(getString(R.string.damage))
                            append(": ")
                        }
                        for (damage in action.damage) {
                            text += buildString {
                                append(damage.damage_dice)
                                append(" ")
                                append(damage.damage_type?.name?.lowercase())
                                append(" ")
                                append(getString(R.string.damage).lowercase())
                                append(" ")
                            }
                        }
                    }
                    if (action.dc != null) {
                        text += buildString {
                            append("\n")
                            append(getString(R.string.dc))
                            append(": ")
                            append(action.dc.dc_type.name)
                            append(" ")
                            append(action.dc.dc_value)
                            append("; ")
                            append(
                                if (action.dc.dc_success == getString(R.string.half)) {
                                    buildString {
                                        append(getString(R.string.half_damage))
                                        append(" ")
                                        append(getString(R.string.on_success))
                                    }
                                } else if (action.dc.dc_success == getString(R.string.none) && action.damage != null) {
                                    buildString {
                                        append(getString(R.string.no_damage))
                                        append(" ")
                                        append(getString(R.string.on_success))
                                    }
                                } else if (action.dc.dc_success == getString(R.string.none) && action.damage != null) {
                                    buildString {
                                        append(getString(R.string.no_effect))
                                        append(" ")
                                        append(getString(R.string.on_success))
                                    }
                                } else {
                                    ""
                                }
                            )
                        }
                    }
                    if (action.desc != null) text += buildString {
                        append("\n")
                        append(action.desc)
                        append("\n")
                    }
                }
            }

            if (result.desc != null) text += buildString {
                append("\n\n")
                append(result.desc)
                append("\n")
            }

            Picasso.get().load(getString(R.string.url)+result.image).into(image)
        }
            is MIResultResponse -> {
                name = buildString {
                    append(result.name)
                    append("\n(")
                    append(result.rarity?.name ?: "")
                    append(")")
                }
                if (!result.desc.isNullOrEmpty()) {
                    for (str in result.desc) {
                        text += buildString {
                            append(str)
                            append("\n\n")
                        }
                    }
                }
            }
        }
        ntv.text = name
        tv.text = text
        return view
    }
}