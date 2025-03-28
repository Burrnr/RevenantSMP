package dev.burrnr.revenant.remnant

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffectType
import org.bukkit.scheduler.BukkitRunnable

class Shade: Remnant() {
	val timeSinceLastHit = hashMapOf<Player, Long>()

	@EventHandler
	fun onHit(event: EntityDamageByEntityEvent) {
		if (event.damager !is Player) return
		val player = event.damager as Player
		val remnants = getRemnantsFromRelic(player.inventory.itemInMainHand)
		if (!remnants.contains(RemnantType.SHADE)) return
		val shadeCount = remnants.count { it == RemnantType.SHADE }
		timeSinceLastHit[event.damager as Player] = System.currentTimeMillis()
	}

	override fun init() {
		object: BukkitRunnable() {
			override fun run() {
				Bukkit.getOnlinePlayers().forEach {
					val remnants = getRemnantsFromRelic(it.inventory.itemInMainHand)
					if (!remnants.contains(RemnantType.SHADE)) return@forEach
					if ((timeSinceLastHit[it] ?: 0) <= System.currentTimeMillis() + 20000) return@forEach
					it.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(19, 0))
				}
			}
		}.runTaskTimer(plugin, 0, 10)
		super.init()
	}
}