package dev.burrnr.revenant.remnant

import dev.burrnr.revenant.RevenantPlugin
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack

open class Remnant: Listener {
	val remnantType: RemnantType = RemnantType.NONE
	val plugin = RevenantPlugin.instance

	open fun init() {
		plugin.server.pluginManager.registerEvents(this, plugin)
	}

	fun getRemnantsFromRelic(item: ItemStack): List<RemnantType> {
		if (item.itemMeta == null) return listOf(RemnantType.NONE)
		if (!item.itemMeta.hasCustomModelData()) return listOf(RemnantType.NONE)
		val modelData = item.itemMeta.customModelData
		val modelDataString = modelData.toString()
		return modelDataString.map { RemnantType.entries[it.digitToInt()] }
	}
}