package dev.burrnr.revenant.relic

import dev.burrnr.revenant.remnant.RemnantType
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

open class Relic {
	open val type: RelicType = RelicType.NONE
	open var remnants: List<RemnantType> = emptyList()

	fun asItem(): ItemStack {
		val item = ItemStack.of(type.getMaterial())
		val meta = item.itemMeta
		meta.displayName(type.relicName())
		meta.lore(type.relicLore())
		val modelDataString: StringBuilder = StringBuilder()
		remnants.forEach {
			modelDataString.append(it.ordinal)
		}
		meta.setCustomModelData(modelDataString.toString().toInt())

		item.itemMeta = meta
		return item
	}

	enum class RelicType {
		NONE,
		SWORD,
		BOW,
		SHIELD;

		fun getMaterial(): Material {
			return when (this) {
				NONE -> Material.CLOCK
				SWORD -> Material.NETHERITE_SWORD
				BOW -> Material.BOW
				SHIELD -> Material.SHIELD
			}
		}

		fun relicName(): Component {
			return when (this) {
				NONE -> Component.text("None")
				SWORD -> Component.text("Severance", TextColor.color(0xc92d22)).decoration(TextDecoration.ITALIC, false)
				BOW -> Component.text("Widowmaker", TextColor.color(0xc92d22)).decoration(TextDecoration.ITALIC, false)
				SHIELD -> Component.text("The Last Grasp", TextColor.color(0xc92d22)).decoration(TextDecoration.ITALIC, false)
			}
		}

		fun relicLore(): List<Component> {
			return when (this) {
				NONE -> listOf(Component.text("No relic"))
				SWORD -> listOf(Component.text("A blade forged in desperation, reforged", TextColor.color(0xc92d22)),
					Component.text("in the blood of the fallen.", TextColor.color(0xc92d22)))
				BOW -> listOf()
				SHIELD -> listOf()
			}
		}
	}
}