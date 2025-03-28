package dev.burrnr.revenant.command

import org.bukkit.Material
import org.bukkit.entity.ItemDisplay
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class TrialAbility {
	fun execute(player: Player) {
		val arm = player.world.spawn(player.location, ItemDisplay::class.java)
		arm.setItemStack(ItemStack.of(Material.CLOCK))
	}
}